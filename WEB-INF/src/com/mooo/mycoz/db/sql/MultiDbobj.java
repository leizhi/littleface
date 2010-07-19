package com.mooo.mycoz.db.sql;

import java.util.List;

public interface MultiDbobj extends MultiSQL {

	List searchAndRetrieveList();
 	
 	int count();
  
}
