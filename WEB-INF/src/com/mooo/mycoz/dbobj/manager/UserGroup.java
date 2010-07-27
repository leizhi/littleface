package com.mooo.mycoz.dbobj.manager;

import java.util.Set;

public class UserGroup {

	private Integer id;
	private String name;
	private String description;
	private Set<?> user;

	public Set<?> getUser() {
		return user;
	}

	public void setUser(Set<?> user) {
		this.user = user;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
