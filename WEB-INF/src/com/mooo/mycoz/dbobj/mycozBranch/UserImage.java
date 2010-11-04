package com.mooo.mycoz.dbobj.mycozBranch;

public class UserImage {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userId;
	private String filepath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
