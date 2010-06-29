package com.manihot.xpc.tools;

import com.manihot.xpc.cache.CacheManager;

public class CacheClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CacheManager cm = CacheManager.getInstance();
		cm.add("0", "table", null);
		
	}

}
