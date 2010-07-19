package com.mooo.mycoz.db.sql;

import java.sql.ResultSet;

public interface OriginalMultiDbobj extends MultiSQL {

	void execute(String sql);
	
	ResultSet executeQuery(String sql);
	
 	int count(String sql);
 	
}
