package com.mooo.mycoz.db.sql;

public abstract class AbstractSQL implements DBCommon{

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

	public abstract String buildSQL();
	//public abstract String buildCountSQL();
}
