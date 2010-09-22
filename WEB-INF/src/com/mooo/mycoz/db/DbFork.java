package com.mooo.mycoz.db;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mooo.mycoz.db.sql.SQLFactory;

public class DbFork extends SQLFactory implements DbAction{

	@Override
	public List<Object> searchAndRetrieveList(Object entity)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer count(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Object entity) throws SQLException {
		Statement stmt = null;
		String doSql = getInstance().addSQL(entity);
		try{		
			stmt = getInstance().getConnection().createStatement();
			stmt.execute(doSql);
		}finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//sqlAction.close();
		}
	}

	@Override
	public void delete(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrieve(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
