package com.mooo.mycoz.tools.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.dbobj.mycozBranch.Example;

public class SingleThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection=null;
		Statement stmt = null;
		
		long startTime = System.currentTimeMillis();
		System.out.println("startTime:" + startTime);

		try {


			connection = DbConnectionManager.getConnection();
			stmt = connection.createStatement();
			
			Example ex = new Example();
			//long lo = 9223372036854775807L;
			for(int i=0;i<2147483647 ;i++) {
				ex.setId(i*1d);
				ex.setName(i + "丑鬼");
				try {
					stmt.execute(ex.addSQL());
				}catch (SQLException e) {
					
				}
			/*
			while (true) {
				ex.setId(new Random().nextInt(65535));
				ex.setName(new Random().nextInt(65535) + "");
				try {
					stmt.execute(ex.addSQL());
					
				}catch (SQLException e) {
					
				}
			*/
			

			
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("SQLException:"+e.getMessage());
		}finally {

			try {
				if (stmt != null){
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

			long finishTime = System.currentTimeMillis();
			long hours = (finishTime - startTime) / 1000 / 60 / 60;
			long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
			long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
			
			System.out.println(finishTime - startTime);
			System.out.println("expends:   " + hours + ":" + minutes + ":" + seconds);
	}

}
