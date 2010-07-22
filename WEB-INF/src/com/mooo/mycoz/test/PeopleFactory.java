package com.mooo.mycoz.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PeopleFactory {
	// Singleton
	private static Object initLock = new Object();
	private static People factory = null;

	private PeopleFactory() { }

	public static People getInstance(String id) {
		//if (factory == null) {
			synchronized (initLock) {
				try {
					factory = new Man(id);
				} catch (Exception e) {
					System.err.println("Exception ARBean." + e.getMessage());
					e.printStackTrace();
					return null;
				}
			}
		//}
		return factory;
	}

}
