package com.mooo.mycoz.db.sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchSQL extends AbstractSQL {
	// Singleton
	private static Object initLock = new Object();
	private static SearchSQL factory = null;

	private SearchSQL() {
		catalog = null;
		table = null;
		whereKey = new ArrayList<String>();
		groupBy = new ArrayList<String>();
		orderBy = new ArrayList<String>();
		recordStart = 0;
		recordEnd = 0;
	}

	public void clear() {
		catalog = null;
		table = null;
		whereKey.clear();
		groupBy.clear();
		orderBy.clear();
		recordStart = 0;
		recordEnd = 0;
	}

	public static SearchSQL getInstance() {
		if (factory == null) {
			synchronized (initLock) {
				try {
					factory = new SearchSQL();
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
	private List<String> groupBy;
	private List<String> orderBy;
	private int recordStart;
	private int recordEnd;

	public void setField(String field, String value) {
		whereKey.add(field + "='" + value + "'");
	}

	public void setLike(String field, String value) {
		whereKey.add(field + " LIKE '%" + value + "%'");
	}

	public void setGreaterEqual(String field, String value) {
		whereKey.add(field + " >= '" + value + "'");
	}

	public void setLessEqual(String field, String value) {
		whereKey.add(field + " <= '" + value + "'");
	}

	public void setField(String field, int value) {
		whereKey.add(field + "=" + value);
	}

	public void setGroupBy(String field) {
		groupBy.add(field);
	}

	public void setOrderBy(String field, String type) {
		orderBy.add(field + " " + type);
	}

	public String buildSQL() {
		String value;
		String sql = "SELECT * FROM ";

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

		if (groupBy != null && !groupBy.isEmpty()) {
			sql += " GROUP BY ";
			for (Iterator<String> it = groupBy.iterator(); it.hasNext();) {
				value = (String) it.next();
				sql += value + ",";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));
		}

		if (orderBy != null && !orderBy.isEmpty()) {
			sql += " ORDER BY ";
			for (Iterator<String> it = orderBy.iterator(); it.hasNext();) {
				value = (String) it.next();
				sql += value + ",";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));
		}

		if (recordStart != 0 && recordEnd != 0) {
			sql += " LIMIT " + recordStart + "," + recordEnd;

		}
		return sql;
	}

	public void setRecord(int recordStart, int recordEnd) {
		this.recordStart = recordStart;
		this.recordEnd = recordEnd;
	}
}
