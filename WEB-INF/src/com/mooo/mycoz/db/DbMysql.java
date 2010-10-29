package com.mooo.mycoz.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.MysqlSQL;
import com.mooo.mycoz.util.BeanUtil;
import com.mooo.mycoz.util.StringUtils;

public class DbMysql extends MysqlSQL implements DbProcess{
	private static Log log = LogFactory.getLog(DbMysql.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Object> searchAndRetrieveList(Object entity)
			throws SQLException {
		return searchAndRetrieveList(null, entity);
	}
	@Override
	public List<Object> searchAndRetrieveList(Connection connection,
			Object entity) throws SQLException {
		List<Object> retrieveList = null;

		String doSql = searchSQL(entity);
		// doSql += " LIMIT 10";
		System.out.println("doSql:" + doSql);

		if (log.isDebugEnabled())
			log.debug("doSql:" + doSql);

		Connection myConn = null;
		boolean isClose = true;

		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		ResultSet result = null;

		try {
			retrieveList = new ArrayList<Object>();
			
			if(connection != null){
				myConn = connection;
				isClose = false;
			} else {
				myConn = DbConnectionManager.getConnection();
				isClose = true;
			}

			stmt = myConn.createStatement();
			result = stmt.executeQuery(doSql);

			rsmd = result.getMetaData();
			Object bean;
			int type=0;

			while (result.next()) {

				bean = entity.getClass().newInstance();

				for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
					type = rsmd.getColumnType(i);
					
					if(type == Types.TIMESTAMP){
						BeanUtil.bindProperty(bean,
								StringUtils.prefixToUpper(rsmd.getColumnName(i),null),
								result.getTimestamp(i));
					}else {
						BeanUtil.bindProperty(bean,
								StringUtils.prefixToUpper(rsmd.getColumnName(i),null),
								result.getString(i));	
					}

				}
				retrieveList.add(bean);
			}
			// addCache(doSql, retrieveList);
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

			try {
				if(isClose)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retrieveList;
	}
	@Override
	public Integer count(Object entity) throws SQLException {
		return count(null,entity);
	}

	@Override
	public Integer count(Connection connection,Object entity) throws SQLException {
		String doSql = countSQL(entity);
		
		if(log.isDebugEnabled())log.debug("doSql="+doSql);
		Connection myConn = null;
		boolean isClose = true;
		
		Statement stmt = null;
		ResultSet result = null;
		int total=0;
		
		try {
			if(connection != null){
				myConn = connection;
				isClose = false;
			} else {
				myConn = DbConnectionManager.getConnection();
				isClose = true;
			}
			
			stmt = myConn.createStatement();
			result = stmt.executeQuery(doSql);
			
			if(result.next())
				total = result.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
					result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(isClose)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return total;
	}
	
	@Override
	public void add(Object entity) throws SQLException {
		add(null,entity);
	}
	
	@Override
	public void add(Connection connection,Object entity) throws SQLException {
		Connection myConn = null;
		boolean isClose = true;
		
		Statement stmt = null;
		String doSql = addSQL(entity);
		try{
			if(connection != null){
				myConn = connection;
				isClose = false;
			} else {
				myConn = DbConnectionManager.getConnection();
				isClose = true;
			}
			
			stmt = myConn.createStatement();
			stmt.execute(doSql);
		}finally {

			try {
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(isClose)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void delete(Object entity) throws SQLException {
		delete(null,entity);
	}

	@Override
	public void delete(Connection connection,Object entity) throws SQLException {
		Connection myConn = null;
		boolean isClose = true;
		
		Statement stmt = null;
		String doSql = deleteSQL(entity);
		try{
			if(connection != null){
				myConn = connection;
				isClose = false;
			} else {
				myConn = DbConnectionManager.getConnection();
				isClose = true;
			}
			
			stmt = myConn.createStatement();
			stmt.execute(doSql);
		}finally {

			try {
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(isClose)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void update(Object entity) throws SQLException {
		update(null,entity);
	}
	@Override
	public void update(Connection connection,Object entity) throws SQLException {
		Connection myConn = null;
		boolean isClose = true;
		
		Statement stmt = null;
		String doSql = updateSQL(entity);
		try{
			if(connection != null){
				myConn = connection;
				isClose = false;
			} else {
				myConn = DbConnectionManager.getConnection();
				isClose = true;
			}
			
			stmt = myConn.createStatement();
			stmt.execute(doSql);
		}finally {

			try {
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(isClose)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	@Override
	public void retrieve(Object entity) throws SQLException {
		retrieve(null,entity);
	}
	@Override
	public void retrieve(Connection connection,Object entity) throws SQLException {

		String doSql = searchSQL(entity);
		
		 doSql += " LIMIT 1";

		System.out.println("doSql:" + doSql);

		if (log.isDebugEnabled())
			log.debug("doSql:" + doSql);

		Connection myConn = null;
		boolean isClose = true;

		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		ResultSet result = null;

		try {
			if(connection != null){
				myConn = connection;
				isClose = false;
			} else {
				myConn = DbConnectionManager.getConnection();
				isClose = true;
			}

			stmt = myConn.createStatement();
			result = stmt.executeQuery(doSql);

			rsmd = result.getMetaData();
			int type=0;
			
			while (result.next()) {
				for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
					type = rsmd.getColumnType(i);
					
					if(type == Types.TIMESTAMP){
						BeanUtil.bindProperty(entity,
								StringUtils.prefixToUpper(rsmd.getColumnName(i),null),
								result.getTimestamp(i));
					}else {
						BeanUtil.bindProperty(entity,
								StringUtils.prefixToUpper(rsmd.getColumnName(i),null),
								result.getString(i));
					}
				}
			}
			
			// addCache(doSql, retrieveList);
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

			try {
				if(isClose)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}