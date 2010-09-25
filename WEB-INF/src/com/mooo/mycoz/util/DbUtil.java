package com.mooo.mycoz.util;

import java.sql.Connection;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mooo.mycoz.db.pool.DbConnectionManager;

public class DbUtil {
	public static HashMap<String,List<String>> primaryKeys;
	
	public static boolean isPrimaryKey(String table,String field){
		try {
			if (primaryKeys.containsKey(table)) {
				if (primaryKeys.get(table).contains(field))
					return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//return false;
		return true;
	}
	
	public static List<?> primaryKey(Connection connection,String table) {
		
		List<String> retrieveList = null;
		
		Connection myConn = null;
		boolean isClose = true;
		
		Statement stmt = null;
		ResultSet result = null;
		try {
			retrieveList = new ArrayList<String>();

			if(connection != null){
				myConn = connection;
				isClose = false;
			} else {
				myConn = DbConnectionManager.getConnection();
				isClose = true;
			}

			result = myConn.getMetaData().getPrimaryKeys(null,null, table.toUpperCase());
			
			while (result.next()) {
				retrieveList.add(result.getString(4).toLowerCase());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (result != null)
					result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(isClose)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return retrieveList;

	}
	
	public static int type(Connection connection,String catalog,String table,String column) {
		
		Connection myConn = null;
		boolean isClose = true;
		
		Statement stmt = null;
		ResultSet result = null;
		//ResultSetMetaData rsmd = null;

		try {
			if(connection != null){
				myConn = connection;
				isClose = false;
			} else {
				myConn = DbConnectionManager.getConnection();
				isClose = true;
			}

			//result = myConn.getMetaData().getColumns(null, schem, "FileInfo","name");
			result = myConn.getMetaData().getColumns(catalog, null, table,column);
			//rsmd = result.getMetaData();
			//String columnName="";

			while (result.next()) {
				/*
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					columnName = rsmd.getColumnName(i + 1).toLowerCase();
					System.out.println(columnName + "="+result.getString(columnName));
				}*/
				//System.out.println(result.getInt(5));

				return result.getInt(5);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (result != null)
					result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(isClose)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return 0;
	}
}
