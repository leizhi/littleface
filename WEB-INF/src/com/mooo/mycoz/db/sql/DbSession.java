package com.mooo.mycoz.db.sql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import com.mooo.mycoz.db.pool.DbConnectionManager;

public abstract class DbSession implements SQLProcess, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5695615314838758248L;
	
	public String catalog;
	public String table;
	
	public Connection connection;
	boolean closeCon;

	public String getCatalog() {
		return catalog;
	}
	
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	
	public String getTable() {
		return table;
	}
	
	public void setTable(String table) {
		this.table = table;
	}
	
	public Connection getConnection(){
		
		if (connection == null) {
			connection = DbConnectionManager.getConnection();
			closeCon = true;
		}

		return connection;
	}
	
	public void setConnection(Connection connection){
		if(connection != null ) {
			this.connection = connection;
			closeCon = false;
		} else {
			getConnection();
		}
	}

	public void close() {
		catalog = null;
		table = null;
		
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
