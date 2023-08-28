package br.com.bookon.server.payload.response;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.bookon.server.models.User;

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

    public Set<RoleResponse> getRoles() {
        return user.getRoles().stream().map(RoleResponse::new).collect(Collectors.toSet());
    }

}
