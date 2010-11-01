package com.mooo.mycoz.dbobj.mycozBranch;

public class ThreadType {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer forumId;
	
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
	public Integer getForumId() {
	return forumId;
	}
	public void setForumId(Integer forumId) {
	 this.forumId = forumId;
	}
	
	// extended attribute

	private ThreadTypeExtension extension;

	public ThreadTypeExtension getExtension() {
		extension = new ThreadTypeExtension();
		extension.setId(getId());
		return extension;
	}
	
	public void setExtension(ThreadTypeExtension extension) {
		this.extension = extension;
	}
}
