package com.mooo.mycoz.test;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.dbobj.mycozBranch.Example;
import com.mooo.mycoz.util.Transaction;

public class MultiThread  {
	private static Log log = LogFactory.getLog(MultiThread.class);

	private List<Thread> threader = new ArrayList<Thread>();
	
	private Object initLock = new Object();

	public int count(){
		synchronized (initLock) {
			return threader.size();
		}
	}

	public MultiThread() {

		if(log.isDebugEnabled()) log.debug("server start ok");
		
		System.out.println("服务器启动");
		int max = 200;
		//int min = 10;

		try {
			int i=0;
			
			while (true) {
				
				while(i<max){
					threader.add(i, new Thread(new DoThread(i)));
					threader.get(i).start();
					i++;
				}
				
				try {
					System.out.println("主线程睡了...");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} finally {
			
		}
		
	}

	public static void main(String[] args) throws IOException {
		new MultiThread();
	}

	// --- DoThread
	class DoThread implements Runnable  {
		private Object initLock = new Object();
		private int i;
		
		public DoThread(){
			System.out.println("线程启动..");
		}
		
		public DoThread(int i){
			this.i=i;
			System.out.println("线程"+i+"启动..");
		}
		
		public void run() {
			synchronized (initLock) {
				while(true){
					System.out.println("线程"+i+"执行");
					doIt();
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		public void doIt(){
			Transaction tx = new Transaction();
			try {
				tx.start();
				
				Example ex = new Example();
				ex.setConnection(tx.getConnection());
				long startTime = System.currentTimeMillis();
				//Runtime rt = Runtime.getRuntime();

				//for(int i=0;i<100000;i++){
				ex.setId((new Random().nextInt())*1d);
				ex.setName("日本女"+new Random().nextInt());
				ex.add();
					
					//System.out.println("add i:"+i);
					//System.out.println("Total Memory:" + rt.totalMemory()+ " Free Memory:" + rt.freeMemory());
				//}
				
				System.out.println("==============================");

				tx.commit();
				
				long finishTime = System.currentTimeMillis();
				long hours = (finishTime - startTime) / 1000 / 60 / 60;
				long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
				long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
				
				System.out.println(finishTime - startTime);
				System.out.println("expends:   " + hours + ":" + minutes + ":" + seconds);
			} catch (SQLException e) {
				System.out.println("SQLException:"+e.getMessage());
				e.printStackTrace();
				tx.rollback();
			} catch (RuntimeException e) {
				System.out.println("RuntimeException:"+e.getMessage());
				e.printStackTrace();
				tx.rollback();
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} catch (Throwable e) {
				System.out.println("Throwable:"+e.getMessage());
				e.printStackTrace();
				tx.rollback();
			}finally {
				tx.end();
				System.out.println("tx end");
			}
		}
	}

}
