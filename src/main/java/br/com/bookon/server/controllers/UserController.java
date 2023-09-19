package br.com.bookon.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bookon.server.payload.request.postgres.FilterRequest;
import br.com.bookon.server.payload.response.postgres.UserResponse;
import br.com.bookon.server.payload.response.simple.postgres.UserSimpleResponse;
import br.com.bookon.server.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users/search")
    public Page<UserResponse> search(@RequestBody FilterRequest filterRequest) {
        return userService.list(filterRequest).map(UserResponse::new);
    }
    
    @PostMapping("/{userId}")
    public ResponseEntity<List<UserSimpleResponse>>getBookByLocation(@PathVariable Integer userId) {
    	List<UserSimpleResponse> users = userService.getBookByLocation(userId);
    	return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
}
