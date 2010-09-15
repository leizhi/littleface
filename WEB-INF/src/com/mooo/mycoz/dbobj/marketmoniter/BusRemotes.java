package com.mooo.mycoz.dbobj.marketmoniter;

import java.util.*;
import com.mooo.mycoz.dbobj.DBObject;
public class BusRemotes extends DBObject {
	private static final long serialVersionUID = 1L;
	String remoteid;
	String remotename;
	Date lastpushtime;
	Integer lastpushed;
	Integer linkstate;
	String enabled;
	String maxpayid;
	String maxsaleflowid;
	String zone;
	String industry;
	String masterenterp;
	String addr;
	String area;
	String remarks;
	String rootkey;
	Date payhiststart;
	String dirtypayhist;
	public String getRemoteid() {
	return remoteid;
	}
	public void setRemoteid(String remoteid) {
	 this.remoteid = remoteid;
	}
	public String getRemotename() {
	return remotename;
	}
	public void setRemotename(String remotename) {
	 this.remotename = remotename;
	}
	public Date getLastpushtime() {
	return lastpushtime;
	}
	public void setLastpushtime(Date lastpushtime) {
	 this.lastpushtime = lastpushtime;
	}
	public Integer getLastpushed() {
	return lastpushed;
	}
	public void setLastpushed(Integer lastpushed) {
	 this.lastpushed = lastpushed;
	}
	public Integer getLinkstate() {
	return linkstate;
	}
	public void setLinkstate(Integer linkstate) {
	 this.linkstate = linkstate;
	}
	public String getEnabled() {
	return enabled;
	}
	public void setEnabled(String enabled) {
	 this.enabled = enabled;
	}
	public String getMaxpayid() {
	return maxpayid;
	}
	public void setMaxpayid(String maxpayid) {
	 this.maxpayid = maxpayid;
	}
	public String getMaxsaleflowid() {
	return maxsaleflowid;
	}
	public void setMaxsaleflowid(String maxsaleflowid) {
	 this.maxsaleflowid = maxsaleflowid;
	}
	public String getZone() {
	return zone;
	}
	public void setZone(String zone) {
	 this.zone = zone;
	}
	public String getIndustry() {
	return industry;
	}
	public void setIndustry(String industry) {
	 this.industry = industry;
	}
	public String getMasterenterp() {
	return masterenterp;
	}
	public void setMasterenterp(String masterenterp) {
	 this.masterenterp = masterenterp;
	}
	public String getAddr() {
	return addr;
	}
	public void setAddr(String addr) {
	 this.addr = addr;
	}
	public String getArea() {
	return area;
	}
	public void setArea(String area) {
	 this.area = area;
	}
	public String getRemarks() {
	return remarks;
	}
	public void setRemarks(String remarks) {
	 this.remarks = remarks;
	}
	public String getRootkey() {
	return rootkey;
	}
	public void setRootkey(String rootkey) {
	 this.rootkey = rootkey;
	}
	public Date getPayhiststart() {
	return payhiststart;
	}
	public void setPayhiststart(Date payhiststart) {
	 this.payhiststart = payhiststart;
	}
	public String getDirtypayhist() {
	return dirtypayhist;
	}
	public void setDirtypayhist(String dirtypayhist) {
	 this.dirtypayhist = dirtypayhist;
	}
}
