package com.manihot.xpc.tools;
/**
 * 
 */

/**
 * @author xpc
 * 
 */
import java.util.*; // import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import com.mooo.mycoz.db.pool.*;
import com.mooo.mycoz.db.sql.*;
import org.apache.log4j.Category;

public class DBTest {
	// private static Log log = LogFactory.getLog(DBTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Connection connection = DbConnectionManager.getConnection();
			// Gets the database metadata
			DatabaseMetaData dbmd = connection.getMetaData();

			// Specify the type of object; in this case we want tables
			String[] types = { "TABLE" };
			ResultSet resultSet = dbmd.getTables(null, null, "%", types);

			// Get the table names
			while (resultSet.next()) {
				// Get the table name
				String tableName = resultSet.getString(3);

				// Get the table's catalog and schema names (if any)
				String tableCatalog = resultSet.getString(1);
				String tableSchema = resultSet.getString(2);
				System.out.println("tableName:" + tableName + " tableCatalog:"
						+ tableCatalog + " tableSchema:" + tableSchema);
			}
		} catch (SQLException e) {
		}
	}

}
