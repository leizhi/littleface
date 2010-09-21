package com.mooo.mycoz.dbobj.mycozBranch;

import java.sql.Timestamp;

public class AccessLog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1500604579062955853L;
	
	private Integer id;
	private String ip;
	private Timestamp startdate;
	private Timestamp enddate;
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
	public Timestamp getStartdate() {
		return startdate;
	}
	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}
	public Timestamp getEnddate() {
		return enddate;
	}
	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}
}
