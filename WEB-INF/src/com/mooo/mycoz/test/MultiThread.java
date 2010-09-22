package com.mooo.mycoz.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.DbAction;
import com.mooo.mycoz.db.DbFork;
import com.mooo.mycoz.db.DbOperation;
import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.MysqlAction;
import com.mooo.mycoz.db.sql.SQLAction;
import com.mooo.mycoz.dbobj.mycozBranch.Example;
import com.mooo.mycoz.util.Transaction;

public class MultiThread {
	private static Log log = LogFactory.getLog(MultiThread.class);

	//private static final long maxLong = 9223372036854775807L;
	private static final int maxInt = 2147483647;
	private static final int maxThread=800;
	//private static final double maxDouble = 1.79769313486231570e+308;
	
	private Thread[] threadPool;
	
	private int maxConnMSec;
	//private int maxConnPool=25;

	public MultiThread(int maxConns,double maxConnTime) {

		threadPool = new Thread[maxConns];
		
		Transaction tx = new Transaction();
		tx.start();
		tx.end();
		
		//maxConnMSec = (int) (maxConnTime * 86400000.0); // 86400 sec/day
		maxConnMSec = (int) (maxConnTime * 1000.0);		// loop 1 seconds.
		//maxConnMSec = (int) (maxConnTime * 3000.0);		// loop 1 seconds.

		if (maxConnMSec < 30000) { // Recycle no less than 30 seconds.
			maxConnMSec = 30000;
		}
		
		System.out.println("^_^ 服务器启动  ^_^");
		long startTime = System.currentTimeMillis();
		System.out.println("startTime:"+ startTime);

		try {
			//check init
			if(maxConns > maxThread)
				maxConns=maxThread;
			
			// create threadPool
			DoThread doThread = new DoThread();
			for (int i = 0; i < maxConns;i++) {
				
				//threadPool[i] = new Thread(doThread);
				threadPool[i] = new Thread(new DoThread());

				if(log.isDebugEnabled()) log.debug("create thread["+i+"]");
				threadPool[i].start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("create threadPool error:"+e.getMessage());
		}
		
		try {
			boolean forever = true;
			
			while (forever) {
				try {
					
//					System.out.println("name \tPriority \tisAlive \tState \tisInterrupted \tisDaemon");
//					System.out.println(threadPool[i].getName() + "\t" + threadPool[i].getPriority() +
//							"\t" + threadPool[i].isAlive() + "\t" + threadPool[i].getState() + 
//							"\t" + threadPool[i].isInterrupted() +
//							"\t" + threadPool[i].isDaemon());
					
					int runCount=0;
					
					for (int i= 0; i < maxConns; i++) {
						if(threadPool[i].isAlive())
							runCount++;
					}
					System.out.println("=======================运行线程数===================:"+runCount);
					
					if(runCount == 0){
						break;
					}
					
					//wait(5000);
					Thread.sleep(5000); // sleep 5 seconds.
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//System.out.println("=======================LOOP Watch===================");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception:"+e.getMessage());
		}
		long finishTime = System.currentTimeMillis();
		long hours = (finishTime - startTime) / 1000 / 60 / 60;
		long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
		long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
		
		System.out.println(finishTime - startTime);
		System.out.println("expends:   " + hours + ":" + minutes + ":" + seconds);
		
		System.out.println("^_^  服务器停止 ^_^");
	}

	public static void main(String[] args) throws IOException {
		new MultiThread(8,10);
	}
	
	class DoThread implements Runnable  {
		private Object initLock = new Object();
		
		private Connection connection;
		private Statement stmt;
		private long createTime;
		private String sql;
		private DbFork dbFork;
		private DbAction dbAction;
		
		public DoThread(){
			createTime = System.currentTimeMillis();
			dbFork = new DbFork();
			dbAction =  new DbOperation();
		}
		/*
		public void run() {
			boolean forever = true;
			while (forever) {
				try {
					synchronized (initLock) {
//						if(log.isDebugEnabled()) log.debug(Thread.currentThread().getName()+"打印 count:");
						
//						System.out.println("name \t\tPriority \t\tisAlive \tState \t\t\tisInterrupted \tisDaemon");
//						System.out.println(Thread.currentThread().getName() + "\t\t" + Thread.currentThread().getPriority() +
//							"\t\t" + Thread.currentThread().isAlive() + "\t\t" + Thread.currentThread().getState() + 
//								"\t\t" + Thread.currentThread().isInterrupted() +
//								"\t\t\t" + Thread.currentThread().isDaemon());

						//Example ex = new Example();
						//ex.setId(new Random().nextDouble() * maxInt);
						//ex.setName(new Random().nextDouble() * maxInt + "名称");
						sql = "INSERT INTO Example(id,name) VALUES(";
						sql += (new Random().nextDouble() * maxInt)+",";
						sql += "'"+(new Random().nextDouble() * maxInt) + "名称"+"'";
						sql += ")";
						
						System.out.println("AddSQL="+sql);

						//System.out.println("Connection:"+sa.getConnection());
						connection = DbConnectionManager.getConnection();
						stmt = connection.createStatement();
						//System.out.println("stmt:"+stmt);

						try {
							stmt.execute(sql);
						} catch (SQLException e) {
							e.printStackTrace();
							
							Thread.sleep(20); // do other
							continue;
						}
						
						long age = System.currentTimeMillis() - createTime;
						if (age > maxConnMSec) { // Force a reset at the max
							System.out.println("===超时 退出=====");
							break;
						}
						//LOOP Run
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					System.out.println("异常退出..");
					break;
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
			}
		}
	}
	*/
		public void run() {
			boolean forever = true;
			while (forever) {
				try {
					synchronized (initLock) {
//						if(log.isDebugEnabled()) log.debug(Thread.currentThread().getName()+"打印 count:");
						
//						System.out.println("name \t\tPriority \t\tisAlive \tState \t\t\tisInterrupted \tisDaemon");
//						System.out.println(Thread.currentThread().getName() + "\t\t" + Thread.currentThread().getPriority() +
//							"\t\t" + Thread.currentThread().isAlive() + "\t\t" + Thread.currentThread().getState() + 
//								"\t\t" + Thread.currentThread().isInterrupted() +
//								"\t\t\t" + Thread.currentThread().isDaemon());
						
						Example ex = new Example();
						ex.setId(new Random().nextDouble() * maxInt);
						ex.setName(new Random().nextDouble() * maxInt + "名称");
						
						try {
							//dbFork.add(ex);
							dbAction.add(ex);
						} catch (SQLException e) {
							e.printStackTrace();
							
							Thread.sleep(20); // do other
							continue;
						}
						
						long age = System.currentTimeMillis() - createTime;
						if (age > maxConnMSec) { // Force a reset at the max
							System.out.println("===超时 退出=====");
							break;
						}
						//LOOP Run
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					System.out.println("异常退出..");
					break;
				}
			} // while
		} // end run
		
	}

}
