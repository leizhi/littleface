package com.mooo.mycoz.tools.test;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.dbobj.mycozBranch.Example;

public class MultiThread {
	private static Log log = LogFactory.getLog(MultiThread.class);

	private List<Thread> threadPool = new ArrayList<Thread>();
	private List<Long> threadCreateTime = new ArrayList<Long>();

	private int maxConnMSec;
	
	public MultiThread(int maxConns,double maxConnTime) {

		maxConnMSec = (int) (maxConnTime * 86400000.0); // 86400 sec/day
		//maxConnMSec = (int) (maxConnTime * 3000.0);		// loop seconds.
		
		if (maxConnMSec < 30000) { // Recycle no less than 30 seconds.
			maxConnMSec = 30000;
		}
		
		System.out.println("^_^ 服务器启动  ^_^");

		try {
			
			boolean forever = true;
			int init=0;

			while (forever) {
				
				while(init<maxConns){
					synchronized (threadPool) {
						threadPool.add(init, new Thread(new DoThread(init)));
						threadCreateTime.add(init, System.currentTimeMillis());
						threadPool.get(init).start();
					}
					init++;
				}
				
				maxConns = threadPool.size();
				
				if(maxConns < 1)
					break;
				
				for (int c = 0; c < maxConns; c++) {
					long age = System.currentTimeMillis() - threadCreateTime.get(c);
					
					Thread thread = (Thread) threadPool.get(c);
					/*System.out.println("name \tPriority \tisAlive \tState \tisInterrupted \tisDaemon");

					System.out.println(thread.getName() + "\t" + thread.getPriority() +
							"\t" + thread.isAlive() + "\t" + thread.getState() + 
							"\t" + thread.isInterrupted() +
							"\t" + thread.isDaemon());
*/
					if (age > maxConnMSec) { // Force a reset at the max
						// thread time
						threadPool.remove(c);
						threadCreateTime.remove(c);
						
						thread.interrupt();
						System.out.println("===中断超时线程=====::::::"+c);

					}
				}
				
				try {
					System.out.println("=======================运行线程数===================:"+threadPool.size());
					//wait(5000);
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception:"+e.getMessage());
		}
		
		System.out.println("^_^  服务器停止 ^_^");
	}

	public static void main(String[] args) throws IOException {
		new MultiThread(19000,0.5);
	}
	// --- DoThread
	class DoThread implements Runnable  {
		//private Object initLock = new Object();
		private int i;
			
		public DoThread(int i){
			this.i=i;
			if(log.isDebugEnabled())log.debug("线程"+i+"启动..");
		}
		
		public void run() {

			while (true) {
				try {
					doWrite();

					//wait(80);
					Thread.sleep(80);
				} catch (InterruptedException e) {
					e.printStackTrace();
					
					threadPool.remove(i);
					threadCreateTime.remove(i);
					
					break;
				}
				
			}

	}
		public void doWrite(){
			try {
				long startTime = System.currentTimeMillis();

				Example ex = new Example();
				ex.setId(new Random().nextInt(2147483647));
				ex.setName(new Random().nextInt(2147483647)+"");
				ex.add();

				long finishTime = System.currentTimeMillis();
				long hours = (finishTime - startTime) / 1000 / 60 / 60;
				long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
				long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
				
				System.out.println(finishTime - startTime);
				System.out.println("expends:   " + hours + ":" + minutes + ":" + seconds);
			}catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQLException:"+e.getMessage());
			}
		}
		
		public void doSearch(){
			Connection connection=null;
			Statement stmt = null;
			ResultSet result = null;
			try {
				long startTime = System.currentTimeMillis();

				connection = DbConnectionManager.getConnection();
				
				stmt = connection.createStatement();
				
				Example ex = new Example();
				//ex.setTable("Example");
				ex.searchSQL();
				
				//result = stmt.executeQuery(ex.searchSQL()+" LIMIT 20");
				result = stmt.executeQuery(ex.searchSQL());

				while(result.next()){
					System.out.println("id="+result.getString("id")+" name="+result.getString("name"));
				}
				
				long finishTime = System.currentTimeMillis();
				long hours = (finishTime - startTime) / 1000 / 60 / 60;
				long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
				long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
				
				System.out.println(finishTime - startTime);
				System.out.println("expends:   " + hours + ":" + minutes + ":" + seconds);
			}catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQLException:"+e.getMessage());
			}finally {
				/*
				try {
					System.out.println("finally befon");
					
					System.out.println("result:"+result.isClosed());
					System.out.println("stmt:"+stmt.isClosed());
					System.out.println("connection:"+connection.isClosed());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				*/
				
				try {
					if (result != null) {
						result.close();
						result = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

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
	/*		
			try {
				System.out.println("end");

				System.out.println("result:"+result.isClosed());
				//System.out.println("stmt:"+stmt.isClosed());
				//System.out.println("connection:"+connection.isClosed());
			} catch (SQLException e) {
				e.printStackTrace();
			}
*/
		}
	}
	
}
