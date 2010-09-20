/**
 * 
 */
package com.mooo.mycoz.example;

import java.sql.*;
import java.util.Iterator;
import java.util.List;

import com.mooo.mycoz.db.pool.*;
import com.mooo.mycoz.dbobj.DBSession;
import com.mooo.mycoz.dbobj.mycozBranch.Example;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author xpc
 * 
 */

public class DbobjExample {
	private static Log log = LogFactory.getLog(DbobjExample.class);

	public static void main(String[] args) {	
		//long startTime = System.currentTimeMillis();
		DbobjExample td = new DbobjExample();
		try {
			for (int i=0;i<10000;i++)
				td.getCon();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		DbobjExample td = new DbobjExample();
		
		for (int i=0;i<1;i++){
			//td.saveTransactionAction();
			td.saveAction();
			td.searchBeanL();
			//td.saveSql();
		}
		*/
		/*
		long finishTime = System.currentTimeMillis();
		long hours = (finishTime - startTime) / 1000 / 60 / 60;
		long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
		long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
		
		System.out.println(finishTime - startTime);
		System.out.println("expends:   " + hours + ":" + minutes + ":" + seconds);
		*/
		//td.saveBratchBean();
		//td.searchBigData();
		/*

		*/
			//System.out.println("Add SQL:"+bean.AddSQL());
			//System.out.println("Upate SQL:"+bean.UpdateSQL());
			//System.out.println("Delete SQL:"+bean.DeleteSQL());
			//System.out.println("Search SQL:"+bean.SearchSQL());
	}
	
	public void searchBean() throws SQLException{
		Example ex = new Example();
		//ex.setRecord(1, 200000);
		DBSession session = DBSession.getInstance();

		List examples = session.searchAndRetrieveList(ex);
		
		long startTime = System.currentTimeMillis();
		
		for (Iterator it = examples.iterator(); it.hasNext();) {
			ex = (Example)it.next();
			System.out.println("id="+ex.getId()+" name="+ex.getName());
		}
		
		long finishTime = System.currentTimeMillis();
		long hours = (finishTime - startTime) / 1000 / 60 / 60;
		long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
		long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
		
		System.out.println(finishTime - startTime);
		System.out.println("expends:   " + hours + ":" + minutes + ":" + seconds);
	}
	
	public void searchBigData(){
		Connection connection=null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			long startTime = System.currentTimeMillis();

			connection = DbConnectionManager.getConnection();
			
			stmt = connection.createStatement();
			
			rs = stmt.executeQuery("");
			
			while(rs.next()){
				System.out.println("id="+rs.getString("id")+" name="+rs.getString("name"));
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
	
	public void saveBratchSQL(){
		String sql="INSERT INTO Example(name,id) VALUES ('日本女2','677')";
		Connection connection=null;
		Statement stmt = null;
		try {
			connection = DbConnectionManager.getConnection();
			connection.setAutoCommit(false);
			
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			//stmt.executeUpdate(sql);
			//stmt.executeUpdate(sql);
			//stmt.executeUpdate(sql);
			
			connection.commit();
			
		}catch (SQLException e) {
			e.printStackTrace();
			if(log.isDebugEnabled()) log.debug("SQLException:"+e.getMessage());
			System.out.println("SQLException:"+e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				connection.setAutoCommit(true);
				if(stmt != null)
					stmt.close();
				
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void saveBratchBean(){
		Transaction tx = new Transaction();
		try {
			tx.start();
			
			DBSession session = DBSession.getInstance();
			session.setConnection(tx.getConnection());
			
			Example bean = new Example();
			bean.setId(79d);
			bean.setName("79");
			
			session.save(bean);

			bean.setId(80d);
			bean.setName("80");
			
			session.save(bean);
			
			tx.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			if(log.isDebugEnabled()) log.debug("SQLException:"+e.getMessage());
			System.out.println("SQLException:"+e.getMessage());
			tx.rollback();
		}finally {
			tx.end();
		}
	}
	
	public void saveAction(){
		try {
			
			DBSession session = DBSession.getInstance();
			Example bean = new Example();
			bean.setId(IDGenerator.getNextID("Example"));
			bean.setName(IDGenerator.getNextID("Example")+"");
			session.save(bean);
			
		}catch (SQLException e) {
			e.printStackTrace();
			if(log.isDebugEnabled()) log.debug("SQLException:"+e.getMessage());
			System.out.println("SQLException:"+e.getMessage());
		}
	}
	
	public void saveTransactionAction(){
		Transaction tx = new Transaction();
		try {
			tx.start();
			
			DBSession session = DBSession.getInstance();
			session.setConnection(tx.getConnection());
			
			Example bean = new Example();
			bean.setId(IDGenerator.getNextID("Example"));
			bean.setName(IDGenerator.getNextID("Example")+"");
			session.save(bean);
			
			tx.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			if(log.isDebugEnabled()) log.debug("SQLException:"+e.getMessage());
			System.out.println("SQLException:"+e.getMessage());
			tx.rollback();
		}finally {
			tx.end();
		}
	}
	
	public void saveDbObject(){
		try {
			DBSession session = DBSession.getInstance();

			Example bean = new Example();
			
			session.add(bean);
		}catch (SQLException e) {
			e.printStackTrace();
			if(log.isDebugEnabled()) log.debug("SQLException:"+e.getMessage());
			System.out.println("SQLException:"+e.getMessage());
		}
	}
	
	public void saveSql(){
		String sql="INSERT INTO Example(id,name) VALUES (?,?)";
		Connection connection=null;
        PreparedStatement pstmt = null;
        try {
			connection = DbConnectionManager.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setDouble(1, (IDGenerator.getNextID("Example") * 1d));
            pstmt.setString(2, IDGenerator.getNextID("Example").toString());
            pstmt.execute();
		}catch (SQLException e) {
			e.printStackTrace();
			if(log.isDebugEnabled()) log.debug("SQLException:"+e.getMessage());
			System.out.println("SQLException:"+e.getMessage());
	   }finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void searchBeanL(){
		try {
			DBSession session = DBSession.getInstance();

			Example bean = new Example();
			session.searchAndRetrieveList(bean);
			
		}catch (SQLException e) {
			e.printStackTrace();
			if(log.isDebugEnabled()) log.debug("SQLException:"+e.getMessage());
			System.out.println("SQLException:"+e.getMessage());
		}
	}
	public void getCon() throws InterruptedException{

	long startTime = System.currentTimeMillis();

	Connection conn=null;
	Statement stmt=null;
	try {
			conn=DbConnectionManager.getConnection();
			stmt = conn.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {

        try {  stmt.close(); }
        catch (Exception e) { e.printStackTrace(); }
        try {  conn.close();   }
        catch (Exception e) { e.printStackTrace(); }
        
    	long finishTime = System.currentTimeMillis();
		System.out.println(finishTime - startTime);

	}
	}
}
