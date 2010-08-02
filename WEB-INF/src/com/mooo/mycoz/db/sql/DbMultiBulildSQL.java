package com.mooo.mycoz.db.sql;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DbMultiBulildSQL implements DbMultiSql {

	public String catalog;
	public Map<String,String> tables;
	public List<String> whereKey;
	public List<String> retrieveFields;
	public List<String> groupBy;
	public List<String> orderBy;
	public int offset;
	public int rowcount;
	
	public Map<String,Class<?>> objs;
	
	public Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public DbMultiBulildSQL() {
		catalog = null;
		objs = new HashMap<String, Class<?>>();
		tables = new HashMap<String,String>();
		whereKey = new ArrayList<String>();
		retrieveFields = new ArrayList<String>();
		groupBy = new ArrayList<String>();
		orderBy = new ArrayList<String>();
		offset = 0;
		rowcount = 0;
	}
	
	public void clear() {
		catalog = null;
		tables.clear();
		whereKey.clear();
		retrieveFields.clear();
		groupBy.clear();
		orderBy.clear();
		offset = 0;
		rowcount = 0;
	}
	public void addTable(Class<?> clazz, String alias) {
		
		objs.put(alias, clazz);
		
		if (catalog != null)
			tables.put(alias, catalog + "." + clazz.getSimpleName());
		else
			tables.put(alias, getDbName(clazz));
	}
	
	public void addTable(String name, String alias) {
		if (catalog != null)
			tables.put(alias, catalog + "." + name);
		else
			tables.put(alias, name);
	}

	public void addTable(String catalog, String name, String alias) {
		tables.put(alias, catalog + "." + name);
	}

	public void setRetrieveField(String alias, String field) {
		retrieveFields.add(alias + "." + field);
	}

	public void setForeignKey(String name, String field, String fName,
			String fField) {
		whereKey.add(name + "." + field + "=" + fName + "." + fField);
	}

	public void setField(String field, String value) {
		whereKey.add(field + "='" + value + "'");
	}

	public void setField(String field, int value) {
		whereKey.add(field + "=" + value);
	}

	public void setLike(String alias, String field, String value) {
		whereKey.add(alias + "." + field + " LIKE '%" + value + "%'");
	}

	public void setGreaterEqual(String alias, String field, String value) {
		whereKey.add(alias + "." + field + " >= '" + value + "'");
	}

	public void setLessEqual(String alias, String field, String value) {
		whereKey.add(alias + "." + field + " <= '" + value + "'");
	}

	public void setNotEqual(String alias, String field, String value) {
		whereKey.add(alias + "." + field + " <> '" + value + "'");
	}

	public void addCustomWhereClause(String value) {
		whereKey.add(value);
	}

	public void setGroupBy(String alias, String field) {
		groupBy.add(alias + "." + field);
	}

	public void setOrderBy(String alias, String field, String type) {
		orderBy.add(field + " " + type);
	}

	public void setOrderBy(String alias, String field) {
		orderBy.add(alias + "." + field);
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String searchSQL() {
		String key;
		String value;
		String sql = "";
		if (retrieveFields != null && !retrieveFields.isEmpty()) {
			sql += "SELECT ";
			for (Iterator<String> it = retrieveFields.iterator(); it.hasNext();) {
				value = it.next();
				sql += value + ",";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));

		}

		if (tables != null && !tables.isEmpty()) {
			sql += " FROM ";
			for (Iterator<?> it = tables.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				value = (String) tables.get(key);
				sql += value +" "+key+ ",";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));
		}

		if (whereKey != null && !whereKey.isEmpty()) {
			sql += " WHERE ";
			for (Iterator<String> it = whereKey.iterator(); it.hasNext();) {
				value = it.next();
				sql += value + " AND ";
			}
			sql = sql.substring(0, sql.lastIndexOf(" AND "));
		}

		if (groupBy != null && !groupBy.isEmpty()) {
			sql += " GROUP BY ";
			for (Iterator<String> it = groupBy.iterator(); it.hasNext();) {
				value = it.next();
				sql += value + ",";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));
		}

		if (orderBy != null && !orderBy.isEmpty()) {
			sql += " ORDER BY ";
			for (Iterator<String> it = orderBy.iterator(); it.hasNext();) {
				value = it.next();
				sql += value + ",";
			}
			sql = sql.substring(0, sql.lastIndexOf(","));
		}

		if (offset != 0 && rowcount != 0) {
			sql += " LIMIT " + offset + "," + rowcount;

		} else if (rowcount != 0) {
			sql += " LIMIT " + rowcount;

		}

		// clear();

		return sql;
	}

	public String buildCountSQL() {
		String searchSQL = searchSQL();

		String sql = "SELECT COUNT(*) ";
		searchSQL = searchSQL.substring(searchSQL.indexOf("FROM"));
		sql += searchSQL;
		return sql;
	}

	public void setRecord(int offset, int rowcount) {
		this.offset = offset;
		this.rowcount = rowcount;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}

	public int count() {
		return 0;
	}
	
	public String getDbName(Class<?> clazz){
		
		String value ="";
		String fillName = clazz.getName();
		String[] packArray=fillName.split("\\.");

		for(int i=packArray.length-2;i<packArray.length;i++)
			value += packArray[i]+".";
		value = value.substring(0, value.length()-1);
		
		return value;
	}
}
