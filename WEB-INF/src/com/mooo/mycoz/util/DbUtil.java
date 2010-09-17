package com.mooo.mycoz.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mooo.mycoz.db.pool.DbConnectionManager;

public class DbUtil {

	public static List<?> search(String table) {
		List<String> searchList = null;
		Connection connection = null;

		try {
			searchList = new ArrayList<String>();

			connection = DbConnectionManager.getConnection();

			ResultSet result = connection.getMetaData().getPrimaryKeys(null,null, table.toUpperCase());
			
			while (result.next()) {
				searchList.add(result.getString(4).toLowerCase());
			}
			
			result.close();
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("NullPointerException :"+e.getMessage());
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
