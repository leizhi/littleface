package com.mooo.mycoz.dbobj.mycozBranch;

import com.mooo.mycoz.dbobj.DbFactory;

public class Example extends DbFactory {
	private static final long serialVersionUID = 1L;
	Double id;
	String name;
	Double age;
	String school;

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
}
