package com.mooo.mycoz.db.sql;

public interface Search extends DBOperation {

	void setField(String field, String value);

	void setLike(String field, String value);

	void setGreaterEqual(String field, String value);
	
	void setLessEqual(String field, String value);

	void setField(String field, int value);

	void setGroupBy(String field);

	void setOrderBy(String field, String type);

	void setRecord(int recordStart, int recordEnd);
}
