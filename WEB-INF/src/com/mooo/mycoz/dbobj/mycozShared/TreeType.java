package com.mooo.mycoz.dbobj.mycozShared;

import com.mooo.mycoz.dbobj.DBObject;

public class TreeType extends DBObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4967724540385667356L;
	
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
	
}
