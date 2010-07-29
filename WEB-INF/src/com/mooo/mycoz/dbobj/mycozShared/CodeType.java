package com.mooo.mycoz.dbobj.mycozShared;

import com.mooo.mycoz.dbobj.DBObject;

public class CodeType extends DBObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3328991927142750586L;
	private Integer id;
	private String name;
	private String category;
	
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	
}
