package com.mooo.mycoz.tools.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.dbobj.marketmoniter.BufferPrice;
import com.mooo.mycoz.dbobj.marketmoniter.BufferTraffic;
import com.mooo.mycoz.dbobj.marketmoniter.BusRemotes;
import com.mooo.mycoz.dbobj.marketmoniter.BusSamples;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.Transaction;

public class CxrdTools {
	private static Log log = LogFactory.getLog(CxrdTools.class);

	//private long maxLong = 9223372036854775807L;
	//private double maxDouble = 1.79769313486231570e+308;
	
	private static List<Object> busRemotesList;
	private static List<Object> busSamplesList;

	private Thread[] threadPool;
	private Long[] threadCreateTime;
	private Integer[] threadLevel;
	
	private int maxConnMSec;
	
	public CxrdTools(){
		long startTime = System.currentTimeMillis();
		new DoThread(0).writeTransaction();
		long finishTime = System.currentTimeMillis();
		System.out.println("ex ms:"+(finishTime - startTime));
	}
	
	public CxrdTools(int maxConns,double maxConnTime) {

		threadPool = new  Thread[maxConns];
		threadCreateTime = new Long[maxConns];
		threadLevel = new Integer[maxConns];
		
		Transaction tx = new Transaction();
		tx.start();
		tx.end();
		///////
		try {
			busRemotesList = new BusRemotes().searchAndRetrieveList();
			busSamplesList = new BusSamples().searchAndRetrieveList();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
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
				
				//System.out.println("===启动线程=====::::::" + i);
				//Thread.sleep(80); // wait other thread initialization
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
		//new CxrdTools(20000,0.5);
		new CxrdTools(800,0.5);
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
					
					//Thread.sleep(5);
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

			Transaction tx = new Transaction();
			try {
				tx.start();
				Random random;
				int randomId=0;
				
				random = new Random();
				BusRemotes busRemotes=null;
				randomId = busRemotesList.size();
				if (randomId > 0) {
					randomId = random.nextInt(randomId);
					if(randomId < busRemotesList.size())
						busRemotes = (BusRemotes) busRemotesList.get(randomId);
					else
						busRemotes = (BusRemotes) busRemotesList.get(randomId-1);
				} else {
					busRemotes = (BusRemotes) busRemotesList.get(0);
				}
				
				random = new Random();
				BusSamples busSamples=null;
				randomId = busSamplesList.size();
				if (randomId > 0) {
					random.nextInt(randomId);
					if(randomId < busRemotesList.size())
						busSamples = (BusSamples) busSamplesList.get(randomId);
					else
						busSamples = (BusSamples) busSamplesList.get(randomId-1);
				} else {
					busSamples = (BusSamples) busSamplesList.get(0);
				}
				
				String remoteid = busRemotes.getRemoteid();
				String sampleid = busSamples.getSampleid();

				if(log.isDebugEnabled())log.debug("remoteid="+remoteid);
				if(log.isDebugEnabled())log.debug("sampleid="+sampleid);
				
				BigDecimal bd = new BigDecimal(new Random().nextDouble() * 10);
				double salePrice = bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
				Integer islocal = (new Random().nextInt(2));
				Date operDate = IDGenerator.randomDate();

				bd = new BigDecimal(new Random().nextDouble() * 100);
				double saleQnty = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

				bd = new BigDecimal(new Random().nextDouble() * 100);
				double saleMoney = bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
				double maxPrice = saleMoney;
				double minPrice = saleMoney;
				
				BufferPrice bufferPrice = new BufferPrice();
				bufferPrice.setConnection(tx.getConnection());
				
				bufferPrice.setRemoteid(remoteid);
				bufferPrice.setSampleid(sampleid);

				if (bufferPrice.count() > 0)
					new SQLException("is have .");
				
				bufferPrice.setSalePrice(salePrice);
				bufferPrice.setOperDate(operDate);
				bufferPrice.setSaleQnty(saleQnty);
				bufferPrice.setSaleMoney(saleMoney);
				bufferPrice.setMaxPrice(maxPrice);
				bufferPrice.setMinPrice(minPrice);
				bufferPrice.setIslocal(islocal.toString());
				
				bufferPrice.add();
					
				BufferTraffic bufferTraffic = new BufferTraffic();
				bufferTraffic.setConnection(tx.getConnection());
				bufferTraffic.setRemoteid(remoteid);
				bufferTraffic.setOperDate(operDate);

				bd = new BigDecimal(saleMoney * saleQnty);
				double totleMoney = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				
				if (bufferTraffic.count() > 0) {
					bufferTraffic.retrieve();
					bufferTraffic.setTradeAmount(bufferTraffic.getTradeAmount() + 1);
					bufferTraffic.setSaleMoney(bufferTraffic.getSaleMoney()+ totleMoney);
					bufferTraffic.update();
				} else {
					bufferTraffic.setTradeAmount(1);
					bufferTraffic.setSaleMoney(totleMoney);
					bufferTraffic.add();
				}

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
			}
			
		}
	}
	
}
