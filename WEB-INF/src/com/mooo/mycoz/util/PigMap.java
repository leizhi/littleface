package com.mooo.mycoz.util;

import java.util.Hashtable;

import com.mooo.mycoz.util.PigNode;

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
