package com.mooo.mycoz.dbobj.mycozBranch;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.DbProcess;

public class ThreadTypeExtension {
	private Integer id;

	private Integer topics;
	private Integer posts;
	private Message lastPost;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTopics() {
		DbProcess dbProcess = DbFactory.getInstance();
		ForumThread forumThread = new ForumThread();
		forumThread.setTypeId(getId());
		try {
			topics = dbProcess.count(forumThread);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return topics;
	}
	public void setTopics(Integer topics) {
		this.topics = topics;
	}
	public Integer getPosts() {
		posts = 0;
		
		DbProcess dbProcess = DbFactory.getInstance();
		ForumThread forumThread = new ForumThread();
		forumThread.setTypeId(getId());
		try {
			List<Object> forumThreadList = dbProcess.searchAndRetrieveList(forumThread);
			Message message = null;
			for (Iterator<?> it = forumThreadList.iterator(); it.hasNext();) {
				forumThread = (ForumThread) it.next();
				message = new Message();
				message.setThreadId(forumThread.getId());
				posts += dbProcess.count(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}
	public void setPosts(Integer posts) {
		this.posts = posts;
	}
	public Message getLastPost() {
		return lastPost;
	}
	public void setLastPost(Message lastPost) {
		this.lastPost = lastPost;
	}
	
}
