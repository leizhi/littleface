package com.mooo.mycoz.db.sql;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import com.mooo.mycoz.db.pool.*;

public class DbSession {
	private static Object initLock = new Object();
	private static DbSession factory = null;

	private static Connection con;
	private static String catalog;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet resultSet;

	private DbSession() {
		// initLock=null;
		// factory=null;

		// con=null;
		// catalog=null;
		// pstmt=null;
	}

	public static DbSession getInstance() {
		if (factory == null) {
			synchronized (initLock) {
				try {
					factory = new DbSession();
				} catch (Exception e) {
					System.err.println("Exception DbSession." + e.getMessage());
					e.printStackTrace();
					return null;
				}
			}
		}
		return factory;
	}

	public static void setDatabase(String database) {
		catalog = database;
	}

	public static void execute(String sql) {

		try {
			con = DbConnectionManager.getConnection();
			if (catalog != null)
				con.setCatalog(catalog);

			pstmt = con.prepareStatement(sql);
			pstmt.execute();
		} catch (SQLException sqle) {
			System.err.println("Exception in DbAuthorizationFactory:" + sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}
	}

	public static void executeQuery(String sql) {
		try {
			con = DbConnectionManager.getConnection();
			if (catalog != null)
				con.setCatalog(catalog);
				
			pstmt = con.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
		} catch (SQLException sqle) {
			System.err.println("Exception in DbSeesion:" + sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}
		
	}

	public static int count(String sql) {
		int count = 0;
		try {
			con = DbConnectionManager.getConnection();
			if (catalog != null)
				con.setCatalog(catalog);
				
			pstmt = con.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			
			resultSet.first();
			count = resultSet.getInt(1);
			
		} catch (SQLException sqle) {
			System.err.println("Exception in DbSeesion:" + sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}
		return count;
	}
	
	public static List searchAndRetrieveList(String sql) {
		List resultList = new LinkedList();
		try {
					con = DbConnectionManager.getConnection();
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					resultSet = stmt.executeQuery(sql);
					
					int n = resultSet.getMetaData().getColumnCount();
					while ( resultSet.next() ) {
						Map map = new HashMap();
						for (int i = 1; i <= n; i++) {					
								map.put(resultSet.getMetaData().getCatalogName(i), resultSet.getObject(i));
							}
						resultList.add(map);
					}
		} catch (SQLException sqle) {
			System.err.println("Exception in DbSeesion:" + sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}
		return resultList;
	}
	
		public static ResultSet getResultSet() {

		return resultSet;
	}
	
	public static void close() {
		try {
			if (resultSet != null)
				resultSet.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException sqle) {
			System.err.println("Exception in DbSeesion:" + sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}
	}
}
