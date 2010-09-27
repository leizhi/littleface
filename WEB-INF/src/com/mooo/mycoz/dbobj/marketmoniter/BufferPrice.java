package com.mooo.mycoz.dbobj.marketmoniter;

import java.util.*;
public class BufferPrice  {
	private static final long serialVersionUID = 1L;
	String remoteid;
	String sampleid;
	Double salePrice;
	String islocal;
	Date operDate;
	Double saleQnty;
	Double saleMoney;
	Double maxPrice;
	Double minPrice;
	Integer paycount;
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
	public Double getSalePrice() {
	return salePrice;
	}
	public void setSalePrice(Double salePrice) {
	 this.salePrice = salePrice;
	}
	public String getIslocal() {
	return islocal;
	}
	public void setIslocal(String islocal) {
	 this.islocal = islocal;
	}
	public Date getOperDate() {
	return operDate;
	}
	public void setOperDate(Date operDate) {
	 this.operDate = operDate;
	}
	public Double getSaleQnty() {
	return saleQnty;
	}
	public void setSaleQnty(Double saleQnty) {
	 this.saleQnty = saleQnty;
	}
	public Double getSaleMoney() {
	return saleMoney;
	}
	public void setSaleMoney(Double saleMoney) {
	 this.saleMoney = saleMoney;
	}
	public Double getMaxPrice() {
	return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
	 this.maxPrice = maxPrice;
	}
	public Double getMinPrice() {
	return minPrice;
	}
	public void setMinPrice(Double minPrice) {
	 this.minPrice = minPrice;
	}
	public Integer getPaycount() {
	return paycount;
	}
	public void setPaycount(Integer paycount) {
	 this.paycount = paycount;
	}
}