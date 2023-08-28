package br.com.bookon.server.payload.response;

import br.com.bookon.server.enumerations.RoleEnum;
import br.com.bookon.server.models.Role;

public class RoleResponse {

    private Role role;

    public RoleResponse(Role role) {
        this.role = role;
    }

    public RoleEnum getName() {
        return role.getName();
    }

}
