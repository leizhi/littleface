package com.mooo.mycoz.tools;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.dbobj.marketmoniter.BusRemotes;

public class MultiThread {

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
			String remoteid = randomNo("bus_remotes","remoteid");
			String sampleid = randomNo("bus_samples","sampleid");

			System.out.println("remoteid="+remoteid);
			System.out.println("sampleid="+sampleid);
			Random random = new Random();
			
			//random.setSeed(10000000L);
	       BigDecimal bd = new BigDecimal(random.nextDouble() * 10);
	       double sale_price = bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	       int islocal = new Random().nextInt(2);
	       String oper_date = randomDate();

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
					+"date'"+oper_date+"',"
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
					+ islocal +","
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public String randomNo(String table,String rFiled){
		Connection con = null;
		Statement stmt = null;
		String sql = "";
		String rValue="";
		try {
			//mypool
			con = DbConnectionManager.getConnection();
			System.out.println("打开连接-------------");
			System.out.println(con);
			
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			sql = "SELECT  * FROM "+table;
			ResultSet rs = stmt.executeQuery(sql);
			List remotes = new ArrayList();
			
			while (rs.next()) {
				remotes.add(rs.getString(rFiled));
			}
			rs.first();
			
			int randomIndex=0;
			
			Random random = new Random();
			randomIndex = random.nextInt(remotes.size());

			int i=0;
			while (rs.next()) {
				if(i==randomIndex){
					rValue = rs.getString(rFiled);
					break;
				}
				i++;
			}
			//random.
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception: " + e.getMessage());
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rValue;
	}
	
	public String randomDate(){
		String rDate="1976-01-01";
		
		Random random = new Random();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 7, 1);
		long start = cal.getTimeInMillis();
		cal.set(2010, 9, 13);
		long end = cal.getTimeInMillis();
		for (int i = 0; i < 10; i++) {
			Date d = new Date(start + (long) (random.nextDouble() * (end - start)));
			rDate = format.format(d);
			//System.out.println("build value="+rDate);
		}
		
		return rDate;
	}

	public int randomInt(int max){
		return new Random().nextInt(max);
	}
	
	public void sdb(){
		try {
			
			BusRemotes busRemotes = new BusRemotes();
			busRemotes.setTable("bus_remotes");
			List remotes = busRemotes.searchAndRetrieveList();
			Random random = new Random();
			for(int i=0;i<remotes.size();i++)
			System.out.println("random="+random.nextInt(remotes.size()));
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException:"+e.getMessage());
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//MultiThread mt = new MultiThread();
		//mt.();
		
		//mt.db();
		//System.out.println("no="+mt.randomNo("bus_remotes","remoteid"));
		//System.out.println("no="+mt.randomNo("bus_samples","sampleid"));
		//System.out.println("no="+mt.randomNo("bus_remotes","remoteid"));

		new Thread(new WriteRun()).start();
		//new Thread(new ReadRun()).start();
	}

}

class ReadRun implements Runnable {

	int count = 1;

	public ReadRun() {
		System.out.println("创建线程 ");
	}

	public void run() {
		try {
			while (true) {
				System.out.println();

				if ((count++) % 100 == 0)
					break;
				Thread.sleep(30000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class WriteRun implements Runnable {

	//int count = 1;
	MultiThread mt = new MultiThread();
	
	public WriteRun() {
		System.out.println("创建写入线程...");
	}

	public void run() {
		try {
			while (true) {
				System.out.println("insert into data");
				mt.writeDb();
				//if ((count++) % 100 == 0)
				//	break;
				//wait(30000); //30 seconds
				//Thread.sleep(30000); //30 seconds
				Thread.sleep(10000); //10 seconds
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
