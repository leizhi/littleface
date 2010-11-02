package com.mooo.mycoz.db.sql;

import java.sql.SQLException;

public interface SQLProcess {
	
	String getCatalog();

	void setCatalog(String catalog);
	
	String getTable();
	
	void setTable(String table);
	
	void setField(String field, String value);
	
	void setField(String field, Integer value);

	void setLike(String field, String value);

	void setGreaterEqual(String field, String value);
	
	void setLessEqual(String field, String value);

	void setGroupBy(String groupSql);

	void setOrderBy(String orderSql);

	void setRecord(Integer offsetRecord, Integer maxRecords);
	
 	String addSQL(Object entity) throws SQLException;
 	
 	String deleteSQL(Object entity) throws SQLException;
 	
 	String updateSQL(Object entity) throws SQLException;
 	
 	String searchSQL(Object entity) throws SQLException;

 	String countSQL(Object entity) throws SQLException;
 	
 	void entityFillField(Object entity);
}
