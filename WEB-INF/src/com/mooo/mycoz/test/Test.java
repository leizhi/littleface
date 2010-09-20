package com.mooo.mycoz.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.mooo.mycoz.db.DbOperation;
import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.dbobj.mycozBranch.Example;

public class Test {

	public static void main(String[] args) {
		//new Test().searchSql();
		Connection conn = null;
	      try {
	    	  		conn = DbConnectionManager.getConnection();
	            DatabaseMetaData meta = conn.getMetaData();
	            ResultSet rs = meta.getColumns(null, null, "Example", null);
	            while (rs.next()) {
	                System.out.println(rs.getString(1));
	                System.out.println(rs.getString(2));
	                System.out.println(rs.getString(3));
	                System.out.println(rs.getString(4));
	                System.out.println(rs.getString(5));
	                System.out.println(rs.getString(6));
	                System.out.println(rs.getString(7));
	                System.out.println(rs.getString(8));
	                System.out.println(rs.getString(9));
	                System.out.println(rs.getString(10));
	                System.out.println("-----db2--------");
	            }
	        } catch (Exception ex) {
	            System.out.println(ex);
	        } finally {
	            try {
	                conn.close();
	            } catch (Exception ex) {

	            }
	        }
		/*long maxLong = 9223372036854775807L;
		int MaxInt = 2147483647;

		try {
			DbOperation session = DbOperation.getInstance();
			//session.setConnection(tx.getConnection());
			
			Example ex = new Example();
			ex.setId(new Random().nextDouble() * MaxInt);
			ex.setName(new Random().nextDouble() * MaxInt+"名称");
			session.add(ex);
			
			//DbOperation factory = DbOperation.getInstance();
			//DbOperation factory = DbOperation.getInstance();
			//List reList = de.searchAndRetrieveList(ex);
			
			System.out.println("sql:"+ex.searchSQL());

			List reList = ex.searchAndRetrieveList();

			Iterator it = reList.iterator();
			Example bean;
			while (it.hasNext()) {
				bean = (Example) it.next();
				System.out.println("name:"+bean.getName()+" id:"+bean.getId());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		//System.out.println(com.mooo.mycoz.util.IDGenerator.getNowTime());
		//System.out.println(com.mooo.mycoz.util.IDGenerator.getNow());
		//System.out.println(com.mooo.mycoz.util.IDGenerator.getNowYear());
	}
}