package com.mooo.mycoz.dbobj.manager;

public class AuthRole {

	private Integer id;
	private Integer roleID;
	private Integer controllerID;
	private Integer actionID;

	private String roleName;
	private String actionName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public Integer getControllerID() {
		return controllerID;
	}

	public void setControllerID(Integer controllerID) {
		this.controllerID = controllerID;
	}

	public Integer getActionID() {
		return actionID;
	}

	public void setActionID(Integer actionID) {
		this.actionID = actionID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

}
