package com.mooo.mycoz.db.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DbBulildSQL extends AbstractSQL {
	// Singleton
/*
	private static Object initLock = new Object();
	private static DbBulildSQL factory = null;
	
	public static DbBulildSQL getInstance() {
		if (factory == null) {
			synchronized (initLock) {
				try {
					factory = new DbBulildSQL();
				} catch (Exception e) {
					System.err.println("Exception ARBean." + e.getMessage());
					e.printStackTrace();
					return null;
				}
			}
		}
		return factory;
	}
	
	private DbBulildSQL() {
		catalog = null;
		table = null;
		
		whereKey = new ArrayList<String>();
		groupBy = new ArrayList<String>();
		orderBy = new ArrayList<String>();
		recordStart = 0;
		recordEnd = 0;
		
		intoKey = new HashMap();
		updateKey = new ArrayList<String>();
	}
	*/
	public DbBulildSQL() {
		catalog = null;
		table = null;
		
		whereKey = new ArrayList<String>();
		groupBy = new ArrayList<String>();
		orderBy = new ArrayList<String>();
		recordStart = 0;
		recordEnd = 0;
		
		intoKey = new HashMap();
		updateKey = new ArrayList<String>();
	}
	
	public void clear() {
		catalog = null;
		table = null;
		whereKey.clear();
		groupBy.clear();
		orderBy.clear();
		intoKey.clear();
		recordStart = 0;
		recordEnd = 0;
	}
	
	//public
	
	public void setField(String field, String value) {
		setAddField(field,value);
		setWhereField(field,value);
	}
	
	public void setField(String field, int value) {
		setAddField(field,value);
		setWhereField(field,value);
	}
	
	//Add SQL
	private Map intoKey;
	
	private void setAddField(String field, String value) {
		intoKey.put(field, "'" + value + "'");
	}

	private void setAddField(String field, int value) {
		intoKey.put(field, value);
	}

	public String AddSQL() {
		String value;
		String sql = "INSERT INTO ";

		if (catalog != null)
			sql += catalog + ".";
		
		if (table != null)
			sql += table;
		else
			sql += this.getClass().getSimpleName();

		if (intoKey != null && !intoKey.isEmpty()) {
			sql += "(";
			for (Iterator<String> it = intoKey.keySet().iterator(); it.hasNext();) {
				value = (String) it.next();
				sql += value + ",";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));
			sql += ") VALUES (";

			for (Iterator<String> it = intoKey.values().iterator(); it.hasNext();) {
				value = (String) it.next();
				sql += value + ",";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));

			sql += ")";
		}

		return sql;
	}
	
	/////////Search SQL
	private List<String> whereKey;
	
	private List<String> groupBy;
	private List<String> orderBy;
	private int recordStart;
	private int recordEnd;

	private void setWhereField(String field, String value) {
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

	private void setWhereField(String field, int value) {
		whereKey.add(field + "=" + value);
	}

	public void setGroupBy(String field) {
		groupBy.add(field);
	}

	public void setOrderBy(String field, String type) {
		orderBy.add(field + " " + type);
	}

	public String SearchSQL() {
		String value;
		String sql = "SELECT * FROM ";

		if (catalog != null)
			sql += catalog + ".";
		
		System.out.println("table:"+table);

		if (table != null)
			sql += table;
		else
			sql += this.getClass().getSimpleName();

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
	
	//Delete SQL
	public String DeleteSQL() {
		String value;
		String sql = "DELETE FROM ";

		if (catalog != null)
			sql += catalog + ".";
		
		if (table != null)
			sql += table;
		else
			sql += this.getClass().getSimpleName();

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
	//Update SQL
	private List<String> updateKey;

	public void setUpdate(String field, String value) {
		updateKey.add(field + "='" + value + "'");
	}

	public String UpdateSQL() {
		String value;
		String sql = "UPDATE ";

		if (catalog != null)
			sql += catalog + ".";
		
		if (table != null)
			sql += table;
		else
			sql += this.getClass().getSimpleName();

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
