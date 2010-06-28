package org.pig.util;

public class DBNode {

	public String id = "";
	public String driver = "";
	public String url= "";
	public String name = "";
	public String password = "";
	public String description ="";
	//public DBNode previous;
	//public DBNode next;

	public String getId() {
		return id;
	}

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getDescription() {
		return description;
	}

	public void setId(String str) {
		id = str;
	}

	public void setDriver(String str) {
		driver = str;
	}

	public void setUrl(String str) {
		url = str;
	}

	public void setName(String str) {
		name = str;
	}

	public void setPassword(String str) {
		password = str;
	}

	public void setDescription(String str) {
		description = str;
	}

}
