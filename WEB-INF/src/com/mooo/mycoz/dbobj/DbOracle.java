package com.mooo.mycoz.dbobj;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.util.BeanUtil;
import com.mooo.mycoz.util.StringUtils;

public class DbOracle extends AbstractDb{
	
	private static Log log = LogFactory.getLog(DBObject.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -4716776899444767709L;

	public List<Object> searchAndRetrieveList() throws SQLException{

		List<Object> retrieveList = null;
		
		//setTable(StringUtils.upperToPrefix(this.getClass().getSimpleName(),null));

		String doSql = searchSQL();
		//doSql += " LIMIT 10";
		System.out.println("doSql:"+doSql);

		if(log.isDebugEnabled()) log.debug("doSql:"+doSql);
		
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		ResultSet result = null;
		boolean closeCon = false;

		try {
			retrieveList = new ArrayList<Object>();
			
			if (connection == null || connection.isClosed()) {
				connection = DbConnectionManager.getConnection();
				closeCon = true;
			}

			stmt = connection.createStatement();
			result = stmt.executeQuery(doSql);

			rsmd = result.getMetaData();
			Object bean;

			while (result.next()) {

				bean = cls.newInstance();

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
