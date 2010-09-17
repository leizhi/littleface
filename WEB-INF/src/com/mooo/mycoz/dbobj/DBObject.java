package com.mooo.mycoz.dbobj;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.mooo.mycoz.cache.CacheManager;
import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.OracleSQL;
import com.mooo.mycoz.util.BeanUtil;
import com.mooo.mycoz.util.ReflectUtil;
import com.mooo.mycoz.util.StringUtils;

//public class DBObject extends DbBulildSQL{
public class DBObject extends OracleSQL{
	
	private static Log log = LogFactory.getLog(DBObject.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -4716776899444767709L;
	public Connection connection;
	
	//private static final String CACHE_TYPE = "dbobj";
	//private static CacheManager cacheManager = CacheManager.getInstance();
/*
	public void addCache(String key, Object object){
		cacheManager.add(CACHE_TYPE, key, object);
	}

	public Object getCache(String key){
		return cacheManager.get(CACHE_TYPE, key);
	}
	*/
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {

		this.connection = connection;
	}
	
	public List<Object> searchAndRetrieveList(String sql, Class<?> obj) {
		List<Object> retrieveList = null;
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		ResultSet result = null;
		boolean closeCon = false;

		try {
			retrieveList = new ArrayList<Object>();

			if(connection == null){
				connection = DbConnectionManager.getConnection();
				closeCon=true;
			}
			
			stmt = connection.createStatement();
			result = stmt.executeQuery(sql);
			
			rsmd = result.getMetaData();
			Object bean;

			while (result.next()) {
				bean = obj.newInstance();
				
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					BeanUtil.bindProperty(bean, StringUtils.prefixToUpper(rsmd.getColumnName(i + 1)),result.getString(i + 1), null);
				}
				retrieveList.add(bean);
			}

			
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
				if (connection != null && closeCon)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return retrieveList;
	}

	public List<Object> searchAndRetrieveList(String sql) {
		List<Object> retrieveList = null;
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		boolean closeCon = false;

		try {
			retrieveList = new ArrayList<Object>();
			
			if(connection == null){
				connection = DbConnectionManager.getConnection();
				closeCon=true;
			}
			
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			rsmd = rs.getMetaData();
			Object bean;

			while (rs.next()) {
				bean = this.getClass().newInstance();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					BeanUtil.bindProperty(bean, StringUtils.prefixToUpper(rsmd.getColumnName(i + 1)),rs.getString(i + 1), null);
				}
				retrieveList.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (connection != null && closeCon)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return retrieveList;
	}
	
	public List<Object> searchAndRetrieveList() throws SQLException{

/*
		Object obj = getCache(doSql);

		if(obj != null) {
			retrieveList =  (List<Object>) obj;
		}
		
		if(retrieveList != null)
			return retrieveList;
	*/	
		beanFillField();
		List<Object> retrieveList = null;

		String doSql = searchSQL();

		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		ResultSet result = null;
		boolean closeCon = false;

		try {
			retrieveList = new ArrayList<Object>();
			
			if(connection == null){
				connection = DbConnectionManager.getConnection();
				closeCon=true;
			}
			
			if (connection == null)
				new SQLException("can't do getConnection");
			
			try {
			stmt = connection.createStatement();
			} catch (Exception e) {
				if(log.isDebugEnabled())log.debug("Exception:"+e.getMessage());
			}
			result = stmt.executeQuery(doSql);

			rsmd = result.getMetaData();
			Object bean;

			while (result.next()) {
				bean = this.getClass().newInstance();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					BeanUtil.bindProperty(bean, StringUtils.prefixToUpper(rsmd
							.getColumnName(i + 1)), result.getString(i + 1), null);
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
			
			try {
				if (connection != null && closeCon)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return retrieveList;
	}

	public Integer count() throws SQLException{
		
		beanFillField();
		
		String doSql = countSQL();
		
		if(log.isDebugEnabled())log.debug("doSql="+doSql);

		Statement stmt = null;
		ResultSet result = null;
		boolean closeCon = false;
		int total=0;
		
		try {
			
			if(connection == null){
				connection = DbConnectionManager.getConnection();
				closeCon=true;
			}
			
			stmt = connection.createStatement();
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
			
			try {
				if (connection != null && closeCon)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return total;
	}
	
	public void add() throws SQLException {
		beanFillField();
		
		boolean closeCon = false;
		Statement stmt = null;
		try{
			if(connection == null){
				connection = DbConnectionManager.getConnection();
				closeCon=true;
			}
			
			stmt = connection.createStatement();
			stmt.execute(addSQL());
		}finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (connection != null && closeCon)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	public void delete() throws SQLException {
		beanFillField();
		Statement stmt = null;
		boolean closeCon = false;

		try{
			if(connection == null){
				connection = DbConnectionManager.getConnection();
				closeCon=true;
			}
			
			stmt = connection.createStatement();
			stmt.execute(deleteSQL());

		}finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (connection != null && closeCon)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public synchronized void update() throws SQLException{
		beanFillField();
		boolean closeCon = false;

		Statement stmt = null;
		try{
			if(connection == null){
				connection = DbConnectionManager.getConnection();
				closeCon=true;
			}
			
			stmt = connection.createStatement();
			stmt.execute(updateSQL());
		}finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (connection != null && closeCon)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void beanFillField(){
		try {
			List<String> methods = ReflectUtil.getMethodNames(this.getClass());
			
			setTable(StringUtils.upperToPrefix(this.getClass().getSimpleName()));
			
			initialization();
			
			String method;
			String field;
			
			for (Iterator<String> it = methods.iterator(); it.hasNext();) {
				method = it.next();
				if(method.indexOf("get")==0){
					
					Method getMethod;
					getMethod = this.getClass().getMethod(method);
					
					Object obj = getMethod.invoke(this);
					
					if(obj !=null) {
						field = method.substring(method.indexOf("get")+3);
						if(obj.getClass().isAssignableFrom(Integer.class))
							setField(StringUtils.upperToPrefix(field), (Integer)obj);
						else if(obj.getClass().isAssignableFrom(String.class)){
							setField(StringUtils.upperToPrefix(field), (String)obj);
						}else if(obj.getClass().isAssignableFrom(Date.class)){
							setField(StringUtils.upperToPrefix(field), (Date)obj);
						}else if(obj.getClass().isAssignableFrom(Double.class)){
							setField(StringUtils.upperToPrefix(field), (Double)obj);
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void retrieve() throws SQLException{
		beanFillField();
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		ResultSet result = null;
		boolean closeCon = false;

		try{
			if(connection == null){
				connection = DbConnectionManager.getConnection();
				closeCon=true;
			}
			
			stmt = connection.createStatement();
			result = stmt.executeQuery(searchSQL());
			
			rsmd = result.getMetaData();
			String value;
			while (result.next()) {
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					value = rsmd.getColumnName(i + 1);
					BeanUtil.bindProperty(this, StringUtils.prefixToUpper(value), result.getString(i + 1), null);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();		
		}finally {

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
				if (connection != null && closeCon)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
