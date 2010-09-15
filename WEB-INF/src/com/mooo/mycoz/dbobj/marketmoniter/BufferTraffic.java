package com.mooo.mycoz.dbobj.marketmoniter;

import java.util.*;
import com.mooo.mycoz.dbobj.DBObject;

public class BufferTraffic extends DBObject {
	
	private static final long serialVersionUID = 1L;
	String remoteid;
	Date operDate;
	Integer tradeAmount;
	Double saleMoney;
	
	public String getRemoteid() {
	return remoteid;
	}
	public void setRemoteid(String remoteid) {
	 this.remoteid = remoteid;
	}
	public Date getOperDate() {
	return operDate;
	}
	public void setOperDate(Date operDate) {
	 this.operDate = operDate;
	}
	public Integer getTradeAmount() {
	return tradeAmount;
	}
	public void setTradeAmount(Integer tradeAmount) {
	 this.tradeAmount = tradeAmount;
	}
	public Double getSaleMoney() {
	return saleMoney;
	}
	public void setSaleMoney(Double saleMoney) {
	 this.saleMoney = saleMoney;
	}
}
