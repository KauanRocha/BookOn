package br.com.bookon.server.models.mongo;

public class User {

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

	public User(br.com.bookon.server.models.postgres.User user) {
		this.id = user.getId();
		this.name = user.getUsername();
		
	}
	
	public User() {
	}
	
}