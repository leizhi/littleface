package com.mooo.mycoz.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

import com.mooo.mycoz.util.DBMap;
import com.mooo.mycoz.util.DBNode;

public class MysqlConnection {

	private static Log log = LogFactory.getLog(MysqlConnection.class);
	private static Connection xpcConection = null;

	/*
	 * private String driver = "org.gjt.mm.mysql.Driver"; private Stringurl=
	 * "jdbc:mysql://localhost/xpcwBranch?useUnicode=true&amp;characterEncoding=utf8"
	 * ; private String name = "xpcwuser"; private String password = "click89";
	 */

	/*
	 * *getConection()
	 */
	public static Connection getConection() {
		return getConection("default");
	}

	/*
	 * *getConection(String desc)
	 */
	public static Connection getConection(String desc) {
		try {
			String var = "";
			DBNode dNode = null;
			String driver = null;
			String url = null;
			String name = null;
			String password = null;

			Hashtable<?, ?> dbList = DBMap.getDbList();
			Enumeration<?> e = dbList.keys();
			while (e.hasMoreElements()) {
				var = (String) e.nextElement();
				if (var.equals(desc)) {
					dNode = (DBNode) dbList.get(var);
					driver = dNode.getDriver();
					url = dNode.getUrl();
					name = dNode.getName();
					password = dNode.getPassword();
				}
			}

			Class.forName(driver).newInstance();
			xpcConection = DriverManager.getConnection(url, name, password);

		} catch (SQLException sqlEx) {
			if (log.isDebugEnabled())
				log.debug("SQLException: " + sqlEx.getMessage() + "SQLState: "
						+ sqlEx.getSQLState() + "VendorError: "
						+ sqlEx.getErrorCode());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return xpcConection;
	}

	/*
	 * *clean xpcConection
	 */
	public static void clean() {
		try {
			if (xpcConection != null) {
				try {
					xpcConection.close();
				} catch (SQLException sqlEx) {
					xpcConection = null;
				}
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
	}/* clean() */
}
