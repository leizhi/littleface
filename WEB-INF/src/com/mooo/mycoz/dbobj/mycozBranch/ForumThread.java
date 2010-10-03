package com.mooo.mycoz.dbobj.mycozBranch;

import java.sql.SQLException;
import java.util.Date;

import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.DbProcess;

public class ForumThread {
	Integer id;
	Integer forumId;
	Integer userId;
	String subject;
	String body;
	Date modifiedDate;
	Date creationDate;
	String approved;
	Integer ranking;
	String closed;
	Integer replyPrivateUserId;
	public Integer getId() {
	return id;
	}
	public void setId(Integer id) {
	 this.id = id;
	}
	public Integer getForumId() {
	return forumId;
	}
	public void setForumId(Integer forumId) {
	 this.forumId = forumId;
	}
	public Integer getUserId() {
	return userId;
	}
	public void setUserId(Integer userId) {
	 this.userId = userId;
	}
	public String getSubject() {
	return subject;
	}
	public void setSubject(String subject) {
	 this.subject = subject;
	}
	public String getBody() {
	return body;
	}
	public void setBody(String body) {
	 this.body = body;
	}
	public Date getModifiedDate() {
	return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
	 this.modifiedDate = modifiedDate;
	}
	public Date getCreationDate() {
	return creationDate;
	}
	public void setCreationDate(Date creationDate) {
	 this.creationDate = creationDate;
	}
	public String getApproved() {
	return approved;
	}
	public void setApproved(String approved) {
	 this.approved = approved;
	}
	public Integer getRanking() {
	return ranking;
	}
	public void setRanking(Integer ranking) {
	 this.ranking = ranking;
	}
	public String getClosed() {
	return closed;
	}
	public void setClosed(String closed) {
	 this.closed = closed;
	}
	public Integer getReplyPrivateUserId() {
	return replyPrivateUserId;
	}
	public void setReplyPrivateUserId(Integer replyPrivateUserId) {
	 this.replyPrivateUserId = replyPrivateUserId;
	}
	
	public User getUser() {
		DbProcess dbProcess = DbFactory.getInstance();
		User user = new User();
		user.setId(this.getUserId());
		try {
			dbProcess.retrieve(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public User getReplyPrivateUser() {
		DbProcess dbProcess = DbFactory.getInstance();
		User user = new User();
		user.setId(this.getReplyPrivateUserId());
		try {
			dbProcess.retrieve(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
