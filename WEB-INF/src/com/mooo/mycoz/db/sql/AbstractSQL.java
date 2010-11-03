package com.mooo.mycoz.db.sql;

import java.io.Serializable;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.Field;
import com.mooo.mycoz.util.DbUtil;
import com.mooo.mycoz.util.StringUtils;

public abstract class AbstractSQL implements SQLProcess, Serializable{
	
	private static Log log = LogFactory.getLog(AbstractSQL.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 5695615314838758248L;
	
	private String catalog;
	private String table;

	private boolean byWhere;
	private boolean byGroup;
	private boolean byOrder;
	private boolean byLimit;

	private StringBuilder whereBy;
	private StringBuilder groupBy;
	private StringBuilder orderBy;
	private StringBuilder limitBy;

	private boolean isSave;
	private boolean isUpdate;
	private boolean isSearch;

	private StringBuilder saveKey;
	private StringBuilder saveValue;
	private StringBuilder saveSql;
	
	private StringBuilder updateSql;
	private StringBuilder deleteSql;
	private StringBuilder searchSql;
	private StringBuilder countSql;

	private Map<String, Field> fields;
	private Map<String, Object> columnValues;
	
	//////////////////////////////
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
	
	public boolean isByLimit() {
		return byLimit;
	}

	public void setByLimit(boolean byLimit) {
		this.byLimit = byLimit;
	}

	public StringBuilder getLimitBy() {
		return limitBy;
	}

	public void setLimitBy(StringBuilder limitBy) {
		this.limitBy = limitBy;
	}

	public Map<String, Object> getColumnValues() {
		return columnValues;
	}

	public void setColumnValues(Map<String, Object> columnValues) {
		this.columnValues = columnValues;
	}
	
	///////////////////////////
	public void refresh(Object entity){
		refresh(entity,null);
	}
	
	public void refresh(Object entity,String prefix){
		refresh(StringUtils.getCatalog(entity.getClass(),1),
				StringUtils.upperToPrefix(entity.getClass().getSimpleName(),prefix));
		entityFillField(entity);
	}

	public void refresh(String catalog,String table){
		
		this.catalog = catalog;
		this.table = table;
		
		byWhere = false;
		byGroup = false;
		byOrder = false;
		byLimit = false;

		whereBy = new StringBuilder(" WHERE ");
		groupBy = new StringBuilder(" GROUP BY ");
		orderBy = new StringBuilder(" ORDER BY ");
		
		isSave = false;
		isUpdate = false;
		isSearch = true;

		saveKey = new StringBuilder("(");
		saveValue = new StringBuilder(") VALUES(");

		saveSql = new StringBuilder("INSERT INTO ");

		updateSql = new StringBuilder("UPDATE ");
		deleteSql = new StringBuilder("DELETE FROM ");
		searchSql = new StringBuilder("SELECT * FROM ");
		countSql = new StringBuilder("SELECT COUNT(*) AS total FROM ");

		saveSql.append(catalog + ".");
		updateSql.append(catalog + ".");
		deleteSql.append(catalog + ".");
		searchSql.append(catalog + ".");
		countSql.append(catalog + ".");

		saveSql.append(table);
		updateSql.append(table + " SET ");
		deleteSql.append(table);
		searchSql.append(table);
		countSql.append(table);

		fields = new HashMap<String, Field>();
		columnValues = new HashMap<String, Object>();
	}
	
	///////////////////////////////
	public void setField(String field, String value) {
		try {
			if (field == null || value == null)
				new Exception("set value is null");

			fields.put(field, new Field(field,Types.VARCHAR));
			columnValues.put(field, value);
		} catch (Exception e) {
		}
	}

	public void setField(String field, Integer value) {
		try {
			if (field == null || value == null)
				new Exception("set value is null");

			fields.put(field, new Field(field,Types.INTEGER));
			columnValues.put(field, value);
		} catch (Exception e) {
		}
	}

	public void setField(String field, Double value) {
		try {
			if (field == null || value == null)
				new Exception("set value is null");

			fields.put(field, new Field(field,Types.DOUBLE));
			columnValues.put(field, value);
		} catch (Exception e) {
		}
	}

	public void setField(String field, Date value,Integer columnType) {
		try {
			if (field == null || value == null)
				throw new Exception("set value is null");

			fields.put(field, new Field(field,columnType));
			columnValues.put(field, value);
			
		} catch (Exception e) {
		}
	}
	
	public void setLike(String field) {
		Field likeField = (Field)fields.get(field);
		likeField.setWhereByLike(true);
		likeField.setWhereByEqual(false);
		likeField.setWhereByGreaterEqual(false);
		likeField.setWhereByLessEqual(false);
	}
	
	public void setGreaterEqual(String field) {
		Field geField = (Field)fields.get(field);
		geField.setWhereByLike(false);
		geField.setWhereByEqual(false);
		geField.setWhereByGreaterEqual(true);
		geField.setWhereByLessEqual(false);
	}
	
	public void setLessEqual(String field) {
		Field leField = (Field)fields.get(field);
		leField.setWhereByLike(false);
		leField.setWhereByEqual(false);
		leField.setWhereByGreaterEqual(false);
		leField.setWhereByLessEqual(true);
	}
	
	@Override
	public void addGroupBy(String groupField) {
		groupBy.append(groupField+",");
		byGroup = true;
	}
	
	@Override
	public void addOrderBy(String orderField) {
		orderBy.append(orderField+",");
		byOrder = true;
	}
	
	public String addSQL(Object entity) {
		refresh(entity);
		
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
					if(field.getType()==Types.TIMESTAMP){
						saveValue.append("date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)obj)) +"',");
					} else {
						saveValue.append("date'"+new SimpleDateFormat("yyyy-MM-dd ").format(((Date)obj)) +"',");
					}
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					saveValue.append(obj+",");
				}
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

		if(byWhere)
			deleteSql.append(whereBy);
		
		if(log.isDebugEnabled())log.debug("deleteSql="+deleteSql);

		return deleteSql.toString();
	}

	public String updateSQL(Object entity) {
		if(fields == null || columnValues == null)
			return null;
		
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
						if(field.getType()==Types.TIMESTAMP){
							whereBy.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)obj)) +"' AND ");
						} else {
							whereBy.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
						}
					}else if(obj.getClass().isAssignableFrom(Double.class)){
						whereBy.append(field.getName()+" = "+obj +" AND ");
					} else {
						whereBy.append(field.getName()+" = '"+obj +"' AND ");
					}
					
					continue;
				}

				if(obj.getClass().isAssignableFrom(Integer.class)){
					updateSql.append(field.getName()+" = "+obj +",");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					if(field.getType()==Types.TIMESTAMP){
						updateSql.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)obj)) +"',");
					} else {
						updateSql.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"',");
					}
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
		System.out.println("updateSql="+updateSql);

		return updateSql.toString();
	}
//////////////search too
	public String searchSQL(Object entity) {
		
		if(fields == null || columnValues == null)
			return null;
		
		Field field;
		String key;
		
		for (Iterator<?> it = fields.keySet().iterator(); it.hasNext();) {
		
			key = (String) it.next();
			field = (Field) fields.get(key);
			
			Object obj = columnValues.get(key);
			
			System.out.println("Object->" + obj);

			System.out.println("isWhereByEqual->" + field.isWhereByEqual());
			System.out.println("isWhereByGreaterEqual->" + field.isWhereByGreaterEqual());
			System.out.println("isWhereByLessEqual->" + field.isWhereByLessEqual());
			System.out.println("isWhereByLike->" + field.isWhereByLike());

			if(field.isWhereByEqual()) {
				byWhere = true;
				
				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" = "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					if(field.getType()==Types.TIMESTAMP){
						whereBy.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)obj)) +"' AND ");
					} else {
						whereBy.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
					}
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
					if(field.getType()==Types.TIMESTAMP){
						whereBy.append(field.getName()+" >= date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)obj)) +"' AND ");
					} else {
						whereBy.append(field.getName()+" >= date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
					}
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
					if(field.getType()==Types.TIMESTAMP){
						whereBy.append(field.getName()+" <= date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)obj)) +"' AND ");
					} else {
						whereBy.append(field.getName()+" <= date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
					}
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" <= "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" <= '"+obj +"' AND ");
				}
			}

			if(field.isWhereByLike()) {
				byWhere = true;

				if(obj.getClass().isAssignableFrom(Integer.class)){
					whereBy.append(field.getName()+" LIKE "+obj +" AND ");
				}else if(obj.getClass().isAssignableFrom(Date.class)){
					if(field.getType()==Types.TIMESTAMP){
						whereBy.append(field.getName()+" LIKE date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)obj)) +"' AND ");
					} else {
						whereBy.append(field.getName()+" LIKE date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
					}
				}else if(obj.getClass().isAssignableFrom(Double.class)){
					whereBy.append(field.getName()+" LIKE "+obj +" AND ");
				} else {
					whereBy.append(field.getName()+" LIKE '%"+obj +"%' AND ");
				}
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
			
			if(byLimit)
				searchSql.append(limitBy);
		}
		
		if(log.isDebugEnabled())log.debug("searchSql="+searchSql);

		return searchSql.toString();
	}
	public String countSQL(Object entity) {
		
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
					if(field.getType()==Types.TIMESTAMP){
						whereBy.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)obj)) +"' AND ");
					} else {
						whereBy.append(field.getName()+" = date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
					}
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
					if(field.getType()==Types.TIMESTAMP){
						whereBy.append(field.getName()+" >= date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)obj)) +"' AND ");
					} else {
						whereBy.append(field.getName()+" >= date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
					}
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
					if(field.getType()==Types.TIMESTAMP){
						whereBy.append(field.getName()+" <= date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)obj)) +"' AND ");
					} else {
						whereBy.append(field.getName()+" <= date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)obj)) +"' AND ");
					}
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
}
