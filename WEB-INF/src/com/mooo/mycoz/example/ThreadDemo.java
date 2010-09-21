package com.mooo.mycoz.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.MysqlAction;
import com.mooo.mycoz.db.sql.SQLAction;

public class ThreadDemo{

	public ThreadDemo () throws SQLException{
		DoThread runThread = new DoThread();
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(runThread);
			t.start();
		}
	}
	public static void main(String[] args) {
		try {
			new ThreadDemo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class DoThread implements Runnable  {
		//private int i = 0;
		private Object initLock = new Object();
	    
		private int i;
		private Connection conn;
		private Statement stmt;
		private SQLAction sa;
		private int count = 0;

		public DoThread() throws SQLException{
			if(conn == null){
				synchronized (initLock) {
					conn = DbConnectionManager.getConnection();
				}
			}
			
			if(sa == null){
				synchronized (initLock) {
					sa = new MysqlAction();
				}
			}
			
			if(stmt == null){
				synchronized (initLock) {
					stmt = conn.createStatement();
				}
			}
		}
		public DoThread(int i) throws SQLException{
			this.i=i;
			
			if(conn == null){
				synchronized (initLock) {
					conn = DbConnectionManager.getConnection();
				}
			}
			
			if(sa == null){
				synchronized (initLock) {
					sa = new MysqlAction();
				}
			}
			
			if(stmt == null){
				synchronized (initLock) {
					stmt = conn.createStatement();
				}
			}
			
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(this + ":" + ++count);
		}
		
	}
}
