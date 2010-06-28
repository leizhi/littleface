package org.pig.util;

import java.util.Hashtable;
import org.pig.util.PigNode;

public class PigMap {

	public static Hashtable mvcList = null;

	public static Hashtable getMvcList() {
		return mvcList;
	}

	public static void addNode(String key, PigNode value) {
		if (mvcList==null) mvcList = new Hashtable();
		mvcList.put(key,value);
	}

}
