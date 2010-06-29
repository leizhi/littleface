package com.mooo.mycoz.sockt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.manihot.xpc.jdbc.DbConnectionManager;

public class Service {

	public String list(){
		String value="";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = DbConnectionManager.getConnection();
			con.setAutoCommit(false);
			// con.setCatalog("xpcBranch");
			stmt = con.createStatement();
			sql = "SELECT  * FROM dm_xzqh_map_bak";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				value +="XXXX"+rs.getString("XZQH_MC");
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("Exception: " + e.getMessage());

		} finally {

			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	public String doIt(String sql){
		String value="";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DbConnectionManager.getConnection();
			con.setAutoCommit(false);
			// con.setCatalog("xpcBranch");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				value +="XXXX"+rs.getString("XZQH_MC");
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("Exception: " + e.getMessage());

		} finally {

			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
}
