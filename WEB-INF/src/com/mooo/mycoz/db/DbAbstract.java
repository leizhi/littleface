package com.mooo.mycoz.db;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.SQLAction;
import com.mooo.mycoz.util.DbUtil;
import com.mooo.mycoz.util.ReflectUtil;
import com.mooo.mycoz.util.StringUtils;

public abstract class DbAbstract implements SQLAction, Serializable{
	
	private static Log log = LogFactory.getLog(DbAbstract.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 5695615314838758248L;
	
	public Connection connection;
	public boolean closeCon;

	public String catalog;
	public String table;
	
	public boolean byWhere;
	public boolean byGroup;
	public boolean byOrder;

	public StringBuilder whereBy;
	public StringBuilder groupBy;
	public StringBuilder orderBy;
	
	public boolean isSave;
	public boolean isUpdate;
	public boolean isSearch;

	public StringBuilder saveKey;
	public StringBuilder saveValue;
	public StringBuilder saveSql;
	
	public StringBuilder updateSql;
	public StringBuilder deleteSql;
	public StringBuilder searchSql;
	public StringBuilder countSql;
	
	public Map fields;
	public Map columnValues;
	
	public void initialization(){
		byWhere = false;
		byGroup = false;
		byOrder = false;

		whereBy=new StringBuilder(" WHERE ");
		groupBy=new StringBuilder(" GROUP BY ");
		orderBy=new StringBuilder(" ORDER BY ");
		
		isSave = false;
		isUpdate = false;
		isSearch = true;

		saveKey = new StringBuilder("(");
		saveValue = new StringBuilder(") VALUES(");
		saveSql = new StringBuilder("INSERT INTO "+catalog+"."+table);
		
		updateSql = new StringBuilder("UPDATE "+catalog+"."+table+" SET ");
		deleteSql = new StringBuilder("DELETE FROM "+catalog+"."+table);
		searchSql = new StringBuilder("SELECT * FROM "+catalog+"."+table);
		countSql = new StringBuilder("SELECT COUNT(*) AS total FROM "+catalog+"."+table);	
		
		fields= new HashMap();
		columnValues = new HashMap();
	}
	
	public String getCatalog() {
		return catalog;
	}
	
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	
	public String getTable() {
		return table;
	}
	
	public void setTable(String table) {
		this.table = table;
	}
	
	public Connection getConnection(){
		
		if (connection == null) {
			connection = DbConnectionManager.getConnection();
			closeCon = true;
		}

		return connection;
	}
	
	public void setConnection(Connection connection){
		if(connection != null ) {
			this.connection = connection;
			closeCon = false;
		} else {
			getConnection();
		}
	}

	public void close() {
		catalog = null;
		table = null;
		
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	///////////////////////////////
	public void setField(String field, String value) {
		try {
			if (field == null || value == null)
				new Exception("set value is null");

			fields.put(field, new Field(field,Types.VARCHAR));
			columnValues.put(field, value);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void setField(String field, Integer value) {
		try {
			if (field == null || value == null)
				new Exception("set value is null");

			fields.put(field, new Field(field,Types.INTEGER));
			columnValues.put(field, value);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void setField(String field, Double value) {
		try {
			if (field == null || value == null)
				new Exception("set value is null");

			fields.put(field, new Field(field,Types.DOUBLE));
			columnValues.put(field, value);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void setField(String field, Date value) {
		try {
			if (field == null || value == null)
				new Exception("set value is null");

			fields.put(field, new Field(field,Types.TIMESTAMP));
			columnValues.put(field, value);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void setLike(String field, String value) {
		// TODO Auto-generated method stub
		((Field)fields.get(field)).setWhereByLike(true);
		columnValues.put(field, value);
	}
	
	//public void setLike(String field) {
	//	((Field)fields.get(field)).setWhereByLike(true);
	//}
	
	public void setGreaterEqual(String field) {
		((Field)fields.get(field)).setWhereByGreaterEqual(true);
	}
	
	public void setLessEqual(String field) {
		((Field)fields.get(field)).setWhereByEqual(true);
	}
	
	public void setGreaterEqual(String field, String value) {
		Field f = new Field(field);
		f.setWhereByGreaterEqual(true);
		f.setWhereByEqual(false);
		
		fields.put(field, f);
		columnValues.put(field, value);
	}

	public void setLessEqual(String field, String value) {
		// TODO Auto-generated method stub
	}

	public void setGroupBy(String field) {
		((Field)fields.get(field)).setOrderBy(true);
	}

	public void setOrderBy(String field, String type) {
		// TODO Auto-generated method stub
	}

	public void setRecord(int recordStart, int recordEnd) {
		// TODO Auto-generated method stub
	}

	public void addGroupBy(String field) {
		((Field)fields.get(field)).setGroupBy(true);
	}

	public void addOrderBy(String field) {
		((Field)fields.get(field)).setOrderBy(true);
	}

	public String addSQL(Object entity) {
		if(entity != null)
			entityFillField(entity);
		else
			entityFillField(this);
		
		if(fields == null || columnValues == null)
			return null;
		
		Field field;
		String key;
		
		for (Iterator<?> it = fields.keySet().iterator(); it.hasNext();) {
			key = (String) it.next();
			field = (Field) fields.get(key);
			
			//value = (String) columnValues.get(key);
			Object obj = columnValues.get(key);

			if(field.isSave()) {
				isSave = true;
				saveKey.append(field.getName()+",");
				
				if(obj.getClass().isAssignableFrom(Integer.class)){
					saveValue.append(obj+",");
				}else if(obj.getClass().isAssignableFrom(String.class)){
					saveValue.append("'"+obj+"',");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					saveValue.append("date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"',");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					saveValue.append(obj+",");
				}
				
				/*
				if (field.getType() == Types.DATE || field.getType()==Types.TIMESTAMP){
					saveValue.append("date'"+value+"',");
				} else if (field.getType() == Types.BIGINT) {
					saveValue.append(value+",");
				} else { 
					saveValue.append("'"+value+"',");
				}
				*/
			}
			//if(log.isDebugEnabled())log.debug(field.getName()+"="+value);
		}
		
		if(isSave){
			saveKey.deleteCharAt(saveKey.lastIndexOf(","));
			saveValue.deleteCharAt(saveValue.lastIndexOf(","));
			saveValue.append(")");
			
			saveSql.append(saveKey);
			saveSql.append(saveValue);
		}

		if(log.isDebugEnabled())log.debug("saveSql="+saveSql);

		return saveSql.toString();
	}

	public String deleteSQL(Object entity) {
		if(entity != null)
			entityFillField(entity);
		else
			entityFillField(this);
		
		Field field;
		String key;
		
		for (Iterator<?> it = fields.keySet().iterator(); it.hasNext();) {
			key = (String) it.next();
			field = (Field) fields.get(key);
			
			Object obj = columnValues.get(key);

			if(field.isWhereByEqual()) {
				byWhere = true;
				
				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" = "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					whereBy.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" = "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" = '"+obj +"' AND ");
				}
			}
			
			if(field.isWhereByGreaterEqual()) {
				byWhere = true;
				
				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" >= "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					whereBy.append(field.getName()+" >= date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" >= "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" >= '"+obj +"' AND ");
				}
			}

			if(field.isWhereByLessEqual()) {
				byWhere = true;

				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" <= "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					whereBy.append(field.getName()+" <= date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" <= "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" <= '"+obj +"' AND ");
				}
			}
	
			if(log.isDebugEnabled())log.debug("whereBy="+whereBy);
		}
		
		if(byWhere)
			whereBy.delete(whereBy.lastIndexOf("AND"),whereBy.lastIndexOf("AND")+3);

		if(byWhere)
			deleteSql.append(whereBy);
		
		if(log.isDebugEnabled())log.debug("deleteSql="+deleteSql);

		return deleteSql.toString();
	}

	public String updateSQL(Object entity) {
		if(entity != null)
			entityFillField(entity);
		else
			entityFillField(this);
		
		Field field;
		String key;
		whereBy=new StringBuilder(" WHERE ");
		
		for (Iterator<?> it = fields.keySet().iterator(); it.hasNext();) {
			key = (String) it.next();
			field = (Field) fields.get(key);
			
			Object obj = columnValues.get(key);
		
			if(field.isUpdate()) {
				isUpdate = true;
				if (DbUtil.isPrimaryKey(this.getTable(),field.getName())) {
					byWhere = true;
					
					if(obj.getClass().isAssignableFrom(Integer.class)){
						whereBy.append(field.getName()+" = "+obj +" AND ");
					}else if(obj.getClass().isAssignableFrom(Date.class)){
						whereBy.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
					}else if(obj.getClass().isAssignableFrom(Double.class)){
						whereBy.append(field.getName()+" = "+obj +" AND ");
					} else {
						whereBy.append(field.getName()+" = '"+obj +"' AND ");
					}
					
					//continue;
				}

				if(obj.getClass().isAssignableFrom(Integer.class)){
					updateSql.append(field.getName()+" = "+obj +",");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					updateSql.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"',");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					updateSql.append(field.getName()+" = "+obj +",");
				} else {
					updateSql.append(field.getName()+" = '"+obj +"',");
				}

			}
			
			// where build
			/*
			if(field.isWhereByEqual()) {
				byWhere = true;
				
				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" = "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					whereBy.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" = "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" = '"+obj +"' AND ");
				}
			}
			
			if(field.isWhereByGreaterEqual()) {
				byWhere = true;
				
				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" >= "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					whereBy.append(field.getName()+" >= date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" >= "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" >= '"+obj +"' AND ");
				}
			}

			if(field.isWhereByLessEqual()) {
				byWhere = true;

				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" <= "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					whereBy.append(field.getName()+" <= date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" <= "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" <= '"+obj +"' AND ");
				}
			} */
			// build end
			
		}
		
		if(byWhere)
			whereBy.delete(whereBy.lastIndexOf("AND"),whereBy.lastIndexOf("AND")+3);
			
		if(isUpdate){
			updateSql.deleteCharAt(updateSql.lastIndexOf(","));
			
			if(byWhere)
				updateSql.append(whereBy);
		}

		if(log.isDebugEnabled())log.debug("updateSql="+updateSql);

		return updateSql.toString();
	}

	public String searchSQL(Object entity) {
		if(entity != null)
			entityFillField(entity);
		else
			entityFillField(this);
		
		if(fields == null || columnValues == null)
			return null;
		
		Field field;
		String key;
		
		for (Iterator<?> it = fields.keySet().iterator(); it.hasNext();) {
		
			key = (String) it.next();
			field = (Field) fields.get(key);
			
			Object obj = columnValues.get(key);
			
			if(field.isWhereByEqual()) {
				byWhere = true;
				
				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" = "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					whereBy.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" = "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" = '"+obj +"' AND ");
				}
			}
			
			if(field.isWhereByGreaterEqual()) {
				byWhere = true;
				
				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" >= "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					whereBy.append(field.getName()+" >= date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" >= "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" >= '"+obj +"' AND ");
				}
			}

			if(field.isWhereByLessEqual()) {
				byWhere = true;

				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" <= "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					whereBy.append(field.getName()+" <= date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" <= "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" <= '"+obj +"' AND ");
				}
			}

			if(field.isGroupBy()) {
				byGroup = true;
				groupBy.append(field.getName()+",");
			}
			
			if(field.isOrderBy()) {
				byOrder = true;
				orderBy.append(field.getName()+",");
			}
		}
		
		if(byWhere)
			whereBy.delete(whereBy.lastIndexOf("AND"),whereBy.lastIndexOf("AND")+3);
		
		if(byGroup)
			groupBy.deleteCharAt(groupBy.lastIndexOf(","));
		
		if(byOrder)
			orderBy.deleteCharAt(orderBy.lastIndexOf(","));
		
		if(isSearch){
			if(searchSql.lastIndexOf(",") > 0)
				searchSql.deleteCharAt(searchSql.lastIndexOf(","));
			
			if(byWhere) {
				searchSql.append(whereBy);
			}
			
			if(byGroup) {
				searchSql.append(groupBy);
			}
			
			if(byOrder) {
				searchSql.append(orderBy);
			}
		}
		
		if(log.isDebugEnabled())log.debug("searchSql="+searchSql);
		
		return searchSql.toString();
	}

	public String countSQL(Object entity) {
		
		if(entity != null)
			entityFillField(entity);
		else
			entityFillField(this);
		
		if(fields == null || columnValues == null)
			return null;
		
		Field field;
		String key;
		
		for (Iterator<?> it = fields.keySet().iterator(); it.hasNext();) {
			key = (String) it.next();
			field = (Field) fields.get(key);
			
			Object obj = columnValues.get(key);
						
			if(field.isWhereByEqual()) {
				byWhere = true;
				
				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" = "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					whereBy.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" = "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" = '"+obj +"' AND ");
				}
			}
			
			if(field.isWhereByGreaterEqual()) {
				byWhere = true;
				
				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" >= "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					whereBy.append(field.getName()+" >= date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" >= "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" >= '"+obj +"' AND ");
				}
			}

			if(field.isWhereByLessEqual()) {
				byWhere = true;

				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" <= "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					whereBy.append(field.getName()+" <= date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" <= "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" <= '"+obj +"' AND ");
				}
			}
	
			if(log.isDebugEnabled())log.debug("whereBy="+whereBy);
		}
		
		if(byWhere)
			whereBy.delete(whereBy.lastIndexOf("AND"),whereBy.lastIndexOf("AND")+3);
		
		if(isSearch){
			
			if(byWhere) {
				countSql.append(whereBy);
			}
			
			if(byGroup) {
				countSql.append(groupBy);
			}
			
			if(byOrder) {
				countSql.append(orderBy);
			}
		}
		
		if(log.isDebugEnabled())log.debug("countSql="+countSql);
		
		return countSql.toString();
	}
	
	public void entityFillField(Object entity) {
		try {
			List<String> methods = ReflectUtil.getMethodNames(entity.getClass());
			
			//setTable(StringUtils.upperToPrefix(entity.getClass().getSimpleName()));
			
			initialization();
			
			String method;
			String field;
			
			for (Iterator<String> it = methods.iterator(); it.hasNext();) {
				method = it.next();
				if(method.indexOf("get")==0){
					
					Method getMethod;
					getMethod = entity.getClass().getMethod(method);
					
					Object obj = getMethod.invoke(entity);
					
					if(obj !=null) {
						field = method.substring(method.indexOf("get")+3);
						if(obj.getClass().isAssignableFrom(Integer.class))
							setField(StringUtils.upperToPrefix(field), (Integer)obj);
						else if(obj.getClass().isAssignableFrom(String.class)){
							setField(StringUtils.upperToPrefix(field), (String)obj);
						}else if(obj.getClass().isAssignableFrom(Date.class)){
							setField(StringUtils.upperToPrefix(field), (Date)obj);
						}else if(obj.getClass().isAssignableFrom(Double.class)){
							setField(StringUtils.upperToPrefix(field), (Double)obj);
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
