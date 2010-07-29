package com.mooo.mycoz.db.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DbBulildSQL extends AbstractSQL implements DbSql{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6187804890473172495L;

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
		
		groupBy = new ArrayList<String>();
		orderBy = new ArrayList<String>();
		recordStart = 0;
		recordEnd = 0;
		
		fields = new HashMap();
		likeField= new HashMap();
		greaterEqualField= new HashMap();
		lessEqual= new HashMap();
		
		updateKey = new ArrayList<String>();
	}
	*/
	public DbBulildSQL() {
		catalog = null;
		table = null;
		
		groupBy = new ArrayList<String>();
		orderBy = new ArrayList<String>();
		recordStart = 0;
		recordEnd = 0;
		
		fields = new HashMap();
		likeField= new HashMap();
		greaterEqualField= new HashMap();
		lessEqualField= new HashMap();
		
		updateKey = new ArrayList<String>();
	}
	public void clear() {
		catalog = null;
		table = null;
		
		fields.clear();
		likeField.clear();
		greaterEqualField.clear();
		lessEqualField.clear();
		
		groupBy.clear();
		orderBy.clear();
		updateKey.clear();
		
		recordStart = 0;
		recordEnd = 0;
	}
	
	//public
	
	//Add SQL
	private Map fields;
	
	public void setField(String field, String value) {
		fields.put(field, value);
	}

	public void setField(String field, Integer value) {
		fields.put(field, value);
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

		if (fields != null && !fields.isEmpty()) {
			sql += "(";
			for (Iterator<String> it = fields.keySet().iterator(); it.hasNext();) {
				value = (String) it.next();
				sql += value + ",";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));
			sql += ") VALUES (";

			for (Iterator<String> it = fields.values().iterator(); it.hasNext();) {
				value = (String) it.next();
				sql += "'"+value + "',";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));

			sql += ")";
		}
		clear();
		
		System.out.println("AddSQL="+sql);

		return sql;
	}
	
	/////////Search SQL
	
	private List<String> groupBy;
	private List<String> orderBy;
	private int recordStart;
	private int recordEnd;
	
	private Map likeField;
	private Map greaterEqualField;
	private Map lessEqualField;

	public void setLike(String field, String value) {
		likeField.put(field, value);
	}

	public void setGreaterEqual(String field, String value) {
		greaterEqualField.put(field, value);
	}

	public void setLessEqual(String field, String value) {
		lessEqualField.put(field, value);
	}

	public void setGroupBy(String field) {
		groupBy.add(field);
	}

	public void setOrderBy(String field, String type) {
		orderBy.add(field + " " + type);
	}

	public String SearchSQL() {
		
		String key;
		String value;

		String sql = "SELECT * FROM ";

		if (catalog != null)
			sql += catalog + ".";
		
		if (table != null)
			sql += table;
		else
			sql += this.getClass().getSimpleName();

		if (fields != null && !fields.isEmpty()) {
			sql += " WHERE ";
			//fields
			for (Iterator<String> it = fields.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				sql += key + "='" + fields.get(key) + "' AND ";
			}
			//likes
			for (Iterator<String> it = likeField.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				sql += key + " LIKE '%" + likeField.get(key) + "%' AND ";
			}	
			//greaterEqualField
			for (Iterator<String> it = greaterEqualField.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				sql += key + "  >= '" + likeField.get(key) + "' AND ";
			}
			
			//lessEqual
			for (Iterator<String> it = lessEqualField.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				sql += key + " <= '" + likeField.get(key) + "' AND ";
			}
			
			sql = sql.substring(0, sql.lastIndexOf("AND"));
			
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
		clear();
		
		System.out.println("SearchSQL="+sql);

		return sql;
	}

	public void setRecord(int recordStart, int recordEnd) {
		this.recordStart = recordStart;
		this.recordEnd = recordEnd;
	}
	
	//Delete SQL
	public String DeleteSQL() {
		String key;
		String sql = "DELETE FROM ";

		if (catalog != null)
			sql += catalog + ".";
		
		if (table != null)
			sql += table;
		else
			sql += this.getClass().getSimpleName();

		if (fields != null && !fields.isEmpty()) {
			sql += " WHERE ";
			//fields
			for (Iterator<String> it = fields.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				sql += key + "='" + fields.get(key) + "' AND ";
			}
			//likes
			for (Iterator<String> it = likeField.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				sql += key + " LIKE '%" + likeField.get(key) + "%' AND ";
			}	
			//greaterEqualField
			for (Iterator<String> it = greaterEqualField.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				sql += key + "  >= '" + likeField.get(key) + "' AND ";
			}
			
			//lessEqual
			for (Iterator<String> it = lessEqualField.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				sql += key + " <= '" + likeField.get(key) + "' AND ";
			}
			sql = sql.substring(0, sql.lastIndexOf(" AND "));
		}
		clear();
		System.out.println("DeleteSQL="+sql);

		return sql;
	}
	//Update SQL
	private static List<String> updateKey;

	public void setUpdate(String field, String value) {
		updateKey.add(field + "='" + value + "'");
	}

	public String UpdateSQL() {
		String key;
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
			for (Iterator<String> it = updateKey.iterator(); it.hasNext();) {
				value = (String) it.next();
				sql += value + ",";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));
		}

		if (fields != null && !fields.isEmpty()) {
			sql += " WHERE ";
			//fields
			for (Iterator<String> it = fields.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				sql += key + "='" + fields.get(key) + "' AND ";
			}
			//likes
			for (Iterator<String> it = likeField.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				sql += key + " LIKE '%" + likeField.get(key) + "%' AND ";
			}	
			//greaterEqualField
			for (Iterator<String> it = greaterEqualField.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				sql += key + "  >= '" + likeField.get(key) + "' AND ";
			}
			
			//lessEqual
			for (Iterator<String> it = lessEqualField.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				sql += key + " <= '" + likeField.get(key) + "' AND ";
			}
			sql = sql.substring(0, sql.lastIndexOf(" AND "));
		}
		clear();
		System.out.println("UpdateSQL="+sql);

		return sql;
	}
}
