package com.mooo.mycoz.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.MysqlSQL;
import com.mooo.mycoz.db.sql.SQLProcess;
import com.mooo.mycoz.dbobj.marketmoniter.BusRemotes;
import com.mooo.mycoz.dbobj.mycozBranch.Example;
import com.mooo.mycoz.dbobj.mycozBranch.FileInfo;
import com.mooo.mycoz.dbobj.mycozShared.CodeType;
import com.mooo.mycoz.util.StringUtils;

public class Test {

	public static void main(String[] args) {
		
		//System.out.println("StringUtils.getCatalog ="+StringUtils.getCatalog(BusRemotes.class,1));


		long maxLong = 9223372036854775807L;
		int MaxInt = 2147483647;

		try {
			DbProcess dbAction = DbFactory.getInstance();
			FileInfo fi = new FileInfo();
			fi.setId(6);
			fi.setTypeid(4);
			fi.setName("没有6可能");
			fi.setDatetime(new Date());
			fi.setFilepath("filePath");
			
			dbAction.add(fi);
			
			/*
			CodeType codeType = new CodeType();
			codeType.setId(new Integer ("1"));
			dbAction.retrieve(codeType);
			
			System.out.println("getId="+codeType.getId());
			System.out.println("getName="+codeType.getName());
			System.out.println("getCategory="+codeType.getCategory());
			
			BusRemotes busRemotes = new BusRemotes();
			busRemotes.setRemoteid("0014");
			dbAction.retrieve(busRemotes);
			
			System.out.println("count="+busRemotes.getAddr());
			System.out.println("count="+busRemotes.getRemotename());
			System.out.println("count="+busRemotes.getMasterenterp());
			System.out.println("count="+busRemotes.getZone());

			
			List reList = dbAction.searchAndRetrieveList(new BusRemotes());
			System.out.println("sql:");
			System.out.println("count="+dbAction.count(new BusRemotes()));

			Iterator it = reList.iterator();
			BusRemotes bean;
			while (it.hasNext()) {
				bean = (BusRemotes) it.next();
				System.out.println("name:"+bean.getAddr()+" id:"+bean.getRemoteid());
			}
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		int maxInt = 2147483647;

		Connection conn = null;
		Statement stmt = null;

	      try {
	    	  		conn = DbConnectionManager.getConnection();
	    	  		stmt  = conn.createStatement();
	    	  		
	    	  		SQLAction sa = new MysqlAction();
	    	  		
	    			Example ex = new Example();
	    			ex.setId(new Random().nextDouble() * maxInt);
	    			ex.setName(new Random().nextDouble() * maxInt+"名称");
	    			
	    			String sql = sa.addSQL(ex);
	              System.out.println("sleep 200ms");
	              Thread.sleep(2000);
	              System.out.println("salp on");

	    			stmt.execute(sql);
	    			
	    	  		/*
	            DatabaseMetaData meta = conn.getMetaData();
	            ResultSet rs = meta.getColumns(null, null, "Example", null);
	            while (rs.next()) {
	                System.out.println(rs.getString(1));
	                System.out.println(rs.getString(2));
	                System.out.println(rs.getString(3));
	                System.out.println(rs.getString(4));
	                System.out.println(rs.getString(5));
	                System.out.println(rs.getString(6));
	                System.out.println(rs.getString(7));
	                System.out.println(rs.getString(8));
	                System.out.println(rs.getString(9));
	                System.out.println(rs.getString(10));
	                System.out.println("-----db2--------");
	            }
	        } catch (Exception ex) {
	            System.out.println(ex);
	        } finally {
	            try {
	            	stmt.close();
	            } catch (Exception ex) {

	            }
	            
	            try {
	        
	            	conn.close();
	            } catch (Exception ex) {

	            }
	        }
	        */

		//System.out.println(com.mooo.mycoz.util.IDGenerator.getNowTime());
		//System.out.println(com.mooo.mycoz.util.IDGenerator.getNow());
		//System.out.println(com.mooo.mycoz.util.IDGenerator.getNowYear());
	}
}