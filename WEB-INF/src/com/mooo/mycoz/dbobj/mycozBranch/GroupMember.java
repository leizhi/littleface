package com.mooo.mycoz.dbobj.mycozBranch;

public class GroupMember {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer groupId;
	private Integer userId;
	public Integer getId() {
	return id;
	}
	public void setId(Integer id) {
	 this.id = id;
	}
	public Integer getGroupId() {
	return groupId;
	}
	public void setGroupId(Integer groupId) {
	 this.groupId = groupId;
	}
	public Integer getUserId() {
	return userId;
	}
	public void setUserId(Integer userId) {
	 this.userId = userId;
	}
}
