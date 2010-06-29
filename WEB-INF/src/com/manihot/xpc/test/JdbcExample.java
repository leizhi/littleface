package com.manihot.xpc.test;


import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.manihot.xpc.jdbc.DbConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;

public class JdbcExample {
	@SuppressWarnings("unused")

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String sql = "";
		try {
/*
				 Class.forName("org.gjt.mm.mysql.Driver");
					// use proxool
                 con = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=true&amp;characterEncoding=utf8");
                 System.out.println("打开�?个连�?-------------");
                 System.out.println(con);
			con.setAutoCommit(false);
			stmt = con.createStatement();
			sql = "show tables";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Name=" + rs.getString(1));
			}
			con.commit();
*/

			con = DbConnectionManager.getConnection();
            System.out.println("打开连接-------------");
            System.out.println(con);
			con.setAutoCommit(false);
			//con.setCatalog("xpcBranch");
			stmt = con.createStatement();
			//sql = "select * from ClientJob";
			//sql = "show tables";
			sql = "SELECT  * FROM dm_xzqh_map_bak";
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();

			for(int i=0;i<rsmd.getColumnCount();i++){
				System.out.print(rsmd.getColumnName(i+1)+"\t");
			}
			System.out.println();

			while (rs.next() && rs.getRow()>0) {
				for(int i=0;i<rsmd.getColumnCount();i++){
					System.out.print(rs.getString(i+1)+"\t");
				}
				System.out.println();
			}
			con.commit();

/*
			// use mypool
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
*/
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
