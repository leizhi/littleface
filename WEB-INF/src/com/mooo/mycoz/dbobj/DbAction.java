package com.mooo.mycoz.dbobj;

import java.sql.SQLException;
import java.util.List;

import com.mooo.mycoz.db.sql.SQLProcess;

public interface DbAction extends SQLProcess{
	
	List<Object> searchAndRetrieveList() throws SQLException;

	Integer count() throws SQLException;

	void add() throws SQLException;

	void delete() throws SQLException;

	void update() throws SQLException;

	void retrieve() throws SQLException;
	
	void setCls(Class cls);

}
