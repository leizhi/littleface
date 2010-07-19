package com.mooo.mycoz.db.sql;

public interface DBOperation extends DBCommon {
	
	String getTable();
	
	void setTable(String table);
	
 	//void clear();
}
