package com.mooo.mycoz.db.sql;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.util.ParamUtil;
import com.mooo.mycoz.util.ReflectUtil;

public class DbMultiBulildSQL implements DbMultiSql {

	public String catalog;
	public List<String> tables;
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
		tables = new ArrayList<String>();
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
		
		objs.put(clazz.getSimpleName(), clazz);
		
		if (catalog != null)
			tables.add(catalog + "." + clazz.getSimpleName() + " AS " + alias);
		else
			tables.add(clazz.getSimpleName() + " AS " + alias);
	}
	
	public void addTable(String name, String alias) {
		if (catalog != null)
			tables.add(catalog + "." + name + " AS " + alias);
		else
			tables.add(name + " AS " + alias);
	}

	public void addTable(String catalog, String name, String alias) {
		tables.add(catalog + "." + name + " AS " + alias);
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

	public String SearchSQL() {
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
			for (Iterator<String> it = tables.iterator(); it.hasNext();) {
				value = it.next();
				sql += value + ",";
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
		String searchSQL = SearchSQL();

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

	public List searchAndRetrieveList() {
		List<Object> retrieveList = null;
		String doSql = SearchSQL();
		//beanFillField();
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		boolean closeCon = false;

		try {
			retrieveList = new ArrayList<Object>();
			if(connection == null){
				connection = DbConnectionManager.getConnection();
				closeCon=true;
			}
			
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(doSql);
			
			rsmd = rs.getMetaData();
			Object bean;

			while (rs.next()) {
				bean = this.getClass().newInstance();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					ParamUtil.bindProperty(bean, ParamUtil.getFunName(rsmd.getColumnName(i + 1).toLowerCase()),
							rs.getString(i + 1), null);
				}
				retrieveList.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (connection != null && closeCon)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return retrieveList;
	}

	public int count() {
		return 0;
	}
	
	public void beanFillField(Class clazz){
		try {
			List<String> methods = ReflectUtil.getMethodNames(clazz);
			//setTable(clazz.getSimpleName());
			String method;
			String field;
			for (Iterator<String> it = methods.iterator(); it.hasNext();) {
				method = it.next();
				if(method.indexOf("get")==0){
					Method getMethod;
					getMethod = this.getClass().getMethod(method);
					Object obj = getMethod.invoke(this);
					if(obj !=null) {
						field = method.substring(method.indexOf("get")+3).toLowerCase();
						setField(field, obj.toString());
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
