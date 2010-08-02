package com.mooo.mycoz.dbobj;

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
import com.mooo.mycoz.db.sql.DbMultiBulildSQL;
import com.mooo.mycoz.db.sql.DbMultiSql;
import com.mooo.mycoz.util.ParamUtil;
import com.mooo.mycoz.util.ReflectUtil;

public class MultiDBObject extends DbMultiBulildSQL implements DbMultiSql{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4716776899444767709L;
	public Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {

		this.connection = connection;
	}
	

	public List searchAndRetrieveList() {
		List<Object> retrieveList = null;
		String doSql = searchSQL();
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
			Map re;

			String key;
			String value;
			Class cls;
			Object bean;
			String catalog,table,column;

			while (rs.next()) {
				re = new HashMap();
				
				for (Iterator<?> it = objs.keySet().iterator(); it.hasNext();) {
					key = (String)it.next();
					cls = objs.get(key);
					bean = cls.newInstance();

					for (int i=1; i < rsmd.getColumnCount()+1; i++) {
						catalog = rsmd.getCatalogName(i);
						table = rsmd.getTableName(i);
						
						value = tables.get(key);
						
						if(value.equals(catalog+"."+table)){
							column = rsmd.getColumnName(i).toLowerCase();
							ParamUtil.bindProperty(bean, ParamUtil.getFunName(column),
									rs.getString(i), null);
						}
					}
					re.put(key, bean);
				}
				retrieveList.add(re);
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
