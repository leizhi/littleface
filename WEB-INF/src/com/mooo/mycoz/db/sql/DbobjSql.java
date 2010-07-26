package com.mooo.mycoz.db.sql;

public interface DbobjSql extends DbCommon{

 	String getCatalog();
 	
 	void setCatalog(String catalog);
 	
	String getTable();
	
	void setTable(String table);
	
	void setField(String field, String value);

	void setLike(String field, String value);

	void setGreaterEqual(String field, String value);
	
	void setLessEqual(String field, String value);

	void setField(String field, int value);

	void setGroupBy(String field);

	void setOrderBy(String field, String type);

	void setRecord(int recordStart, int recordEnd);
	
 	String AddSQL();
 	
 	String DeleteSQL();
 	
 	String UpdateSQL();
 	
 	String SearchSQL();

}
