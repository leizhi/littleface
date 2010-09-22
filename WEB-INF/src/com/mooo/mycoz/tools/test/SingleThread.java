package com.mooo.mycoz.tools.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.mooo.mycoz.db.DbAction;
import com.mooo.mycoz.db.DbOperation;
import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.MysqlAction;
import com.mooo.mycoz.db.sql.SQLAction;
import com.mooo.mycoz.dbobj.mycozBranch.Example;

public class SingleThread {

	public void writeJDBC(){
		Connection connection=null;
		Statement stmt = null;
		int maxInt = 2147483647;

		long startTime = System.currentTimeMillis();
		System.out.println("startTime:" + startTime);

		try {
			int maxConnMSec=0;
			double maxConnTime = 30d;
			
			maxConnMSec = (int) (maxConnTime * 3000.0);

			connection = DbConnectionManager.getConnection();
			stmt = connection.createStatement();
			//DBSession session = DBSession.getInstance();

	  		SQLAction sa = new MysqlAction();
			boolean forever = true;
			
			while (forever) {

				Example ex = new Example();
				ex.setId(new Random().nextDouble() * maxInt);
				ex.setName(new Random().nextDouble() * maxInt + "名称");

				String sql = sa.addSQL(ex);

				stmt.execute(sql);
				
				long age = System.currentTimeMillis() - startTime;
				if (age > maxConnMSec) { // Force a reset at the max
					break;
				}
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
	
	public void writeDbAction(){
		int maxInt = 2147483647;

		long startTime = System.currentTimeMillis();
		System.out.println("startTime:" + startTime);
		DbAction dbAction = new DbOperation();
		try {
			int maxConnMSec=0;
			
			double maxConnTime = 30d;
			
			maxConnMSec = (int) (maxConnTime * 3000.0);
				
			boolean forever = true;
			while (forever) {

				Example ex = new Example();
				ex.setId(new Random().nextDouble() * maxInt);
				ex.setName(new Random().nextDouble() * maxInt + "名称");
				
				dbAction.add(ex);
				long age = System.currentTimeMillis() - startTime;
				if (age > maxConnMSec) { // Force a reset at the max
					break;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("SQLException:"+e.getMessage());
		}

			long finishTime = System.currentTimeMillis();
			long hours = (finishTime - startTime) / 1000 / 60 / 60;
			long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
			long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
			
			System.out.println(finishTime - startTime);
			System.out.println("expends:   " + hours + ":" + minutes + ":" + seconds);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//SingleThread st = new SingleThread();
		//st.writeJDBC();
		//st.writeDbAction(); //0.01
	}

}
