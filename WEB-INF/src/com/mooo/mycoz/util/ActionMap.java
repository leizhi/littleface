package com.mooo.mycoz.util;

import java.util.HashMap;
import java.util.Map;

public class ActionMap {

	public static Map<String, ActionNode> mvcList = null;

	public static Map<String, ActionNode> getMvcList() {
		return mvcList;
	}

	public static void addNode(String key, ActionNode value) {
		if (mvcList == null)
			mvcList = new HashMap<String, ActionNode>();
		mvcList.put(key, value);
	}

}
