package com.mooo.mycoz.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mooo.mycoz.db.pool.DbConnectionManager;

public class BuilDbobj {
	
	public void build(String table){
		Connection con = null;
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		String sql = "";
		try {

		ResultSet rs = null;
		//mypool
		con = DbConnectionManager.getConnection();
		System.out.println("打开连接-------------");
		System.out.println(con);
		
		sql = "SELECT  * FROM "+table;
		System.out.println(sql);

		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		rsmd = rs.getMetaData();
		
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			System.out.println(rsmd.getColumnTypeName(i+1).toLowerCase()+" "+ rsmd.getColumnName(i + 1).toLowerCase() + ";\t");
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void buildInsert(String table){
		Connection con = null;
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		String sql = "";
		try {

		ResultSet rs = null;
		//mypool
		con = DbConnectionManager.getConnection();
		System.out.println("打开连接-------------");
		System.out.println(con);
		
		sql = "SELECT  * FROM "+table;
		System.out.println(sql);

		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		rsmd = rs.getMetaData();
		
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			System.out.print(rsmd.getColumnName(i + 1).toLowerCase() + ",");
		}
		System.out.println();
		
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BuilDbobj bd = new BuilDbobj();
		//bd.build("bus_remotes");
		bd.buildInsert("buffer_traffic");

	}

}
