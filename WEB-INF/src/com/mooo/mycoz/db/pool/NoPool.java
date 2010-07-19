package com.mooo.mycoz.db.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author dlaurent
 * 
 *         10 f�vr. 2003 NoPool.java some jdbc driver have internal pool...
 */
public class NoPool extends DbConnectionProvider {

	static protected Log log = LogFactory.getLog(NoPool.class);

	// private static final boolean POOLED = false;

	private static String driver = null;
	private static String url = null;
	private static String user = null;
	private static String pass = null;

	/**
	 * Returns a database connection.
	 */
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	protected void start() {
		Properties props = new Properties();
		props.put("driver", "");
		props.put("server", "");
		props.put("username", "");
		props.put("password", "");
		props.put("minConnections", "");
		props.put("maxConnections", "");
		props.put("logPath", "");
		props.put("connectionTimeout", "");

		driver = props.getProperty("driver");
		url = props.getProperty("server");
		user = props.getProperty("username");
		pass = props.getProperty("password");

		try {
			Class.forName(driver);
		} catch (Exception e) {
			log.fatal("jdbc driver not found:" + driver, e);
		}
		return;
	}

	protected void restart() {
		destroy();
		start();
		return;
	}

	protected void destroy() {
		return;
	}

}
