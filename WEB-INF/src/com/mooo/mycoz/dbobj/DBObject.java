package com.mooo.mycoz.dbobj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.mooo.mycoz.cache.CacheManager;
import com.mooo.mycoz.db.sql.SQLFactory;
import com.mooo.mycoz.db.sql.SQLProcess;
import com.mooo.mycoz.util.BeanUtil;
import com.mooo.mycoz.util.StringUtils;

public class DBObject implements DbProcess,SQLProcess{
	
	private static Log log = LogFactory.getLog(DBObject.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -4716776899444767709L;
	
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
	
	public SQLProcess sqlProcess;

	public DBObject(){
		sqlProcess = SQLFactory.getInstance();
	}
	
	public List<Object> searchAndRetrieveList(String sql, Class<?> obj) {
		List<Object> retrieveList = null;
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		ResultSet result = null;

		try {
			retrieveList = new ArrayList<Object>();

			
			stmt = sqlProcess.getConnection().createStatement();
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
			
			sqlProcess.close();


		}
		
		return retrieveList;
	}

	public List<Object> searchAndRetrieveList(String sql) {
		List<Object> retrieveList = null;
		Statement stmt = null;
		ResultSetMetaData rsmd = null;

		try {
			retrieveList = new ArrayList<Object>();
			
			stmt = sqlProcess.getConnection().createStatement();

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
			
			sqlProcess.close();


		}
		
		return retrieveList;
	}
	
	public List searchAndRetrieveList() throws SQLException{

/*
		Object obj = getCache(doSql);

		if(obj != null) {
			retrieveList =  (List<Object>) obj;
		}
		
		if(retrieveList != null)
			return retrieveList;
	*/	
		List<Object> retrieveList = null;
		//sqlProcess.setCatalog(catalog);
		sqlProcess = SQLFactory.getInstance();
		sqlProcess.setTable(StringUtils.upperToPrefix(this.getClass().getSimpleName(),null));

		String doSql = sqlProcess.searchSQL();

		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		ResultSet result = null;

		try {
			retrieveList = new ArrayList<Object>();
			
			sqlProcess = SQLFactory.getInstance();

			stmt = sqlProcess.getConnection().createStatement();

			result = stmt.executeQuery(doSql);

			rsmd = result.getMetaData();
			Object bean;

			while (result.next()) {

				bean = this.getClass().newInstance();

				for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
					BeanUtil.bindProperty(bean,
							StringUtils.prefixToUpper(rsmd.getColumnName(i)),
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
			
			sqlProcess.close();


		}
		return retrieveList;
	}

	public Integer count() throws SQLException{
		
		String doSql = sqlProcess.countSQL();
		
		if(log.isDebugEnabled())log.debug("doSql="+doSql);
		
		Statement stmt = null;
		ResultSet result = null;
		int total=0;
		
		try {
			
			stmt = sqlProcess.getConnection().createStatement();

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
			
			sqlProcess.close();


		}
		return total;
	}
	
	public void add() throws SQLException {
		Statement stmt = null;
		try{
			stmt = sqlProcess.getConnection().createStatement();

			stmt.execute(sqlProcess.addSQL());
		}finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			sqlProcess.close();


		}
	}
	
	public void delete() throws SQLException {
		Statement stmt = null;
		try{
			stmt = sqlProcess.getConnection().createStatement();

			stmt.execute(sqlProcess.deleteSQL());

		}finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			sqlProcess.close();

		}
	}
	public void update() throws SQLException{
		Statement stmt = null;
		try{
			stmt = sqlProcess.getConnection().createStatement();

			stmt.execute(sqlProcess.updateSQL());
		}finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			sqlProcess.close();

		}
	}

	public void retrieve() throws SQLException{
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		ResultSet result = null;

		try{
			stmt = sqlProcess.getConnection().createStatement();

			result = stmt.executeQuery(sqlProcess.searchSQL());
			
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
			
			sqlProcess.close();

		}
	}

	@Override
	public String getCatalog() {
		return sqlProcess.getCatalog();
	}

	@Override
	public void setCatalog(String catalog) {
		sqlProcess.setCatalog(catalog);
	}

	@Override
	public Connection getConnection() {
		return sqlProcess.getConnection();
	}

	@Override
	public void setConnection(Connection connection) {
		sqlProcess.setConnection(connection);
	}

	@Override
	public void close() {
		sqlProcess.close();
	}

	@Override
	public String getTable() {
		return sqlProcess.getTable();
	}

	@Override
	public void setTable(String table) {
		sqlProcess.setTable(table);
	}

	@Override
	public void setField(String field, String value) {
		sqlProcess.setField(field, value);
	}

	@Override
	public void setField(String field, Integer value) {
		sqlProcess.setField(field, value);
	}

	@Override
	public void setLike(String field, String value) {
		sqlProcess.setLike(field, value);
	}

	@Override
	public void setGreaterEqual(String field, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLessEqual(String field, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGroupBy(String field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrderBy(String field, String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRecord(int recordStart, int recordEnd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String addSQL() {
		// TODO Auto-generated method stub
		return sqlProcess.addSQL();
	}

	@Override
	public String deleteSQL() {
		// TODO Auto-generated method stub
		return sqlProcess.deleteSQL();
	}

	@Override
	public String updateSQL() {
		// TODO Auto-generated method stub
		return sqlProcess.updateSQL();
	}

	@Override
	public String searchSQL() {
		// TODO Auto-generated method stub
		return sqlProcess.searchSQL();
	}

	@Override
	public String countSQL() {
		// TODO Auto-generated method stub
		return sqlProcess.countSQL();
	}

	@Override
	public String addSQL(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteSQL(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateSQL(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchSQL(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String countSQL(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
