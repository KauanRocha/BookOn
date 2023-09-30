package br.com.bookon.server.models.mongo;

import br.com.bookon.server.models.postgres.User;

public class UserMongo {

	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public UserMongo(User user) {
		this.id = user.getId();
		this.name = user.getUsername();
		
	}
	
	public UserMongo() {
	}
	
}