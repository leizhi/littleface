package org.pig.util;

import java.util.Hashtable;
import org.pig.util.DBNode;

public class DBMap {

	public static Hashtable dbList = null;

	public static Hashtable getDbList() {
		return dbList;
	}

	public static void addNode(String key,DBNode value) {
		if (dbList==null) dbList= new Hashtable();
		dbList.put(key,value);
	}

}
