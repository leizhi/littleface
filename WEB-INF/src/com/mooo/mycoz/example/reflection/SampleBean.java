package com.mooo.mycoz.example.reflection;

import java.util.Date;


public class SampleBean {
	private Integer id;
	private String name;
	private Date createDate;
	private SubBean subBean;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public SubBean getSubBean() {
		return subBean;
	}
	public void setSubBean(SubBean subBean) {
		this.subBean = subBean;
	}

}
