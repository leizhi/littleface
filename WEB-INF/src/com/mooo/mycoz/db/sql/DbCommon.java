package com.mooo.mycoz.db.sql;

import java.sql.Connection;

public interface DbCommon {
	
	String getCatalog();

	void setCatalog(String catalog);

	Connection getConnection();

	void setConnection(Connection connection);

	void close();
}
