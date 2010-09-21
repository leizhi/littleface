package com.mooo.mycoz.tools.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.MysqlAction;
import com.mooo.mycoz.db.sql.SQLAction;
import com.mooo.mycoz.dbobj.mycozBranch.Example;
import com.mooo.mycoz.util.Transaction;

public class MultiThread {
	private static Log log = LogFactory.getLog(MultiThread.class);

	//private long maxLong = 9223372036854775807L;
	private int maxInt = 2147483647;

	//private double maxDouble = 1.79769313486231570e+308;
	
	private Thread[] threadPool;
	private Long[] threadCreateTime;
	private Integer[] threadLevel;
	
	private int maxConnMSec;
	//private int maxThread=800;
	//private int maxConnPool=25;

	//private Object initLock = new Object();
	
	public MultiThread(int maxConns,double maxConnTime) {

		threadPool = new Thread[maxConns];
		threadCreateTime = new Long[maxConns];
		threadLevel = new Integer[maxConns];
		
		Transaction tx = new Transaction();
		tx.start();
		tx.end();
		
		//maxConnMSec = (int) (maxConnTime * 86400000.0); // 86400 sec/day
		maxConnMSec = (int) (maxConnTime * 1000.0);		// loop 1 seconds.
		
		if (maxConnMSec < 30000) { // Recycle no less than 30 seconds.
			maxConnMSec = 30000;
		}
		
		System.out.println("^_^ 服务器启动  ^_^");
		long startTime = System.currentTimeMillis();
		System.out.println("startTime:"+ startTime);

		try {
			// create threadPool
			for (int i = 0; i < maxConns;i++) {
				threadPool[i] = new Thread(new DoThread());
				
				System.out.println("create thread["+i+"]");
				
				threadCreateTime[i] = System.currentTimeMillis();
				threadLevel[i] = 1;
				threadPool[i].start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("create threadPool error:"+e.getMessage());
		}
		
		try {
			boolean forever = true;
			
			while (forever) {
				
				for (int i= 0; i < maxConns; i++) {
					System.out.println("name \tPriority \tisAlive \tState \tisInterrupted \tisDaemon");
					System.out.println(threadPool[i].getName() + "\t" + threadPool[i].getPriority() +
							"\t" + threadPool[i].isAlive() + "\t" + threadPool[i].getState() + 
							"\t" + threadPool[i].isInterrupted() +
							"\t" + threadPool[i].isDaemon());
					
					long age = System.currentTimeMillis() - threadCreateTime[i];

					if (age > maxConnMSec) { // Force a reset at the max
						// thread time
						threadPool[i].interrupt();
						threadCreateTime[i] = 0L;
						threadLevel[i] = 0;

						System.out.println("===中断超时线程=====::::::"+i);

					}
				}
				
				try {
					int runCount=0;
					for (int i = 0; i < maxConns; i++) {
						if (threadLevel[i]>0) {
							runCount++;
						} else {
							threadLevel[i]++;
						}
					}
					
					System.out.println("=======================运行线程数===================:"+runCount);

					if(runCount == 0){
						for (int i = 0; i < maxConns; i++) {
								threadPool[i].interrupt();
						}
						
						break;
					}
					
					//wait(5000);
					Thread.sleep(60000); // sleep 60 seconds.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("=======================LOOP Watch===================");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception:"+e.getMessage());
		}
		long finishTime = System.currentTimeMillis();
		
		System.out.println("ex ms:"+(finishTime - startTime));
		
		System.out.println("^_^  服务器停止 ^_^");
	}

	public static void main(String[] args) throws IOException {
		new MultiThread(800,60);
		//single multin thread max 800
	}
	
	// --- DoThread
	class DoThread implements Runnable  {
		private Object initLock = new Object();
	    
		//private Transaction tx;
		private Connection conn;
		private Statement stmt;
		private SQLAction sa;
		
		public DoThread() throws SQLException{
			/*if(tx == null){
				synchronized (initLock) {
					tx = new Transaction();
					tx.start();
				}
			}
			*/
			
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
		
		public void run() {
			while (true) {
					try {
						
						Example ex = new Example();
						ex.setId(new Random().nextDouble() * maxInt);
						ex.setName(new Random().nextDouble() * maxInt + "名称");
						
						stmt.execute(sa.addSQL(ex));
						// Thread.sleep(50);
					} catch (SQLException e) {
						try {
							Thread.sleep(50);
						} catch (InterruptedException ie) {
							// TODO: handle exception
							ie.printStackTrace();
						}
						
						continue;
						
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
					//LOOP Run
				}
		}

	}
	
}
