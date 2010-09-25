package com.mooo.mycoz.tools.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.dbobj.marketmoniter.BufferPrice;
import com.mooo.mycoz.dbobj.marketmoniter.BufferTraffic;
import com.mooo.mycoz.dbobj.marketmoniter.BusRemotes;
import com.mooo.mycoz.dbobj.marketmoniter.BusSamples;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.Transaction;

public class CxrdTools {
	private static Log log = LogFactory.getLog(CxrdTools.class);

	//private static final int maxInt = 2147483647;
	//private static final long maxLong = 9223372036854775807L;
	//private static final double maxDouble = 1.79769313486231570e+308;
	private static final int maxThread=800;

	private Thread[] threadPool;
	private int maxConnMSec;
	//private int maxConnPool=25;

	public CxrdTools(int maxConns,double maxConnTime) {

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
			//DoThread doThread = new DoThread();
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
		new CxrdTools(8,10);
	}
	
	class DoThread implements Runnable  {
		private Object initLock = new Object();
		
		private DbProcess dbAction;
		private List<Object> busRemotesList;
		private List<Object> busSamplesList;
		
		private long createTime;

		public DoThread(){
			createTime = System.currentTimeMillis();
			dbAction = DbFactory.getInstance();
			try {
				busRemotesList = dbAction.searchAndRetrieveList(new BusRemotes());
				busSamplesList = dbAction.searchAndRetrieveList(new BusSamples());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		public void run() {
			boolean forever = true;
			while (forever) {
					synchronized (initLock) {
						writeJDBC();
						//writeTransaction();
						long age = System.currentTimeMillis() - createTime;
						if (age > maxConnMSec) { // Force a reset at the max
							System.out.println("===超时 退出=====");
							break;
						}
					} //synchronized end
			} // loop run
		} // end run

		public void writeJDBC(){
			Connection connection = null;
			Statement stmt = null;
			String sql = null;
			try {
				//mypool
				connection = DbConnectionManager.getConnection();
				connection.setAutoCommit(false);
				
				System.out.println("打开连接-------------");
				System.out.println(connection);
				
				stmt = connection.createStatement();
				sql = "INSERT INTO buffer_price(remoteid,sampleid,sale_price,islocal,oper_date,sale_qnty,sale_money,max_price,min_price)";
				
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
				
				System.out.println("remoteid="+remoteid);
				System.out.println("sampleid="+sampleid);
				
				//random.setSeed(10000000L);
		       BigDecimal bd = new BigDecimal(random.nextDouble() * 10);
		       double sale_price = bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		       int islocal = new Random().nextInt(2);
		       Date oper_date = IDGenerator.randomDate();

		       bd = new BigDecimal(random.nextDouble() * 100);
		       double sale_qnty = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		       
		       bd = new BigDecimal(random.nextDouble() * 100);
		       double sale_money = bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		       double max_price = sale_money;
		       double min_price = sale_money;

				System.out.println("sale_price="+sale_price);
				System.out.println("islocal="+islocal);
				System.out.println("oper_date="+oper_date);
				System.out.println("sale_qnty="+sale_qnty);
				
				System.out.println("sale_money="+sale_money);
				System.out.println("max_price="+max_price);
				System.out.println("min_price="+min_price);

				sql += " VALUES(" +"'"+remoteid+"',"
						+"'"+sampleid+"',"
						+sale_price+","
						+islocal+","
						+"date'"+new SimpleDateFormat("yyyy-MM-dd ").format(oper_date) +"',"
						+sale_qnty+","
						+sale_money+","
						+max_price+","
						+min_price
						+")";
				
				System.out.println("SQL="+sql);
				stmt.execute(sql);

			    bd = new BigDecimal(sale_money*sale_qnty);
			    double totle_money = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

			    // check 
				sql = "SELECT COUNT(*) total from buffer_traffic WHERE  oper_date=date'"+new SimpleDateFormat("yyyy-MM-dd ").format(oper_date)+"' AND remoteid = '"+remoteid+"'";
				System.out.println("SQL="+sql);
				ResultSet result = stmt.executeQuery(sql);
				int total=0;
				
				while (result.next()) {
					total = result.getInt("total");
				}
				// if have
				if(total > 0){
					sql = "UPDATE buffer_traffic SET "
							+"trade_amount=trade_amount+1,sale_money=sale_money+"+totle_money
							+" WHERE oper_date=date'"+new SimpleDateFormat("yyyy-MM-dd ").format(oper_date)+"' AND remoteid = '"+remoteid+"'";
				} else { // else not
					sql = "INSERT INTO buffer_traffic(remoteid,oper_date,trade_amount,sale_money) ";
					sql += " VALUES(" +"'"+remoteid+"',"
						+"date'"+new SimpleDateFormat("yyyy-MM-dd ").format(oper_date)+"',"
						+ 1 +","
						+ totle_money
						+")";
				}
				System.out.println("SQL="+sql);

				stmt.execute(sql);
				System.out.println("commit");
				connection.commit();
				
			}catch (SQLException e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				System.out.println("SQLException: " + e.getMessage());

			}catch (Exception e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				System.out.println("Exception: " + e.getMessage());

			} finally {
				try {
					stmt.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
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
				
				System.out.println("remoteid="+remoteid);
				System.out.println("sampleid="+sampleid);

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
				
				bufferPrice.setRemoteid(remoteid);
				bufferPrice.setSampleid(sampleid);

				if (dbAction.count(bufferPrice) > 0)
					new SQLException("is have .");
				
				bufferPrice.setSalePrice(salePrice);
				bufferPrice.setOperDate(operDate);
				bufferPrice.setSaleQnty(saleQnty);
				bufferPrice.setSaleMoney(saleMoney);
				bufferPrice.setMaxPrice(maxPrice);
				bufferPrice.setMinPrice(minPrice);
				bufferPrice.setIslocal(islocal.toString());
				
				dbAction.add(tx.getConnection(),bufferPrice);
					
				BufferTraffic bufferTraffic = new BufferTraffic();
				bufferTraffic.setRemoteid(remoteid);
				bufferTraffic.setOperDate(operDate);

				bd = new BigDecimal(saleMoney * saleQnty);
				double totleMoney = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				
				if (dbAction.count(bufferTraffic) > 0) {
					dbAction.retrieve(bufferTraffic);
					bufferTraffic.setTradeAmount(bufferTraffic.getTradeAmount() + 1);
					bufferTraffic.setSaleMoney(bufferTraffic.getSaleMoney()+ totleMoney);
					dbAction.update(tx.getConnection(),bufferTraffic);
				} else {
					bufferTraffic.setTradeAmount(1);
					bufferTraffic.setSaleMoney(totleMoney);
					dbAction.add(tx.getConnection(),bufferTraffic);
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