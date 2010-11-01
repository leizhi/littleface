package com.mooo.mycoz.dbobj.mycozShared;

public class City {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer countryId;
	private String code;
	private String name;
	private String description;
	public Integer getId() {
	return id;
	}
	public void setId(Integer id) {
	 this.id = id;
	}
	public Integer getCountryId() {
	return countryId;
	}
	public void setCountryId(Integer countryId) {
	 this.countryId = countryId;
	}
	public String getCode() {
	return code;
	}
	public void setCode(String code) {
	 this.code = code;
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
