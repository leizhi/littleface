package com.mooo.mycoz.db;

import java.sql.SQLException;
import java.util.List;

public interface DbAction {
	
	List<Object> searchAndRetrieveList(Object entity) throws SQLException;

	Integer count(Object entity) throws SQLException;

	void add(Object entity) throws SQLException;

	void delete(Object entity) throws SQLException;

	void update(Object entity) throws SQLException;

	void retrieve(Object entity) throws SQLException;
}
