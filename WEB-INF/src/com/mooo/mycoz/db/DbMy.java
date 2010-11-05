package com.mooo.mycoz.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.MySQL;
import com.mooo.mycoz.db.sql.MysqlSQL;
import com.mooo.mycoz.util.BeanUtil;
import com.mooo.mycoz.util.StringUtils;

public class DbMy extends MySQL {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Object>  searchAndRetrieveList(Connection connection,String catalog,String table,Map<String,Object> maps)
			throws SQLException {
		
		List<Object> retrieveList = null;
		String doSql = querySQL(catalog,table,maps);
		
		System.out.println("searchSQL:" + doSql);

		Connection myConn = null;
		boolean isClose = true;

		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		ResultSet result = null;

		try {
			retrieveList = new ArrayList<Object>();
			
			if(connection != null){
				myConn = connection;
				isClose = false;
			} else {
				myConn = DbConnectionManager.getConnection();
				isClose = true;
			}

			stmt = myConn.createStatement();
			result = stmt.executeQuery(doSql);

			rsmd = result.getMetaData();
			int type=0;
			
			Map row;
			String key;
			
			while (result.next()) {
				row = new HashMap();
				
				for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
					type = rsmd.getColumnType(i);
					key = rsmd.getColumnName(i);
					
					if(type == Types.TIMESTAMP){
						row.put(key, result.getTimestamp(i));
					}else {
						row.put(key, result.getString(i));
					}
				}
				retrieveList.add(row);
			}
			// addCache(doSql, retrieveList);
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

	public void save(Connection connection, String catalog, String table,
			Map<String, Object> maps) throws SQLException {

		String doSql = addSQL(catalog, table, maps);

		Connection myConn = null;
		boolean isClose = true;

		Statement stmt = null;

		try {
			if (connection != null) {
				myConn = connection;
				isClose = false;
			} else {
				myConn = DbConnectionManager.getConnection();
				isClose = true;
			}

			stmt = myConn.createStatement();
			stmt.execute(doSql);
			// addCache(doSql, retrieveList);
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
				if (isClose)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}