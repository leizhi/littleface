package com.mooo.mycoz.dbobj;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.jdbc.MysqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public abstract class DBObject {

    	private static Log log = LogFactory.getLog(DBObject.class);
	private Connection DBConection = null;
    	private Statement stmt = null;
    	private ResultSet rs = null;
    	private String sql = null;

    	/**
     	 */
    	public DBObject()
             throws SQLException {
		getConection();
		getStatement();
    	} /* DBObject() */

	public void getConection() 
             throws SQLException {
		DBConection = MysqlConnection.getConection();
    	   } /* getConection() */

	public void getConection(String db)
             throws SQLException {
		DBConection = MysqlConnection.getConection(db);
    	   } /* getConection(String) */

	public void getStatement()
             throws SQLException {
		stmt = DBConection.createStatement();
    	   } /* getStatement() */

	public ResultSet getResultSet(String sql)
             throws SQLException {

		if (DBConection != null && stmt!=null )
			rs = stmt.executeQuery(sql);

		return rs;
    	   } /* getResultSet(String) */

	public void execute(String sql)

             throws SQLException {
		if (DBConection != null && stmt!=null )
			stmt.execute(sql);

    	   } /* execute(String) */

	public void prepareCall(String sql)

             throws SQLException {

		if (DBConection != null)
			DBConection.prepareCall(sql);

    	   } /* prepareCall(String) */

}
