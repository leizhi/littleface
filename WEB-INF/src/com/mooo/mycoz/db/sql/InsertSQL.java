package com.mooo.mycoz.db.sql;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class InsertSQL extends AbstractSQL {
	// Singleton

	private static Object initLock = new Object();
	private static InsertSQL factory = null;

	private InsertSQL() {
		catalog = null;
		table = null;
		intoKey = new HashMap();
	}

	public void clear() {
		catalog = null;
		table = null;
		intoKey.clear();
	}

	public static InsertSQL getInstance() {
		if (factory == null) {
			synchronized (initLock) {
				try {
					factory = new InsertSQL();
				} catch (Exception e) {
					System.err.println("Exception ARBean." + e.getMessage());
					e.printStackTrace();
					return null;
				}
			}
		}
		return factory;
	}

	private Map intoKey;

	public void setField(String field, String value) {
		intoKey.put(field, "'" + value + "'");
	}

	public void setField(String field, int value) {
		intoKey.put(field, value);
	}

	public String buildSQL() {
		String value;
		String sql = "INSERT INTO ";

		if (catalog != null)
			sql += catalog + ".";
		if (table != null)
			sql += table;

		if (intoKey != null && !intoKey.isEmpty()) {
			sql += "(";
			for (Iterator<String> it = intoKey.keySet().iterator(); it
					.hasNext();) {
				value = (String) it.next();
				sql += value + ",";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));
			sql += ") VALUES (";

			for (Iterator<String> it = intoKey.values().iterator(); it
					.hasNext();) {
				value = (String) it.next();
				sql += value + ",";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));

			sql += ")";
		}

		return sql;
	}
}
