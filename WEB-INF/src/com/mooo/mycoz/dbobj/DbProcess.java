package com.mooo.mycoz.dbobj;

import java.sql.SQLException;
import java.util.List;

public interface DbProcess {
	
	List<Object> searchAndRetrieveList() throws SQLException;

	Integer count() throws SQLException;

	void add() throws SQLException;

	void delete() throws SQLException;

	void update() throws SQLException;

	void retrieve() throws SQLException;
	
}
