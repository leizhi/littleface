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
import com.mooo.mycoz.db.sql.DbBulildSQL;
import com.mooo.mycoz.util.ParamUtil;
import com.mooo.mycoz.util.ReflectUtil;

public class DBObject extends DbBulildSQL{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4716776899444767709L;
	public Connection connection;
	public Connection conn;

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
		try {
			retrieveList = new ArrayList<Object>();
			
			if(connection!=null){
				stmt = connection.createStatement();
			}else{
				if(conn==null)
				conn=DbConnectionManager.getConnection();
				stmt = conn.createStatement();
			}
			
			ResultSet rs = stmt.executeQuery(sql);
			
			rsmd = rs.getMetaData();
			Object bean;

			while (rs.next()) {
				bean = obj.newInstance();
				
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					//System.out.println("ColumnTypeName=" + rsmd.getColumnTypeName(i+1));
					ParamUtil.bindProperty(bean, ParamUtil.getFunName(rsmd.getColumnName(i + 1).toLowerCase()),
							rs.getString(i + 1), null);
					//System.out.println(rsmd.getColumnName(i + 1).toLowerCase()+"="+ rs.getString(i + 1));
				}
				//System.out.println("name="+((Download)bean).getName());
				retrieveList.add(bean);
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
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
		try {
			retrieveList = new ArrayList<Object>();
			
			if(connection!=null){
				stmt = connection.createStatement();
			}else{
				if(conn==null)
				conn=DbConnectionManager.getConnection();
				stmt = conn.createStatement();
			}
			
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
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return retrieveList;
	}
	
	public List<Object> searchAndRetrieveList() throws SQLException{
		beanFillField();

		List<Object> retrieveList = null;
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		try {
			retrieveList = new ArrayList<Object>();
			
			if(connection!=null){
				stmt = connection.createStatement();
			}else{
				if(conn==null)
				conn=DbConnectionManager.getConnection();
				stmt = conn.createStatement();
			}
			
			ResultSet rs = stmt.executeQuery(SearchSQL());
			
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
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return retrieveList;
	}
	
	public void add() throws SQLException {
		beanFillField();

		Statement stmt = null;
		try{
			if(connection!=null){
				stmt = connection.createStatement();
			}else{
				if(conn==null)
				conn=DbConnectionManager.getConnection();
				stmt = conn.createStatement();
			}
			stmt.execute(AddSQL());
		}finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	public void delete() throws SQLException {
		beanFillField();
		Statement stmt = null;
		try{
			if(connection!=null){
				stmt = connection.createStatement();
			}else{
				if(conn==null)
				conn=DbConnectionManager.getConnection();
				stmt = conn.createStatement();
			}
			stmt.execute(DeleteSQL());

		}finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void update() throws SQLException{
		beanFillField();

		Statement stmt = null;
		try{
			if(connection!=null){
				stmt = connection.createStatement();
			}else{
				if(conn==null)
				conn=DbConnectionManager.getConnection();
				stmt = conn.createStatement();
			}
			stmt.execute(UpdateSQL());
		}finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void beanFillField(){
		try {
			List<String> methods = ReflectUtil.getMethodNames(this.getClass());
			setTable(this.getClass().getSimpleName());
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

	public void retrieve() throws SQLException{
		beanFillField();
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		try{
			connection = DbConnectionManager.getConnection();
			if(connection!=null){
				stmt = connection.createStatement();
			}else{
				if(conn==null)
				conn=DbConnectionManager.getConnection();
				stmt = conn.createStatement();
			}

			ResultSet rs = stmt.executeQuery(SearchSQL());
			
			rsmd = rs.getMetaData();

			while (rs.next()) {
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					ParamUtil.bindProperty(this, ParamUtil.getFunName(rsmd.getColumnName(i + 1).toLowerCase()),
							rs.getString(i + 1), null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();		
		}finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
}
