package com.mooo.mycoz.dbobj;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.pool.DbConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public abstract class DBObject {

	private static Log log = LogFactory.getLog(DBObject.class);
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private String sql = null;

	/**
     	 */
	public DBObject() throws SQLException {
		getConection();
		getStatement();
	} /* DBObject() */

	public void getConection() throws SQLException {
		conn = DbConnectionManager.getConnection();
	} /* getConection() */

	public void getConection(String db) throws SQLException {
		conn = DbConnectionManager.getConnection();
		conn.setCatalog(db);

	} /* getConection(String) */

	public void getStatement() throws SQLException {
		stmt = conn.createStatement();
	} /* getStatement() */

	public ResultSet getResultSet(String sql) throws SQLException {

		if (conn != null && stmt != null)
			rs = stmt.executeQuery(sql);

		return rs;
	} /* getResultSet(String) */

	public void execute(String sql)

	throws SQLException {
		if (conn != null && stmt != null)
			stmt.execute(sql);

	} /* execute(String) */

	public void prepareCall(String sql)

	throws SQLException {

		if (conn != null)
			conn.prepareCall(sql);

	} /* prepareCall(String) */

}
