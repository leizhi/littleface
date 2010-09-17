package com.mooo.mycoz.dbobj.mycozBranch;

import com.mooo.mycoz.dbobj.DBObject;

public class Example extends DBObject {
	private static final long serialVersionUID = 1L;
	Integer id;
	String name;
	Integer age;
	String school;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
}
