package com.mooo.mycoz.dbobj.marketmoniter;

import com.mooo.mycoz.dbobj.DBObject;

public class BufferPrice extends DBObject {
	private static final long serialVersionUID = 1L;
	String remoteid;
	String sampleid;
	Double sale_price;
	String islocal;
	Double sale_qnty;
	Double sale_money;
	Double max_price;
	Double min_price;
	Double paycount;
	String oper_date;
	
	public String getOper_date() {
		return oper_date;
	}
	public void setOper_date(String oper_date) {
		this.oper_date = oper_date;
	}
	public String getRemoteid() {
		return remoteid;
	}
	public void setRemoteid(String remoteid) {
		this.remoteid = remoteid;
	}
	public String getSampleid() {
		return sampleid;
	}
	public void setSampleid(String sampleid) {
		this.sampleid = sampleid;
	}
	public Double getSale_price() {
		return sale_price;
	}
	public void setSale_price(Double sale_price) {
		this.sale_price = sale_price;
	}
	public String getIslocal() {
		return islocal;
	}
	public void setIslocal(String islocal) {
		this.islocal = islocal;
	}
	public Double getSale_qnty() {
		return sale_qnty;
	}
	public void setSale_qnty(Double sale_qnty) {
		this.sale_qnty = sale_qnty;
	}
	public Double getSale_money() {
		return sale_money;
	}
	public void setSale_money(Double sale_money) {
		this.sale_money = sale_money;
	}
	public Double getMax_price() {
		return max_price;
	}
	public void setMax_price(Double max_price) {
		this.max_price = max_price;
	}
	public Double getMin_price() {
		return min_price;
	}
	public void setMin_price(Double min_price) {
		this.min_price = min_price;
	}
	public Double getPaycount() {
		return paycount;
	}
	public void setPaycount(Double paycount) {
		this.paycount = paycount;
	}

}
