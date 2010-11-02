package com.mooo.mycoz.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DbProcess {
	
	List<Object> searchAndRetrieveList(Object entity) throws SQLException;
	List<Object> searchAndRetrieveList(Connection connection,Object entity) 
		throws SQLException;
	
	Integer count(Object entity) throws SQLException;
	Integer count(Connection connection,Object entity) throws SQLException;

	void add(Object entity) throws SQLException;
	void add(Connection connection,Object entity) throws SQLException;

	void delete(Object entity) throws SQLException;
	void delete(Connection connection,Object entity) throws SQLException;

	void update(Object entity) throws SQLException;
	void update(Connection connection,Object entity) throws SQLException;

	void retrieve(Object entity) throws SQLException;
	void retrieve(Connection connection,Object entity) throws SQLException;
	
	void setRecord(Integer offsetRecord, Integer maxRecords);
}
