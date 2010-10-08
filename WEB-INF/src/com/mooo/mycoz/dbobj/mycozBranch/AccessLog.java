package com.mooo.mycoz.dbobj.mycozBranch;

import java.util.Date;

public class AccessLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1500604579062955853L;

	Integer id;
	String ip;
	Date startdate;
	Date enddate;
	public Integer getId() {
	return id;
	}
	public void setId(Integer id) {
	 this.id = id;
	}
	public String getIp() {
	return ip;
	}
	public void setIp(String ip) {
	 this.ip = ip;
	}
	public Date getStartdate() {
	return startdate;
	}
	public void setStartdate(Date startdate) {
	 this.startdate = startdate;
	}
	public Date getEnddate() {
	return enddate;
	}
	public void setEnddate(Date enddate) {
	 this.enddate = enddate;
	}
}
