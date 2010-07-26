package com.mooo.mycoz.db.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mooo.mycoz.db.pool.*;

public class DbSession {
	private static Object initLock = new Object();
	private static DbSession factory = null;

	private static Connection con;
	private static String catalog;
	private static PreparedStatement pstmt;
	private static ResultSet resultSet;

	private DbSession() {
		con=null;
		catalog=null;
		pstmt=null;
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
