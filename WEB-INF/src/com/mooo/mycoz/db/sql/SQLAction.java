package com.mooo.mycoz.db.sql;

import java.sql.SQLException;

import com.mooo.mycoz.db.DbCommon;

public interface SQLAction extends DbCommon{
	String getTable();
	
	void setTable(String table);
	
	void setField(String field, String value);
	
	void setField(String field, Integer value);

	void setLike(String field, String value);

	void setGreaterEqual(String field, String value);
	
	void setLessEqual(String field, String value);

	void setGroupBy(String field);

	void setOrderBy(String field, String type);

	void setRecord(int recordStart, int recordEnd);
	
 	String addSQL(Object entity) throws SQLException;
 	
 	String deleteSQL(Object entity) throws SQLException;
 	
 	String updateSQL(Object entity) throws SQLException;
 	
 	String searchSQL(Object entity) throws SQLException;

 	String countSQL(Object entity) throws SQLException;
 	
 	void entityFillField(Object entity);
}
