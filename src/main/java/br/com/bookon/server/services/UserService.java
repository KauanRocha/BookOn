package br.com.bookon.server.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.bookon.server.enumerations.RoleEnum;
import br.com.bookon.server.models.postgres.Role;
import br.com.bookon.server.models.postgres.User;
import br.com.bookon.server.payload.request.postgres.AddressResquest;
import br.com.bookon.server.payload.request.postgres.FilterRequest;
import br.com.bookon.server.payload.request.postgres.RegisterRequest;
import br.com.bookon.server.payload.response.postgres.GeolocationResponse;
import br.com.bookon.server.payload.response.postgres.MessageResponse;
import br.com.bookon.server.payload.response.simple.postgres.UserSimpleResponse;
import br.com.bookon.server.repository.postgres.RoleRepository;
import br.com.bookon.server.repository.postgres.UserRepository;
import br.com.bookon.server.specification.UserSpecification;


@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private GeolocationService geolocationService;
    
    @Autowired
    private PasswordEncoder encoder;
    
    public Page<User> list(FilterRequest filterRequest) {
        UserSpecification spec = new UserSpecification();
        return userRepository.findAll(spec.search(filterRequest, User.class), filterRequest.build());
    }
    
    public GeolocationResponse geolocation(AddressResquest address) {
        
        String formattedAddress = (address.getStreet() + " " +
                address.getNumber() + " " +
                address.getCity() + " " +
                address.getState() + " " +
                address.getCountry() + " " +
                address.getPostalCode())
                .replaceAll("\\s+", "+");
        
        return geolocationService.getGeolocation(formattedAddress);
    }
    
    public ResponseEntity<?> register(RegisterRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setRoles(strRolesForEnum(signUpRequest.getRole()));
        user.setLatitude(geolocation(signUpRequest.getAddress()).getResults().get(0).getGeometry().getLocation().getLat());
        user.setLongitude(geolocation(signUpRequest.getAddress()).getResults().get(0).getGeometry().getLocation().getLng());
        
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    
    public Set<Role> strRolesForEnum(Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleEnum.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleEnum.ADMININSTRATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(RoleEnum.MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleEnum.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        return roles;
    }
    
    public List<UserSimpleResponse> getBookByLocation(Integer userId) {
    	User userFinder = userRepository.findById(userId).orElse(null);
    	
    	List<Object[]> nearbyUsers = userRepository.
    			findIdsAndDistancesOfNearbyUsers(userId, userFinder.getLatitude(), userFinder.getLongitude());
    	
    	List<UserSimpleResponse> userResponseList = nearbyUsers.stream().map(userData -> {
    	    User userFound = userRepository.findById((Integer) userData[0]).orElse(null);
    	    String distance = String.format("%.3f",userData[1]);
    	    
    	    UserSimpleResponse userResponse = new UserSimpleResponse(userFound, distance);
    	    userResponse.setDistance(distance);
    	    
    	    return userResponse;
    	}).filter(userResponse -> userResponse.getBooks().size() >= 1)
    			.collect(Collectors.toList());
    	
        return userResponseList;
    }

}
