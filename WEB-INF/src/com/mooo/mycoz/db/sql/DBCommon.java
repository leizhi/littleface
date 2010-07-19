package com.mooo.mycoz.db.sql;

public interface DBCommon {

 	String getCatalog();
 	
 	void setCatalog(String catalog);
 	
 	String buildSQL();
 	
 	//String buildCountSQL();
}
