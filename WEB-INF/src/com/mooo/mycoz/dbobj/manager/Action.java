package com.mooo.mycoz.dbobj.manager;

public class Action {

	private Integer id;
	private Integer controllerID;
	private String name;
	private Boolean isEnable;
	private String controllerName;

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public Integer getId() {
		return id;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getControllerID() {
		return controllerID;
	}

	public void setControllerID(Integer controllerID) {
		this.controllerID = controllerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
