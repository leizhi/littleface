package com.manihot.xpc.tools;

import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.manihot.xpc.jdbc.DbConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExample {
	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(JdbcExample.class);

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
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
				System.out.println("Name=" + rs.getString("XZQH_MC"));
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			System.out.println("Exception: " + e.getMessage());

		} finally {
			rs.close();
			stmt.close();
			con.close();
		}
	}

}
