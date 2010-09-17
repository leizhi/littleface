package com.mooo.mycoz.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mooo.mycoz.db.pool.DbConnectionManager;

public class DbUtil {

	public static List<?> search(String table) {
		List<String> searchList = null;
		Connection connection = null;

		try {
			searchList = new ArrayList<String> ();
			
			connection = DbConnectionManager.getConnection();
			DatabaseMetaData db =  connection.getMetaData();

			ResultSet result = db.getPrimaryKeys(null, null, table.toUpperCase());
			ResultSetMetaData rsmd = result.getMetaData();
			
			while (result.next()) {
				for (int i = 1; i < rsmd.getColumnCount()+1; i++) {
					System.out.println("Column"+i+"="+result.getString(i));
					//System.out.println("beanName="+StringUtils.prefixToUpperNot(result.getString(i)));
				}
				searchList.add(result.getString(4).toLowerCase());
				
				System.out.println();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException:" + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return searchList;
	}
}
