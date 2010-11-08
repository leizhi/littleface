package com.mooo.mycoz.dbobj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.DbMultiBulildSQL;
import com.mooo.mycoz.db.sql.MultiSQLProcess;
import com.mooo.mycoz.util.BeanUtil;
import com.mooo.mycoz.util.DbUtil;
import com.mooo.mycoz.util.StringUtils;

public class MultiDBObject extends DbMultiBulildSQL implements MultiSQLProcess{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4716776899444767709L;

	public List<Object> searchAndRetrieveList() throws SQLException{
		return searchAndRetrieveList(null);
	}
	
	public List searchAndRetrieveListMap(Connection connection) throws SQLException{
		long startTime = System.currentTimeMillis();

		List<Object> retrieveList = null;
		String doSql = searchSQL();
		
		Connection myConn = null;
		Statement stmt = null;
		boolean isClose = true;

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
			ResultSet result = stmt.executeQuery(doSql);
			ResultSetMetaData rsmd = result.getMetaData();

			String key;
			String value;
			String catalog,table,column;
			
			while (result.next()) {
				Map allRow = new HashMap();
				for (Entry<String, String> entry : tables.entrySet()) {
					value = entry.getValue();

					Map bean = new HashMap();
					for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
						catalog = rsmd.getCatalogName(i);
						table = rsmd.getTableName(i);
						column = rsmd.getColumnName(i);

						int type = DbUtil.type(myConn, catalog, table,StringUtils.upperToPrefix(column, null));

						if (value.equals(catalog + "." + table)) {
							if (type == Types.TIMESTAMP) {
								bean.put(column, result.getTimestamp(i));
							} else {
								bean.put(column, result.getString(i));
							}
						}
					}// for column
					allRow.put(entry.getKey(), bean);
				}// for bean
				
				retrieveList.add(allRow);
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
				if(isClose)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		long finishTime = System.currentTimeMillis();
		long hours = (finishTime - startTime) / 1000 / 60 / 60;
		long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
		long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
		
		System.out.println(finishTime - startTime);
		System.out.println("search MultiDBObject expends:   " + hours + ":" + minutes + ":" + seconds);
		return retrieveList;
	}
	
	public List<Object> searchAndRetrieveList(Connection connection) throws SQLException{
		long startTime = System.currentTimeMillis();

		List<Object> retrieveList = null;
		String doSql = searchSQL();
		
		Connection myConn = null;
		Statement stmt = null;
		boolean isClose = true;

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
			ResultSet result = stmt.executeQuery(doSql);
			ResultSetMetaData rsmd = result.getMetaData();

			String key;
			String catalog,table,column;
			
			while (result.next()) {
				
				Map<String, Object> allRow = new HashMap<String, Object>();
				for (Entry<String, Class>  entry:objs.entrySet()) {
					key = entry.getKey();
					
					Class<?> cls = objs.get(key);
					Object bean = cls.newInstance();
					
					allRow.put(key, bean);
				}
				
				for (int i=1; i < rsmd.getColumnCount()+1; i++) {
					
					catalog = rsmd.getCatalogName(i);
					table = rsmd.getTableName(i);
					
					column = rsmd.getColumnName(i);

					int type = DbUtil.type(myConn,catalog,table,StringUtils.upperToPrefix(column,null));
					if(allRow.containsKey(StringUtils.toLowerFirst(table))){
						Object bean = allRow.get(StringUtils.toLowerFirst(table));
						
						if(type == Types.TIMESTAMP){
							BeanUtil.bindProperty(bean,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
						}else {
							BeanUtil.bindProperty(bean,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));	
						}					
					}
				}
				retrieveList.add(allRow);
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
				if(isClose)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		long finishTime = System.currentTimeMillis();
		long hours = (finishTime - startTime) / 1000 / 60 / 60;
		long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
		long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
		
		System.out.println(finishTime - startTime);
		System.out.println("search expends:   " + hours + ":" + minutes + ":" + seconds);
		return retrieveList;
	}
}
