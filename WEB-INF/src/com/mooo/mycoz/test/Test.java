package com.mooo.mycoz.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.OracleSQL;
import com.mooo.mycoz.dbobj.marketmoniter.BufferTraffic;
import com.mooo.mycoz.dbobj.marketmoniter.BusRemotes;
import com.mooo.mycoz.dbobj.mycozBranch.Example;

public class Test {

	public void searchSql(){
		Connection connection=null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			long startTime = System.currentTimeMillis();

			connection = DbConnectionManager.getConnection();
			
			stmt = connection.createStatement();
			
			//Example ex = new Example();
			//ex.searchSQL();
			
			rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM buffer_traffic");
			
			while(rs.next()){
				System.out.println("count ="+rs.getInt(1));
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
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {

		Example ex = new Example();
		try {
			//ex.setRecord(0, 20);
			
			List reList = ex.searchAndRetrieveList();
			System.out.println("count:"+reList.size());

			Iterator it = reList.iterator();
			Example bean;
			while (it.hasNext()) {
				bean = (Example) it.next();
				System.out.println("name:"+bean.getName()+" id:"+bean.getId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//System.out.println(com.mooo.mycoz.util.IDGenerator.getNowTime());
		//System.out.println(com.mooo.mycoz.util.IDGenerator.getNow());
		//System.out.println(com.mooo.mycoz.util.IDGenerator.getNowYear());
		/*new Test().searchSql();
		
		BufferTraffic bufferTraffic = new BufferTraffic();
		try {
			System.out.println("count = "+bufferTraffic.count());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		BufferTraffic bufferTraffic = new BufferTraffic();
		bufferTraffic.setRemoteid("sdfsaf08");
		bufferTraffic.setOperDate(new Date());
		
		try {
			bufferTraffic.add();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		*/
	}
}