package com.mooo.mycoz.dbobj.mycozBranch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.DbProcess;

public class Forum {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	
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
	
	private List threadTypes;

	public List getThreadTypes() {
		threadTypes = new ArrayList();
		
		DbProcess dbProcess = DbFactory.getInstance();
		ThreadType threadType = new ThreadType();
		threadType.setForumId(getId());
		try {
			threadTypes = dbProcess.searchAndRetrieveList(threadType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return threadTypes;
	}
	
	public void setThreadTypes(List threadTypes) {
		this.threadTypes = threadTypes;
	}
	
}
