package com.mooo.mycoz.tools.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.dbobj.DBSession;
import com.mooo.mycoz.dbobj.mycozBranch.Example;
import com.mooo.mycoz.util.Transaction;

public class MultiThread {
	private static Log log = LogFactory.getLog(MultiThread.class);

	private long maxLong = 9223372036854775807L;
	//private double maxDouble = 1.79769313486231570e+308;
	
	private Thread[] threadPool;
	private Long[] threadCreateTime;
	private Integer[] threadLevel;
	
	private int maxConnMSec;
	
	public MultiThread(){
		long startTime = System.currentTimeMillis();
		new DoThread(0).writeTransaction();
		long finishTime = System.currentTimeMillis();
		System.out.println("ex ms:"+(finishTime - startTime));
	}
	
	public MultiThread(int maxConns,double maxConnTime) {

		threadPool = new  Thread[maxConns];
		threadCreateTime = new Long[maxConns];
		threadLevel = new Integer[maxConns];
		
		Transaction tx = new Transaction();
		tx.start();
		tx.end();
		
		maxConnMSec = (int) (maxConnTime * 86400000.0); // 86400 sec/day
		//maxConnMSec = (int) (maxConnTime * 3000.0);		// loop seconds.
		
		if (maxConnMSec < 30000) { // Recycle no less than 30 seconds.
			maxConnMSec = 30000;
		}
		
		System.out.println("^_^ 服务器启动  ^_^");

		try {
			// create threadPool
			
			for (int i = 0; i < maxConns; i++) {
				threadPool[i] = new Thread(new DoThread(i));
				threadCreateTime[i] = System.currentTimeMillis();
				threadLevel[i] = 1;

				threadPool[i].start();
				System.out.println("===启动线程=====::::::" + i);

				Thread.sleep(80); // wait other thread initialization
			}
			
			boolean forever = true;
			
			while (forever) {
				
				for (int i= 0; i < maxConns; i++) {
					long age = System.currentTimeMillis() - threadCreateTime[i];
					
					System.out.println("name \tPriority \tisAlive \tState \tisInterrupted \tisDaemon");
					System.out.println(threadPool[i].getName() + "\t" + threadPool[i].getPriority() +
							"\t" + threadPool[i].isAlive() + "\t" + threadPool[i].getState() + 
							"\t" + threadPool[i].isInterrupted() +
							"\t" + threadPool[i].isDaemon());

					if (age > maxConnMSec) { // Force a reset at the max
						// thread time
						threadPool[i].interrupt();
						
						threadPool[i] = null;
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
							threadPool[i] = null;
							threadCreateTime[i] = 0L;
							threadLevel[i] = 0;
						}
					}
					
					System.out.println("=======================运行线程数===================:"+runCount);

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
		//new CxrdTools(5,0.5);

		//new MultiThread();
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
					writeTransaction();
					threadLevel[i] ++;
					//Thread.sleep(200);
				} catch (Exception e) {
					e.printStackTrace();
					
					threadPool[i].interrupt();
					
					threadPool[i] = null;
					threadCreateTime[i] = 0L;
					threadLevel[i] = 0;
					
					System.out.println("异常退出.......线程"+i);

					break;
				}
			}
		}
		
		public void writeTransaction (){
			long startTime = System.currentTimeMillis();

			Transaction tx = new Transaction();
			try {
				tx.start();
				
				DBSession session = DBSession.getInstance();
				session.setConnection(tx.getConnection());
				
				Example ex = new Example();
				ex.setId(new Random().nextDouble() * maxLong);
				ex.setName(new Random().nextDouble() * maxLong+"名称");

				//ex.setId(new Random().nextInt(maxLong));
				//ex.setName(new Random().nextInt(maxLong)+"");
				//ex.searchAndRetrieveList();
				System.out.println("find count="+session.count(ex));

				if(session.count(ex) < 1)
					session.add(ex);
				else
					session.update(ex);

				tx.commit();
			}catch (SQLException e) {
				e.printStackTrace();
				if(log.isDebugEnabled()) log.debug("SQLException:"+e.getMessage());
				System.out.println("SQLException:"+e.getMessage());
				tx.rollback();
			}catch (Exception e) {
				e.printStackTrace();
				if(log.isDebugEnabled()) log.debug("Exception:"+e.getMessage());
				System.out.println("Exception:"+e.getMessage());
				tx.rollback();
			}finally {
				tx.end();
				System.out.println("tx end");
			}
			
			long finishTime = System.currentTimeMillis();
			long hours = (finishTime - startTime) / 1000 / 60 / 60;
			long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
			long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
			
			System.out.println(finishTime - startTime);
			System.out.println("expends:   " + hours + ":" + minutes + ":" + seconds);
		}
	}
	
}
