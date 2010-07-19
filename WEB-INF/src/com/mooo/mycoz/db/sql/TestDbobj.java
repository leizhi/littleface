/**
 * 
 */
package com.mooo.mycoz.db.sql;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import com.mooo.mycoz.db.pool.*;
import com.mooo.mycoz.bean.accounting.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.cache.*;

import java.util.*;

/**
 * @author xpc
 * 
 */

public class TestDbobj {
	private static Log log = LogFactory.getLog(TestDbobj.class);

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		try {
			/*
			 * String sql = "";
			 * 
			 * SearchSQL ss = SearchSQL.getInstance();
			 * //ss.setDatabase("xpcBranch"); ss.setTable("JobNote");
			 * ss.setLike("ClientJobID", "AE"); ss.setField("GroupID", 254585);
			 * ss.setGroupBy("ID"); ss.setGroupBy("GroupID");
			 * ss.setOrderBy("ID", "DESC"); ss.setOrderBy("Date", "ASC");
			 * ss.setGreaterEqual("Date", "2007-09-09"); ss.setRecord(0, 10);
			 * sql = ss.buildSQL();
			 * 
			 * System.out.println("buildSQL=" + sql);
			 * 
			 * con = DbConnectionManager.getConnection(); stmt =
			 * con.createStatement(); ResultSet rs = stmt.executeQuery(sql);
			 * 
			 * while (rs.next()){ System.out.println(rs.getInt(1)); } UpdateSQL
			 * us = UpdateSQL.getInstance(); //us.setDatabase("xpcBranch");
			 * us.setTable("JobNote"); us.setField("ShortName", "");
			 * us.setField("ID", ""); us.setUpdate("ID", "2");
			 * us.setUpdate("UserID", "2"); sql = us.buildSQL();
			 * 
			 * System.out.println("buildSQL=" + sql);
			 */
			 
			 /*
			MultiSQL ms = MultiSQLFactory.getInstance();
			// ms.setCatalog("xpcBranch");
			ms.addTable("JobNote", "jn");
			ms.addTable("ClientJob", "cj");

			ms.setForeignKey("jn", "ClientJobID", "cj", "ID");
			ms.setForeignKey("jn", "BranchID", "cj", "BranchID");

			ms.setRetrieveField("jn", "ID");
			ms.setRetrieveField("cj", "ID");
			ms.setRecord(80, 90);
			ms.setLike("cj", "ID", "SE");

			String sql = ms.buildSQL();
			System.out.println("buildSQL=" + sql);
			DbSession.getInstance().executeQuery(sql);
			ResultSet rs = DbSession.getInstance().getResultSet();

			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}

			ms = MultiSQLFactory.getInstance();
			// ms.setCatalog("xpcBranch");
			ms.addTable("JobNote", "jn");
			ms.addTable("ClientJob", "cj");

			ms.setForeignKey("jn", "ClientJobID", "cj", "ID");
			ms.setForeignKey("jn", "BranchID", "cj", "BranchID");

			ms.setRetrieveField("jn", "ID");
			ms.setRetrieveField("cj", "ID");
			ms.setRecord(80, 82);
			ms.setLike("cj", "ID", "SE");

			sql = ms.buildSQL();
			System.out.println("buildSQL=" + sql);

			DbSession.getInstance().executeQuery(sql);
			rs = DbSession.getInstance().getResultSet();
			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}

			ARBean ar = ARBean.getInstance();
			ar.getJobNoteNo();
*/
			//MultiDBObject mdb = MultiDBObject.getInstance();
			MultiDBObject mdb = new MultiDBObject();
			mdb.addTable("JobNote", "jn");
			mdb.addTable("ClientJob", "cj");

			mdb.setForeignKey("jn", "ClientJobID", "cj", "ID");
			mdb.setForeignKey("jn", "BranchID", "cj", "BranchID");

			mdb.setRetrieveField("jn", "ID");
			mdb.setRetrieveField("cj", "ID");
			mdb.setRecord(80, 82);
			mdb.setLike("cj", "ID", "SE");
			
			Map map = null;
			System.out.println("SQL = " + mdb.buildSQL());
			//mdb.debug();
			System.out.println("cache list = " + mdb.searchAndRetrieveList());
			for(Iterator it = mdb.searchAndRetrieveList().iterator(); it.hasNext();){
   
  				map = (Map)it.next();
  			 System.out.println((String)map.get("jn.ID"));

  }
			/*
			mdb.searchAndRetrieveList();
			
			CacheManager cacheManager = CacheManager.getInstance();
			DBObjectList mobject=(DBObjectList)cacheManager.get(CacheManager.DBOBJ_CACHE,mdb.buildSQL());
			System.out.println("cache list = " + mobject.getObjectList());
			for(Iterator it = mobject.getObjectList().iterator(); it.hasNext();){
   
  				map = (Map)it.next();
  			 System.out.println((String)map.get("jn.ID"));

  		}
  		*/
			mdb = new MultiDBObject();
			mdb.addTable("JobNote", "jn");
			mdb.addTable("ClientJob", "cj");

			mdb.setForeignKey("jn", "ClientJobID", "cj", "ID");
			mdb.setForeignKey("jn", "BranchID", "cj", "BranchID");

			mdb.setRetrieveField("jn", "ID");
			mdb.setRetrieveField("cj", "ID");
			mdb.setRecord(80, 82);
			//mdb.setLike("cj", "ID", "SE");
			
			System.out.println("SQL = " + mdb.buildSQL());
			//mdb.debug();
			/*mdb.searchAndRetrieveList();
			
			cacheManager = CacheManager.getInstance();
			mobject=(DBObjectList)cacheManager.get(CacheManager.DBOBJ_CACHE,mdb.buildSQL());
			System.out.println("cache list = " + mobject.getObjectList());
			for(Iterator it = mobject.getObjectList().iterator(); it.hasNext();){
   
  				map = (Map)it.next();
  			 System.out.println((String)map.get("jn.ID"));

  		}  		*/
						System.out.println("cache list = " + mdb.searchAndRetrieveList());
			for(Iterator it = mdb.searchAndRetrieveList().iterator(); it.hasNext();){
   
  				map = (Map)it.next();
  			 System.out.println((String)map.get("jn.ID"));

  }

  /*
		 rs = DbSession.getInstance().getResultSet();
			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
			*/
		//} catch (SQLException sqle) {
		//	System.err.println("Error in DbForum.groupsWithPermission:" + sqle);
	//		sqle.printStackTrace();
	//	} finally {
	/*
			try {
				// pstmt.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			*/
	//	}
	} catch (Exception e) {
			System.err.println("Error in DbForum.groupsWithPermission:" + e);
			e.printStackTrace();
	}
	}

}
