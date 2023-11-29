package br.com.bookon.server.payload.response.postgres;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bookon.server.models.postgres.User;

public class UserResponse {

    private User user;
    
    public UserResponse(User user) {
        this.user = user;
    }

	public String getUsername() {
        return user.getUsername();
    }

    public String getEmail() {
        return user.getEmail();
    }
    
    public Double getLatitude() {
		return user.getLatitude();
	}
	
    public Double getLongitude() {
		return user.getLongitude();
	}

    public Set<RoleResponse> getRoles() {
        return user.getRoles().stream().map(RoleResponse::new).collect(Collectors.toSet());
    }
    
    public List<BookResponse> getBooks() {
		return user.getBooks().stream().map(BookResponse::new).collect(Collectors.toList());
	}
    
}
