package com.mooo.mycoz.db.sql;

import java.sql.Connection;

import com.mooo.mycoz.dbobj.DbAction;
import com.mooo.mycoz.util.PropertyManager;

public class SQLFactory implements SQLProcess {
    private static Object initLock = new Object();
    //private static String className = "com.mooo.mycoz.db.DbMysql";
    private static String className = "com.mooo.mycoz.dbobj.DbMysql";
    private static DbAction factory = null;

	public static DbAction getInstance() {
		if (factory == null) {
			synchronized (initLock) {
				if (factory == null) {
					String classNameProp = PropertyManager.getProperty("DbFactory.className");
					if (classNameProp != null) {
						className = classNameProp;
					}
					try {
						// Load the class and create an instance.
						Class<?> c = Class.forName(className);
						factory = (DbAction) c.newInstance();
					} catch (Exception e) {
						System.err.println("Failed to load ForumFactory class "
								+ className
								+ ". Yazd cannot function normally.");
						e.printStackTrace();
						return null;
					}
				}
			}
		}
		return factory;
	}
	
    /*private static SQLProcess factory = null;
    
	public static SQLProcess getInstance() {
		if (factory == null) {
			synchronized (initLock) {
				if (factory == null) {
					String classNameProp = PropertyManager.getProperty("DbFactory.className");
					if (classNameProp != null) {
						className = classNameProp;
					}
					try {
						// Load the class and create an instance.
						Class<?> c = Class.forName(className);
						factory = (SQLProcess) c.newInstance();
					} catch (Exception e) {
						System.err.println("Failed to load ForumFactory class "
								+ className
								+ ". Yazd cannot function normally.");
						e.printStackTrace();
						return null;
					}
				}
			}
		}
		return factory;
	}
*/
	@Override
	public String getCatalog() {
		return factory.getCatalog();
	}

	@Override
	public void setCatalog(String catalog) {
		factory.setCatalog(catalog);
	}

	@Override
	public String getTable() {
		return factory.getTable();
	}

	@Override
	public void setTable(String table) {
		factory.setTable(table);
	}

	@Override
	public void setField(String field, String value) {
		factory.setField(field, value);
	}

	@Override
	public void setField(String field, Integer value) {
		factory.setField(field, value);
	}

	@Override
	public void setLike(String field, String value) {
		factory.setLike(field, value);
	}

	@Override
	public void setGreaterEqual(String field, String value) {
		factory.setGreaterEqual(field, value);
	}

	@Override
	public void setLessEqual(String field, String value) {
		factory.setLessEqual(field, value);
	}

	@Override
	public void setGroupBy(String field) {
		factory.setGroupBy(field);
	}

	@Override
	public void setOrderBy(String field, String type) {
		factory.setOrderBy(field, type);
	}

	@Override
	public void setRecord(int recordStart, int recordEnd) {
		factory.setRecord(recordStart, recordEnd);
	}

	@Override
	public String addSQL() {
		return factory.addSQL();
	}

	@Override
	public String deleteSQL() {
		return factory.deleteSQL();
	}

	@Override
	public String updateSQL() {
		return factory.updateSQL();
	}

	@Override
	public String searchSQL() {
		return factory.searchSQL();
	}

	@Override
	public String countSQL() {
		return factory.countSQL();
	}

	@Override
	public Connection getConnection(){
		return factory.getConnection();
	}

	@Override
	public void setConnection(Connection connection){
		factory.setConnection(connection);
	}

	@Override
	public void close(){
		factory.close();
	}
}