package com.mooo.mycoz.db.sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiSQLFactory {
	// Singleton
	private static Object initLock = new Object();
	private static MultiSQL factory = null;

	private MultiSQLFactory() {

	}

	public static MultiSQL getInstance() {
		if (factory == null) {
			synchronized (initLock) {
				try {
					factory = new CommonMultiSQL();
				} catch (Exception e) {
					System.err.println("Exception ARBean." + e.getMessage());
					e.printStackTrace();
					return null;
				}
			}
		}
		return factory;
	}

}
