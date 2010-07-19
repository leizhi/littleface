package com.mooo.mycoz.jdbc;

import java.util.Hashtable;

import com.mooo.mycoz.jdbc.DBNode;

public class DBMap {

	public static Hashtable<String, DBNode> dbList = null;

	public static Hashtable<String, DBNode> getDbList() {
		return dbList;
	}

	public static void addNode(String key,DBNode value) {
		if (dbList==null) dbList= new Hashtable<String, DBNode>();
		dbList.put(key,value);
	}

}
