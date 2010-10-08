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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.MysqlSQL;
import com.mooo.mycoz.db.sql.SQLProcess;
import com.mooo.mycoz.dbobj.marketmoniter.BusRemotes;
import com.mooo.mycoz.dbobj.mycozBranch.AccessLog;
import com.mooo.mycoz.dbobj.mycozBranch.Example;
import com.mooo.mycoz.dbobj.mycozBranch.FileInfo;
import com.mooo.mycoz.dbobj.mycozBranch.ForumThread;
import com.mooo.mycoz.dbobj.mycozShared.CodeType;
import com.mooo.mycoz.util.DbUtil;
import com.mooo.mycoz.util.StringUtils;

public class Test {

	public static void main(String[] args) {
		
//		String value=null;
//		String str="RefjobID";
//		Pattern p = Pattern.compile("[A-Z]*");
//		Matcher m = p.matcher(str);
//		
//		while(m.find()){
//			value = m.group();
//			System.out.println("value="+value);
//			//result += prefix+m.group().toLowerCase();
//		}
		
		//System.out.println("StringUtils.getCatalog ="+StringUtils.getCatalog(BusRemotes.class,1));
		//DbUtil.type(null,"mycozBranch","FileInfo","name");

		long maxLong = 9223372036854775807L;
		int maxInt = 2147483647;

		try {
			DbProcess dbAction = DbFactory.getInstance();
//			FileInfo fi = new FileInfo();
//			fi.setId(3);
//			fi.setTypeid(4);
//			fi.setName("没有3可能");
//			fi.setDatetime(new Date());
//			fi.setFilepath("filePathd");
//			
//			dbAction.add(fi);
			AccessLog ex = new AccessLog();
			ex.setId(5);
			dbAction.retrieve(ex);
			
			ex.setIp("192.168.0.1");

			dbAction.update(ex);
			
//			dbAction.searchAndRetrieveList(ex);
//			List reList = dbAction.searchAndRetrieveList(ex);
//			AccessLog bean;
//			for (Iterator it = reList.iterator(); it.hasNext();) {
//				bean = (AccessLog)it.next();
//				System.out.println("name:"+bean.getStartdate()+" id:"+bean.getId());
//			}
//			ex.setId(new Random().nextDouble() * maxInt);
//			ex.setName(new Random().nextDouble() * maxInt+"名称");
//			ex.setCreatedate(new Date());
//			dbAction.add(ex);
//			ForumThread forumThread = new ForumThread();
//			System.out.println("count="+dbAction.count(forumThread));
			
//			List reList = dbAction.searchAndRetrieveList(ex);
//			System.out.println("sql:");
//			System.out.println("count="+dbAction.count(ex));
//
//			Iterator it = reList.iterator();
//			FileInfo bean;
//			while (it.hasNext()) {
//				bean = (FileInfo) it.next();
//				System.out.println("name:"+bean.getName()+" id:"+bean.getId());
//			}
		} catch (SQLException e) {
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