package com.mooo.mycoz.dbobj.marketmoniter;

import java.sql.Date;

import com.mooo.mycoz.dbobj.DBObject;

public class BusSamples extends DBObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 656828564872501218L;
	String sampleid;	
	String cateid;	
	String samplename;	
	String unit;	
	String remarks;	
	Date startacquist;	
	String isprice;	
	String isstock;
	
	public BusSamples(){
		setTable("bus_samples");
	}
	
	public String getSampleid() {
		return sampleid;
	}
	public void setSampleid(String sampleid) {
		this.sampleid = sampleid;
	}
	public String getCateid() {
		return cateid;
	}
	public void setCateid(String cateid) {
		this.cateid = cateid;
	}
	public String getSamplename() {
		return samplename;
	}
	public void setSamplename(String samplename) {
		this.samplename = samplename;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getStartacquist() {
		return startacquist;
	}
	public void setStartacquist(Date startacquist) {
		this.startacquist = startacquist;
	}
	public String getIsprice() {
		return isprice;
	}
	public void setIsprice(String isprice) {
		this.isprice = isprice;
	}
	public String getIsstock() {
		return isstock;
	}
	public void setIsstock(String isstock) {
		this.isstock = isstock;
	}
	
}
