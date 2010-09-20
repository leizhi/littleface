package com.mooo.mycoz.dbobj;

import java.sql.SQLException;
import java.util.List;

import com.mooo.mycoz.db.sql.SQLFactory;
import com.mooo.mycoz.util.StringUtils;

public class DbFactory extends SQLFactory implements DbAction{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -150097142399145015L;

	public DbFactory(){
		getInstance();
		setTable(StringUtils.upperToPrefix(this.getClass().getSimpleName(),null));
		setCls(this.getClass());
	}
	
	@Override
	public List<Object> searchAndRetrieveList() throws SQLException {
		return getInstance().searchAndRetrieveList();
	}

	@Override
	public Integer count() throws SQLException {
		return getInstance().count();
	}

	@Override
	public void add() throws SQLException {
		getInstance().add();
	}

	@Override
	public void delete() throws SQLException {
		getInstance().delete();
	}

	@Override
	public void update() throws SQLException {
		getInstance().update();
	}

	@Override
	public void retrieve() throws SQLException {
		getInstance().retrieve();
	}

	@Override
	public void setCls(Class cls) {
		getInstance().setCls(cls);
	}
	
}
