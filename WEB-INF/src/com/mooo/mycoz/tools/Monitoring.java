package com.mooo.mycoz.tools;

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

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.dbobj.DBSession;
import com.mooo.mycoz.dbobj.marketmoniter.BufferPrice;
import com.mooo.mycoz.dbobj.marketmoniter.BufferTraffic;
import com.mooo.mycoz.dbobj.marketmoniter.BusRemotes;
import com.mooo.mycoz.dbobj.marketmoniter.BusSamples;
import com.mooo.mycoz.dbobj.mycozBranch.Example;
import com.mooo.mycoz.util.DbUtil;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.Transaction;

public class Monitoring {
	
	final public static int MAX = 80;
	
	
	public Monitoring (){
		System.out.println("欢迎使用本软件!");
		new MainThread();
	}
	
	// --- Main Thread
	class MainThread extends Thread {
		//private Thread[] threadPool;
		private List<Thread> threadPool;
		private List<Long> threadCreateTime;

		private int maxConnMSec;
		private int maxConnTime = 30;

		public MainThread(){
			
			maxConnMSec = (int) (maxConnTime * 86400000.0); // 86400 sec/day
			if (maxConnMSec < 30000) { // Recycle no less than 30 seconds.
				maxConnMSec = 30000;
			}
			
			threadPool = new ArrayList<Thread>();
			threadCreateTime = new ArrayList<Long>();
			
			start();
		}

		public void run() {
				//while (true){
					for(int i=0;i<MAX;i++){
						
						threadPool.add(new Thread(new WriteRunnable()));
						((Thread) threadPool.get(i)).start();
					}
				//}
		}
		
	}
	
	class WriteRunnable implements Runnable {

		//int count = 1;
		
		public WriteRunnable() {
			System.out.println("创建写入线程...");
		}
/*
		public void run(int i) {
			try {
				Date now = new Date();

				while (true) {
					System.out.println("insert into data");
					
					writeDb();
					//if ((count++) % 100 == 0)
					//	break;
					//wait(30000); //30 seconds
					//Thread.sleep(30000); //30 seconds
					//Thread.sleep(10000); //10 seconds
					Thread.sleep(10); //10 ms
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		*/
			public void writeDb(){
				Connection con = null;
				Statement stmt = null;
				String sql = "";
				try {
					//mypool
					con = DbConnectionManager.getConnection();
					con.setAutoCommit(false);
					
					System.out.println("打开连接-------------");
					System.out.println(con);
					
					stmt = con.createStatement();
					sql = "INSERT INTO buffer_price(remoteid,sampleid,sale_price,islocal,oper_date,sale_qnty,sale_money,max_price,min_price)";
					
					BusRemotes busRemotes = (BusRemotes)IDGenerator.randomNo(BusRemotes.class);
					String remoteid = busRemotes.getRemoteid();
					
					BusSamples busSamples = (BusSamples)IDGenerator.randomNo(BusSamples.class);
					String sampleid = busSamples.getSampleid();
					
					System.out.println("remoteid="+remoteid);
					System.out.println("sampleid="+sampleid);
					
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
					sql = "SELECT COUNT(*) total from buffer_traffic WHERE  oper_date=date'"+oper_date+"' AND remoteid = '"+remoteid+"'";
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
								+" WHERE oper_date=date'"+oper_date+"' AND remoteid = '"+remoteid+"'";
					} else { // else not
						sql = "INSERT INTO buffer_traffic(remoteid,oper_date,trade_amount,sale_money) ";
						sql += " VALUES(" +"'"+remoteid+"',"
							+"date'"+oper_date+"',"
							+ 1 +","
							+ totle_money
							+")";
					}
					System.out.println("SQL="+sql);

					stmt.execute(sql);
					System.out.println("commit");
					con.commit();
					
				}catch (Exception e) {
					e.printStackTrace();
					try {
						con.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					System.out.println("Exception: " + e.getMessage());

				} finally {
					try {
						stmt.close();
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}

			public void writeTx(){
				Transaction tx = new Transaction();
				try {
					tx.start();
					
					BusRemotes busRemotes = (BusRemotes)IDGenerator.randomNo(BusRemotes.class);
					String remoteid = busRemotes.getRemoteid();
					
					BusSamples busSamples = (BusSamples)IDGenerator.randomNo(BusSamples.class);
					String sampleid = busSamples.getSampleid();
					
					System.out.println("remoteid="+remoteid);
					System.out.println("sampleid="+sampleid);
					
					Random random = new Random();
					
					//random.setSeed(10000000L);
			       BigDecimal bd = new BigDecimal(random.nextDouble() * 10);
			       double salePrice = bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
			       Integer islocal = (new Random().nextInt(2));
			       Date operDate = IDGenerator.randomDate();

			       bd = new BigDecimal(random.nextDouble() * 100);
			       double saleQnty = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
			       
			       bd = new BigDecimal(random.nextDouble() * 100);
			       double saleMoney = bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
			       double maxPrice = saleMoney;
			       double minPrice = saleMoney;

					System.out.println("sale_price="+salePrice);
					System.out.println("islocal="+islocal);
					System.out.println("oper_date="+operDate);
					System.out.println("sale_qnty="+saleQnty);
					
					System.out.println("sale_money="+saleMoney);
					System.out.println("max_price="+maxPrice);
					System.out.println("min_price="+minPrice);
					
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
					
				    bd = new BigDecimal(saleMoney*saleQnty);
				    double totleMoney = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				    
					if(bufferTraffic.count() > 0) {
						System.out.println("count="+bufferTraffic.count());
						
						bufferTraffic.retrieve();
						
						System.out.println("saleMoney="+bufferTraffic.getSaleMoney());
						System.out.println("tradeAmount="+bufferTraffic.getTradeAmount());

						bufferTraffic.setTradeAmount(bufferTraffic.getTradeAmount()+1);
						bufferTraffic.setSaleMoney(bufferTraffic.getSaleMoney()+totleMoney);
						
						System.out.println("saleMoney="+bufferTraffic.getSaleMoney());
						System.out.println("tradeAmount="+bufferTraffic.getTradeAmount());
						
						bufferTraffic.update();
						
					}else {
						System.out.println("count="+bufferTraffic.count());
						bufferTraffic.setTradeAmount(1);
						bufferTraffic.setSaleMoney(totleMoney);
						bufferTraffic.add();
					}
				
					tx.commit();
				}catch (SQLException e) {
					e.printStackTrace();
					System.out.println("SQLException:"+e.getMessage());
					tx.rollback();
				}finally {
					tx.end();
				}
			}
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					//Date now = new Date();

					while (true) {
						System.out.println("insert into data");
						writeTx();
						//writeDb();
						//if ((count++) % 100 == 0)
						//	break;
						//wait(30000); //30 seconds
						//Thread.sleep(30000); //30 seconds
						//Thread.sleep(10000); //10 seconds
						Thread.sleep(10); //10 ms
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("欢迎使用本软件!");
		
		new Monitoring();
		
		//while(true) {
			
		//}
/*
		try {
			System.out.println("欢迎使用本软件!");

			byte buffer[] = new byte[512]; // 输入缓冲区
			int count = System.in.read(buffer); // 读取标准输入流
//			System.out.println("Output: ");
//			for (int i = 0; i < count; i++) { // 输出buffer元素值
//				System.out.print(" " + buffer[i]);
//			}
//			System.out.println();
			for (int i = 0; i < count; i++) { // 按字符方式输出buffer
				System.out.print((char) buffer[i]);
			}
			//System.out.println("count = " + count); // buffer实际长度
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}

}
