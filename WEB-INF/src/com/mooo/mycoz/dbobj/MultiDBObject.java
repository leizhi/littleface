package com.mooo.mycoz.dbobj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	public Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {

		this.connection = connection;
	}
	

	public List searchAndRetrieveList() {
		List<Object> retrieveList = null;
		String doSql = searchSQL();
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
			ResultSet result = stmt.executeQuery(doSql);
			
			rsmd = result.getMetaData();
			Map re;

			String key;
			String value;
			Class cls;
			Object bean;
			String catalog,table,column;

			while (result.next()) {
				re = new HashMap();
				
				for (Iterator<?> it = objs.keySet().iterator(); it.hasNext();) {
					key = (String)it.next();
					cls = objs.get(key);
					bean = cls.newInstance();

					for (int i=1; i < rsmd.getColumnCount()+1; i++) {
						catalog = rsmd.getCatalogName(i);
						table = rsmd.getTableName(i);
						
						value = tables.get(key);
						column = rsmd.getColumnName(i);

						int type = DbUtil.type(null,catalog,table,StringUtils.upperToPrefix(column,null));
						
						if(value.equals(catalog+"."+table)){
							//column = rsmd.getColumnName(i).toLowerCase();
							//BeanUtil.bindProperty(bean, StringUtils.prefixToUpper(column),result.getString(i));
							if(type == Types.TIMESTAMP){
								BeanUtil.bindProperty(bean,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
							}else {
								BeanUtil.bindProperty(bean,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));	
							}
						}
					}
					re.put(key, bean);
				}
				retrieveList.add(re);
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

}
