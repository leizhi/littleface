package com.mooo.mycoz.test;

import com.mooo.mycoz.db.sql.OracleSQL;

public class TestDbobject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OracleSQL factory = new OracleSQL();
		//factory.buildSQL();
		factory.searchSQL();
		factory.addSQL();
		factory.updateSQL();
		factory.deleteSQL();
		factory.findSQL();
	}

}
