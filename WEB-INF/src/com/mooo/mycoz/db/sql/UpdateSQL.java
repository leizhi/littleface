package com.mooo.mycoz.db.sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UpdateSQL extends AbstractSQL {
	// Singleton

	private static Object initLock = new Object();
	private static UpdateSQL factory = null;

	private UpdateSQL() {
		catalog = null;
		table = null;
		whereKey = new ArrayList<String>();
		updateKey = new ArrayList<String>();
	}

	public static UpdateSQL getInstance() {
		if (factory == null) {
			synchronized (initLock) {
				try {
					factory = new UpdateSQL();
				} catch (Exception e) {
					System.err.println("Exception ARBean." + e.getMessage());
					e.printStackTrace();
					return null;
				}
			}
		}
		return factory;
	}

	private List<String> whereKey;
	private List<String> updateKey;

	public void setUpdate(String field, String value) {
		updateKey.add(field + "='" + value + "'");
	}

	public void setField(String field, String value) {
		whereKey.add(field + "='" + value + "'");
	}

	public String buildSQL() {
		String value;
		String sql = "UPDATE ";

		if (catalog != null)
			sql += catalog + ".";
		if (table != null)
			sql += table;

		if (updateKey != null && !updateKey.isEmpty()) {
			sql += " SET ";
			for (Iterator<String> it = whereKey.iterator(); it.hasNext();) {
				value = (String) it.next();
				sql += value + ",";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));
		}

		if (whereKey != null && !whereKey.isEmpty()) {
			sql += " WHERE ";
			for (Iterator<String> it = whereKey.iterator(); it.hasNext();) {
				value = (String) it.next();
				sql += value + " AND ";
			}
			sql = sql.substring(0, sql.lastIndexOf(" AND "));
		}

		return sql;
	}
}
