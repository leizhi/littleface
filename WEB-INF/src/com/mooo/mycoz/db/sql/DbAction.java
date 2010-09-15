package com.mooo.mycoz.db.sql;

public interface DbAction extends DbCommon{
 	String getCatalog();
 	
 	void setCatalog(String catalog);
 	
	String getTable();
	
	void setTable(String table);
	
	void setField(Field field);
	
	void setField(String field, Integer value);

	void setLike(String field, String value);

	void setGreaterEqual(String field, String value);
	
	void setLessEqual(String field, String value);

	void setGroupBy(String field);

	void setOrderBy(String field, String type);

	void setRecord(int recordStart, int recordEnd);
	
 	String addSQL();
 	
 	String deleteSQL();
 	
 	String updateSQL();
 	
 	String searchSQL();

 	String findSQL();
}
