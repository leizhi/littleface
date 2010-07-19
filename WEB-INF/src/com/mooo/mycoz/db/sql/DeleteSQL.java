package com.mooo.mycoz.db.sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteSQL extends AbstractSQL {
	// Singleton
	private static Object initLock = new Object();
	private static DeleteSQL factory = null;

	private DeleteSQL() {
		catalog = null;
		table = null;
		whereKey = new ArrayList<String>();
	}

	public void clear() {
		catalog = null;
		table = null;
		whereKey.clear();
	}

	public static DeleteSQL getInstance() {
		if (factory == null) {
			synchronized (initLock) {
				try {
					factory = new DeleteSQL();
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

	public void setField(String field, String value) {
		whereKey.add(field + "='" + value + "'");
	}

	public void setField(String field, int value) {
		whereKey.add(field + "=" + value);
	}

	public String buildSQL() {
		String value;
		String sql = "DELETE FROM ";

		if (catalog != null)
			sql += catalog + ".";
		if (table != null)
			sql += table;

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
