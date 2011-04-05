package com.mooo.mycoz.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.util.BeanUtil;

public class BeanTools {
	
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
		String columnName="";

		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			columnName = rsmd.getColumnName(i + 1).toLowerCase();
			System.out.print(columnName + ",");
		}
		System.out.println();
		
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
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BeanUtil bd = new BeanUtil();
		//bd.build("bus_remotes");
		//bd.build("bus_samples");
		//bd.build("marketmoniter","buffer_price");
		//bd.build("buffer_traffic");
		//bd.build("mycozBranch","Example");
		//bd.build("mycozBranch","FileInfo");
		//bd.build("mycozBranch","AccessLog");
		//bd.build("mycozBranch","User");
		//bd.build("mycozBranch","Account");
		//bd.build("mycozBranch","UserInfo");
		//bd.build("mycozShared","LinearCode");
		//bd.build("mycozShared","CodeType");
		//bd.build("mycozBranch","ForumThread");
		//bd.build("mycozBranch","Message");
		//bd.build("mycozBranch","AccessLog");
		//bd.build("mycozBranch","UserInfo");
		//bd.build("mycozBranch","AddressBook");
		//bd.build("mycozBranch","FileTree");
		//bd.build("mycozBranch","AuthGroup");
		//bd.build("mycozBranch","FileTree");
		//bd.build("mycozBranch","GroupMember");
		//bd.build("mycozBranch","RoleMember");

		
//		bd.build("mycozBranch","UserImage");
		//bd.buildInsert("buffer_traffic");

		//bd.build("mycozBranch","Forum");
		//bd.build("mycozBranch","ThreadType");
		//bd.build("mycozBranch","ForumThread");
		//bd.build("mycozShared","WeightUnit");
		//bd.build("mycozShared","Language");
		//bd.build("mycozShared","Country");
		//bd.build("mycozShared","City");
		//bd.build("mycozShared","Action");
		//bd.build("mycozShared","Method");
		//bd.build("mycozShared","AuthRole");
		//bd.build("mycozShared","AuthGroup");
		//bd.build("mycozShared","UserGroup");
		bd.dbToBean("mycozShared","UserRole");	
	}

}
