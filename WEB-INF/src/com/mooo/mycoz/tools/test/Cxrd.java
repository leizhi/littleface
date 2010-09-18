package com.mooo.mycoz.tools.test;


import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.dbobj.marketmoniter.BufferPrice;
import com.mooo.mycoz.dbobj.marketmoniter.BufferTraffic;
import com.mooo.mycoz.dbobj.marketmoniter.BusRemotes;
import com.mooo.mycoz.dbobj.marketmoniter.BusSamples;
import com.mooo.mycoz.dbobj.mycozBranch.Example;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.Transaction;

public class Cxrd {
	private static Log log = LogFactory.getLog(Cxrd.class);

	private List<Thread> threadPool = new ArrayList<Thread>();
	private List<Long> threadCreateTime = new ArrayList<Long>();

	private int maxConnMSec;
	
	public Cxrd(int maxConns,double maxConnTime) {

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
					System.out.println("name \tPriority \tisAlive \tState \tisInterrupted \tisDaemon");

					System.out.println(thread.getName() + "\t" + thread.getPriority() +
							"\t" + thread.isAlive() + "\t" + thread.getState() + 
							"\t" + thread.isInterrupted() +
							"\t" + thread.isDaemon());

					if (age > maxConnMSec) { // Force a reset at the max
						// thread time
						threadPool.remove(c);
						threadCreateTime.remove(c);
						
						((Thread) threadPool.get(c)).interrupt();
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
		new Cxrd(13,0.5);
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
					
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}


		public void writeEx(){
			Connection con = null;
			Statement stmt = null;
			String sql = "";
			try {
				//mypool
				con = DbConnectionManager.getConnection();
				con.setAutoCommit(false);
				
				if(log.isDebugEnabled())log.debug("打开连接-------------");
				if(log.isDebugEnabled())log.debug(con);
				
				stmt = con.createStatement();
				sql = "INSERT INTO buffer_price(remoteid,sampleid,sale_price,islocal,oper_date,sale_qnty,sale_money,max_price,min_price)";
				
				BusRemotes busRemotes = (BusRemotes)IDGenerator.randomNo(BusRemotes.class);
				String remoteid = busRemotes.getRemoteid();
				
				BusSamples busSamples = (BusSamples)IDGenerator.randomNo(BusSamples.class);
				String sampleid = busSamples.getSampleid();
				
				if(log.isDebugEnabled())log.debug("remoteid="+remoteid);
				if(log.isDebugEnabled())log.debug("sampleid="+sampleid);
				
				Random random = new Random();
				
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

				if(log.isDebugEnabled())log.debug("sale_price="+sale_price);
				if(log.isDebugEnabled())log.debug("islocal="+islocal);
				if(log.isDebugEnabled())log.debug("oper_date="+oper_date);
				if(log.isDebugEnabled())log.debug("sale_qnty="+sale_qnty);
				
				if(log.isDebugEnabled())log.debug("sale_money="+sale_money);
				if(log.isDebugEnabled())log.debug("max_price="+max_price);
				if(log.isDebugEnabled())log.debug("min_price="+min_price);

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
				
				if(log.isDebugEnabled())log.debug("SQL="+sql);
				stmt.execute(sql);

			    bd = new BigDecimal(sale_money*sale_qnty);
			    double totle_money = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

			    // check 
				sql = "SELECT COUNT(*) total from buffer_traffic WHERE  oper_date=date'"+new SimpleDateFormat("yyyy-MM-dd ").format(oper_date)+"' AND remoteid = '"+remoteid+"'";
				if(log.isDebugEnabled())log.debug("SQL="+sql);
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
				if(log.isDebugEnabled())log.debug("SQL="+sql);

				stmt.execute(sql);
				if(log.isDebugEnabled())log.debug("commit");
				con.commit();
				
			}catch (Exception e) {
				e.printStackTrace();
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(log.isDebugEnabled())log.debug("Exception: " + e.getMessage());

			} finally {
				try {
					stmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		
		///////////////
		public void doIt() {
			if(log.isDebugEnabled())log.debug("线程" + i + "执行");
			Transaction tx = new Transaction();
			
			try {
				tx.start();
				
				long startTime = System.currentTimeMillis();
				Runtime rt = Runtime.getRuntime();
	/*
				BusRemotes busRemotes = new BusRemotes();
				busRemotes.setConnection(tx.getConnection());
				List rList = busRemotes.searchAndRetrieveList();

				int random=0;
				
				if(rList.size() > 0){
					random = new Random().nextInt(rList.size());
					busRemotes = (BusRemotes) rList.get(random);
				}else{
					new SQLException("Not find BusRemotes Random Record........");
				}
				
				String remoteid = busRemotes.getRemoteid();
				
				BusSamples busSamples = new BusSamples();
				busSamples.setConnection(tx.getConnection());
				rList = busSamples.searchAndRetrieveList();
				
				if(rList.size() > 0){
					random = new Random().nextInt(rList.size());
					busSamples = (BusSamples) rList.get(random);
				}else{
					new SQLException("Not find BusSamples Random Record........");
				}
				
				String sampleid = busSamples.getSampleid();

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
				
				BufferPrice BufferPrice = new BufferPrice();
				BufferPrice.setConnection(tx.getConnection());
				
				BufferPrice.setRemoteid(remoteid);
				BufferPrice.setSampleid(sampleid);

				BufferPrice.setSalePrice(salePrice);
				BufferPrice.setOperDate(operDate);
				BufferPrice.setSaleQnty(saleQnty);
				BufferPrice.setSaleMoney(saleMoney);
				BufferPrice.setMaxPrice(maxPrice);
				BufferPrice.setMinPrice(minPrice);
				BufferPrice.setIslocal(islocal.toString());
				BufferPrice.add();
					
				BufferTraffic bufferTraffic = new BufferTraffic();
				bufferTraffic.setConnection(tx.getConnection());
				bufferTraffic.setRemoteid(remoteid);
				bufferTraffic.setOperDate(operDate);

				bd = new BigDecimal(saleMoney * saleQnty);
				double totleMoney = bd.setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();

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
				*/
				
				Example ex1 = new Example();
				ex1.setConnection(tx.getConnection());
				System.out.println("查询======"+ex1.count());
				
				Example a1 = new Example();
				a1.setConnection(tx.getConnection());
				a1.setId(new Random().nextInt());
				a1.setName("日本女"+new Random().nextInt());
				a1.add();
				
				Example ex2 = new Example();
				ex1.setConnection(tx.getConnection());
				System.out.println("查询======"+ex2.count());

				Example a2 = new Example();
				a2.setConnection(tx.getConnection());
				a2.setId(new Random().nextInt());
				a2.setName("日本女"+new Random().nextInt());
				a2.add();
				
				Example ex3 = new Example();
				ex3.setConnection(tx.getConnection());
				System.out.println("查询======"+ex3.count());
				
				Example a3 = new Example();
				a3.setConnection(tx.getConnection());
				a3.setId(new Random().nextInt());
				a3.setName("日本女"+new Random().nextInt());
				a3.add();
				
				tx.commit();
				
				long finishTime = System.currentTimeMillis();
				long hours = (finishTime - startTime) / 1000 / 60 / 60;
				long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
				long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
				
				if(log.isDebugEnabled())log.debug("Total Memory:" + rt.totalMemory()+ " Free Memory:" + rt.freeMemory());
				
				if(log.isDebugEnabled())log.debug(finishTime - startTime);
				if(log.isDebugEnabled())log.debug("expends:   " + hours + ":" + minutes + ":" + seconds);
			} catch (NullPointerException e) {
				tx.rollback();

				e.printStackTrace();
				if(log.isDebugEnabled())log.debug("NullPointerException :" + e.getMessage());
				System.out.println("线程" + i + "异常退出");
			} catch (SQLException e) {
				tx.rollback();

				if(log.isDebugEnabled())log.debug("SQLException:"+e.getMessage());
				e.printStackTrace();
				System.out.println("线程" + i + "异常退出");

			} catch (RuntimeException e) {
				tx.rollback();

				if(log.isDebugEnabled())log.debug("RuntimeException:"+e.getMessage());
				e.printStackTrace();
				System.out.println("线程" + i + "异常退出");

			} catch (Exception e) {
				tx.rollback();

				e.printStackTrace();
				if(log.isDebugEnabled())log.debug("Exception:"+e.getMessage());
				System.out.println("线程" + i + "异常退出");

			} catch (Throwable e) {
				tx.rollback();

				e.printStackTrace();
				if(log.isDebugEnabled())log.debug("Throwable:"+e.getMessage());
				System.out.println("线程" + i + "异常退出");

			}finally {
				tx.end();
				System.out.println("线程" + i + "操作完成");
			}
		}
		
		///////////
		public void writeJdbc() {
			Connection con = null;
			Statement stmt = null;
			String sql = "";
			try {
				//mypool
				con = DbConnectionManager.getConnection();
				con.setAutoCommit(false);
				
				if(log.isDebugEnabled())log.debug("打开连接-------------");
				if(log.isDebugEnabled())log.debug(con);
				
				stmt = con.createStatement();
				sql = "INSERT INTO buffer_price(remoteid,sampleid,sale_price,islocal,oper_date,sale_qnty,sale_money,max_price,min_price)";
				
				BusRemotes busRemotes = (BusRemotes)IDGenerator.randomNo(BusRemotes.class);
				String remoteid = busRemotes.getRemoteid();
				
				BusSamples busSamples = (BusSamples)IDGenerator.randomNo(BusSamples.class);
				String sampleid = busSamples.getSampleid();
				
				if(log.isDebugEnabled())log.debug("remoteid="+remoteid);
				if(log.isDebugEnabled())log.debug("sampleid="+sampleid);
				
				Random random = new Random();
				
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

				if(log.isDebugEnabled())log.debug("sale_price="+sale_price);
				if(log.isDebugEnabled())log.debug("islocal="+islocal);
				if(log.isDebugEnabled())log.debug("oper_date="+oper_date);
				if(log.isDebugEnabled())log.debug("sale_qnty="+sale_qnty);
				
				if(log.isDebugEnabled())log.debug("sale_money="+sale_money);
				if(log.isDebugEnabled())log.debug("max_price="+max_price);
				if(log.isDebugEnabled())log.debug("min_price="+min_price);

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
				
				if(log.isDebugEnabled())log.debug("SQL="+sql);
				stmt.execute(sql);

			    bd = new BigDecimal(sale_money*sale_qnty);
			    double totle_money = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

			    // check 
				sql = "SELECT COUNT(*) total from buffer_traffic WHERE  oper_date=date'"+new SimpleDateFormat("yyyy-MM-dd ").format(oper_date)+"' AND remoteid = '"+remoteid+"'";
				if(log.isDebugEnabled())log.debug("SQL="+sql);
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
				if(log.isDebugEnabled())log.debug("SQL="+sql);

				stmt.execute(sql);
				if(log.isDebugEnabled())log.debug("commit");
				con.commit();
				
			}catch (Exception e) {
				e.printStackTrace();
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(log.isDebugEnabled())log.debug("Exception: " + e.getMessage());

			} finally {
				try {
					stmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//////////////////
		public void writeBean() {

			Transaction tx = new Transaction();
			try {
				tx.start();

				BusRemotes busRemotes = new BusRemotes();
				busRemotes.setConnection(tx.getConnection());
				//Vector vector = busRemotes.searchAndRetrieveList();
				
				List rList = busRemotes.searchAndRetrieveList();
				if(log.isDebugEnabled())log.debug("BusRemotes rList.size="+rList.size());
				busRemotes = (BusRemotes) rList.get(new Random().nextInt(rList.size()));
				String remoteid = busRemotes.getRemoteid();
				
				BusSamples busSamples = new BusSamples();
				busSamples.setConnection(tx.getConnection());
				rList = busSamples.searchAndRetrieveList();
				if(log.isDebugEnabled())log.debug("BusSamples rList.size="+rList.size());

				busSamples = (BusSamples) rList.get(new Random().nextInt(rList.size()));
				String sampleid = busSamples.getSampleid();

				if(log.isDebugEnabled())log.debug("remoteid=" + remoteid);
				if(log.isDebugEnabled())log.debug("sampleid=" + sampleid);

				// random.setSeed(10000000L);
				BigDecimal bd = new BigDecimal(new Random().nextDouble() * 10);
				double salePrice = bd.setScale(3, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				Integer islocal = (new Random().nextInt(2));
				Date operDate = IDGenerator.randomDate();

				bd = new BigDecimal(new Random().nextDouble() * 100);
				double saleQnty = bd.setScale(1, BigDecimal.ROUND_HALF_UP)
						.doubleValue();

				bd = new BigDecimal(new Random().nextDouble() * 100);
				double saleMoney = bd.setScale(3, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				double maxPrice = saleMoney;
				double minPrice = saleMoney;
//				if(log.isDebugEnabled())log.debug("sale_price=" + salePrice);
//				if(log.isDebugEnabled())log.debug("islocal=" + islocal);
//				if(log.isDebugEnabled())log.debug("oper_date=" + operDate);
//				if(log.isDebugEnabled())log.debug("sale_qnty=" + saleQnty);
	//
//				if(log.isDebugEnabled())log.debug("sale_money=" + saleMoney);
//				if(log.isDebugEnabled())log.debug("max_price=" + maxPrice);
//				if(log.isDebugEnabled())log.debug("min_price=" + minPrice);
				BufferPrice BufferPrice = new BufferPrice();
				BufferPrice.setConnection(tx.getConnection());
				BufferPrice.setRemoteid(remoteid);
				BufferPrice.setSampleid(sampleid);

				BufferPrice.setSalePrice(salePrice);
				BufferPrice.setOperDate(operDate);
				BufferPrice.setSaleQnty(saleQnty);
				BufferPrice.setSaleMoney(saleMoney);
				BufferPrice.setMaxPrice(maxPrice);
				BufferPrice.setMinPrice(minPrice);
				BufferPrice.setIslocal(islocal.toString());
				BufferPrice.add();

				BufferTraffic bufferTraffic = new BufferTraffic();
				bufferTraffic.setConnection(tx.getConnection());
				bufferTraffic.setRemoteid(remoteid);
				bufferTraffic.setOperDate(operDate);

				bd = new BigDecimal(saleMoney * saleQnty);
				double totleMoney = bd.setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();

				if (bufferTraffic.count() > 0) {
					if(log.isDebugEnabled())log.debug("count=" + bufferTraffic.count());

					bufferTraffic.retrieve();

					if(log.isDebugEnabled())log.debug("saleMoney="
							+ bufferTraffic.getSaleMoney());
					if(log.isDebugEnabled())log.debug("tradeAmount="
							+ bufferTraffic.getTradeAmount());

					bufferTraffic
							.setTradeAmount(bufferTraffic.getTradeAmount() + 1);
					bufferTraffic.setSaleMoney(bufferTraffic.getSaleMoney()
							+ totleMoney);

					if(log.isDebugEnabled())log.debug("saleMoney="
							+ bufferTraffic.getSaleMoney());
					if(log.isDebugEnabled())log.debug("tradeAmount="
							+ bufferTraffic.getTradeAmount());

					bufferTraffic.update();

				} else {
					if(log.isDebugEnabled())log.debug("count=" + bufferTraffic.count());
					bufferTraffic.setTradeAmount(1);
					bufferTraffic.setSaleMoney(totleMoney);
					bufferTraffic.add();
				}

				tx.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				if(log.isDebugEnabled())log.debug("SQLException:" + e.getMessage());
				tx.rollback();
			} finally {
				tx.end();
			}
			
		}
	
	}}