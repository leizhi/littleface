package com.mooo.mycoz.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.sql.SQLActionFactory;
import com.mooo.mycoz.util.BeanUtil;
import com.mooo.mycoz.util.StringUtils;

public class DbOperation extends SQLActionFactory implements DbAction{
	
	private static Log log = LogFactory.getLog(DbOperation.class);
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -150097142399145015L;

	@Override
	public List<Object> searchAndRetrieveList(Object entity)
			throws SQLException {
		
		List<Object> retrieveList = null;
		
		String doSql = getInstance().searchSQL(entity);
		//doSql += " LIMIT 10";
		System.out.println("doSql:"+doSql);

		if(log.isDebugEnabled()) log.debug("doSql:"+doSql);
		
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		ResultSet result = null;

		try {
			retrieveList = new ArrayList<Object>();
			
			stmt = getInstance().getConnection().createStatement();
			result = stmt.executeQuery(doSql);

			rsmd = result.getMetaData();
			Object bean;

			while (result.next()) {

				bean = entity.getClass().newInstance();

				for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
					BeanUtil.bindProperty(bean,StringUtils.prefixToUpper(rsmd.getColumnName(i)),
							result.getString(i), null);
				}
				retrieveList.add(bean);
			}
			//addCache(doSql, retrieveList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (result != null)
					result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			getInstance().close();

		}
		return retrieveList;
	}
		
	@Override
	public synchronized Integer count(Object entity) throws SQLException {
		
		String doSql = getInstance().countSQL(entity);
		
		if(log.isDebugEnabled())log.debug("doSql="+doSql);
		
		Statement stmt = null;
		ResultSet result = null;
		int total=0;
		
		try {
					
			stmt = getInstance().getConnection().createStatement();
			result = stmt.executeQuery(doSql);
			
			if(result.next())
				total = result.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (result != null)
					result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			getInstance().close();
		}
		return total;
	}


	@Override
	public synchronized void add(Object entity) throws SQLException {
		Statement stmt = null;
		String doSql = getInstance().addSQL(entity);
		try{		
			stmt = getInstance().getConnection().createStatement();
			stmt.execute(doSql);
		}finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//sqlAction.close();
		}
	}

	public synchronized void save(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public synchronized void delete(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public synchronized void update(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public synchronized void retrieve(Object entity) throws SQLException {
		// TODO Auto-generated method stub
	}
	
}
