package br.com.bookon.server.payload.response.mongo;

import br.com.bookon.server.models.mongo.User;

public class UserResponse {

	private User user;

	public Integer getId() {
		return user.getId();
	}
	
	public String getName() {
		return user.getName();
	}

	public UserResponse(User user) {
		this.user = user;
	}
	
}
