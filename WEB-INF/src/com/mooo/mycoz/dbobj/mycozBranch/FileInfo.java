package com.mooo.mycoz.dbobj.mycozBranch;

import java.util.Date;


public class FileInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 63855371401315925L;
	
	private Integer id;
	private String name;
	private String filepath;
	private Date datetime;
	private String size;
	private String folder;
	
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
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}

}
