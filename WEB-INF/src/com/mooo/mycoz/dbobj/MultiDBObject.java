package com.mooo.mycoz.dbobj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.DbBulildSQL;
import com.mooo.mycoz.util.ParamUtil;

public class MultiDBObject extends DbBulildSQL{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7029434178845389537L;
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
		ResultSet rs = null;
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
			
			rs = stmt.executeQuery(sql);
			
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
				if (rs != null)
					rs.close();
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
		ResultSet rs = null;
		try {
			retrieveList = new ArrayList<Object>();
			
			if(connection!=null){
				stmt = connection.createStatement();
			}else{
				if(conn==null)
				conn=DbConnectionManager.getConnection();
				stmt = conn.createStatement();
			}
			
			rs = stmt.executeQuery(sql);
			
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
				if (rs != null)
					rs.close();
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
	
	public List<Object> searchAndRetrieveList() {
		List<Object> retrieveList = null;
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		ResultSet rs = null;
		try {
			retrieveList = new ArrayList<Object>();
			
			if(connection!=null){
				stmt = connection.createStatement();
			}else{
				if(conn==null)
				conn=DbConnectionManager.getConnection();
				stmt = conn.createStatement();
			}
			
			rs = stmt.executeQuery(SearchSQL());
			
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
				if (rs != null)
					rs.close();
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
	
	public void retrieve(){
		
	}
	
}
