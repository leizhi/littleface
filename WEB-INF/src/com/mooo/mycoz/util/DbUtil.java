package com.mooo.mycoz.util;

import java.sql.Connection;
import java.sql.ResultSet;
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
	
	public static List<?> primaryKey(String table) {
		
		List<String> retrieveList = null;
		Statement stmt = null;
		ResultSet result = null;
		boolean closeCon = false;
		Connection connection = null;
		
		try {
			retrieveList = new ArrayList<String>();

			if(connection == null || connection.isClosed()){
				connection = DbConnectionManager.getConnection();
				closeCon=true;
			}

			result = connection.getMetaData().getPrimaryKeys(null,null, table.toUpperCase());
			
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
				if (connection != null && closeCon)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return retrieveList;

	}
}
