package com.mooo.mycoz.db.sql;

import java.io.Serializable;

public abstract class AbstractSQL implements DbSql, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5695615314838758248L;
	
	public String catalog;
	public String table;

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

	public void clear() {
		catalog = null;
		table = null;
	}
}
