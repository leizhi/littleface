package com.mooo.mycoz.dbobj;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.DbMultiBulildSQL;
import com.mooo.mycoz.util.ParamUtil;
import com.mooo.mycoz.util.ReflectUtil;

public class MultiDBObject extends DbMultiBulildSQL{
	
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
	
	public List<Object> searchAndRetrieveList(String sql, Class<?> obj) {
		List<Object> retrieveList = null;
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
			ResultSet rs = stmt.executeQuery(sql);
			
			rsmd = rs.getMetaData();
			Object bean;

			while (rs.next()) {
				bean = obj.newInstance();
				
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
				if (connection != null && closeCon)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return retrieveList;
	}

	public List<Object> searchAndRetrieveList(String sql) {
		List<Object> retrieveList = null;
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
			ResultSet rs = stmt.executeQuery(sql);
			
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
	
	public List<Object> searchAndRetrieveList(){
		long startTime = System.currentTimeMillis();
		long finishTime = System.currentTimeMillis();
		
		List<Object> retrieveList = null;
		String doSql = SearchSQL();

		beanFillField();
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		boolean closeCon = false;

		try {
			retrieveList = new ArrayList<Object>();
			System.out.println("do searchAndRetrieveList can1:"+(System.currentTimeMillis() - finishTime));
			finishTime = System.currentTimeMillis();
			
			System.out.println("do searchAndRetrieveList connection:"+connection);
			
			if(connection == null){
				connection = DbConnectionManager.getConnection();
				closeCon=true;
			}
			
			stmt = connection.createStatement();
			System.out.println("do searchAndRetrieveList can2:"+(System.currentTimeMillis() - finishTime));
			finishTime = System.currentTimeMillis();
			
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
			//addCache(doSql, retrieveList);
			
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
		System.out.println("do searchAndRetrieveList end:"+(System.currentTimeMillis() - startTime));

		return retrieveList;
	}
	
	public void beanFillField(){
		try {
			List<String> methods = ReflectUtil.getMethodNames(this.getClass());
			//setTable(this.getClass().getSimpleName());
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
