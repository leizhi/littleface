package com.mooo.mycoz.dbobj.mycozBranch;

import com.mooo.mycoz.dbobj.DBObject;

public class Example extends DBObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer id;
	String name;
	String school;
	String age;

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

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
