package com.mooo.mycoz.db.sql;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.util.DbUtil;
import com.mooo.mycoz.util.ReflectUtil;
import com.mooo.mycoz.util.StringUtils;

public class OracleSQL extends AbstractSQL implements DbSql{
	
	private static Log log = LogFactory.getLog(OracleSQL.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 4971778169729898320L;

	boolean byWhere;
	boolean byGroup;
	boolean byOrder;

	StringBuilder whereBy;
	StringBuilder groupBy;
	StringBuilder orderBy;
	
	boolean isSave;
	boolean isUpdate;
	boolean isSearch;

	StringBuilder saveKey;
	StringBuilder saveValue;
	StringBuilder saveSql;
	
	StringBuilder updateSql;
	StringBuilder deleteSql;
	StringBuilder searchSql;
	StringBuilder countSql;
	
	Map fields;
	Map columnValues;
	
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
		saveSql = new StringBuilder("INSERT INTO "+table);
		
		updateSql = new StringBuilder("UPDATE "+table+" SET ");
		deleteSql = new StringBuilder("DELETE FROM "+table);
		searchSql = new StringBuilder("SELECT * FROM "+table);
		countSql = new StringBuilder("SELECT COUNT(*) AS total FROM "+table);	
		
		fields= new HashMap();
		columnValues = new HashMap();
	}
	
	public void buildSQL(){
		
		beanFillField();
		
		Field field;
		String key,value;
		
		for (Iterator<?> it = fields.keySet().iterator(); it.hasNext();) {
			key = (String) it.next();
			field = (Field) fields.get(key);
			value = (String) columnValues.get(key);
			
			if(field.isSave()) {
				isSave = true;
				saveKey.append(field.getName()+",");

				if (field.getType() == Types.DATE){
					saveValue.append("date'"+value+"',");
				} else if (field.getType() == Types.BIGINT) {
					saveValue.append(value+",");
				} else { 
					saveValue.append("'"+value+"',");
				}
			}
			
			if(field.isUpdate()) {
				isUpdate = true;
				
				if (field.getType() == Types.DATE)
					updateSql.append(field.getName()+"=date'"+value+"',");
				else if (field.getType() == Types.BIGINT)
					updateSql.append(field.getName()+"="+value);
				else 
					updateSql.append(field.getName()+"='"+value+"',");
			}
					
			if(field.isWhereByEqual()) {
				byWhere = true;

				if (field.getType() == Types.DATE)
					whereBy.append(field.getName()+" = date'"+value+"' AND ");
				else if (field.getType() == Types.BIGINT)
					whereBy.append(field.getName()+" = "+value + " AND ");
				else 
					whereBy.append(field.getName()+" = '"+value+"' AND ");
			}
			
			if(field.isWhereByGreaterEqual()) {
				byWhere = true;

				if (field.getType() == Types.DATE)
					whereBy.append(field.getName()+" >= date'"+value+"' AND ");
				else if (field.getType() == Types.BIGINT)
					whereBy.append(field.getName()+" >= "+value + " AND ");
				else 
					whereBy.append(field.getName()+" >= '"+value+"' AND");
			}

			if(field.isWhereByLessEqual()) {
				byWhere = true;

				if (field.getType() == Types.DATE)
					whereBy.append(field.getName()+" <= date'"+value+"' AND ");
				else if (field.getType() == Types.BIGINT)
					whereBy.append(field.getName()+" <= "+value + " AND ");
				else 
					whereBy.append(field.getName()+" <= '"+value+"' AND ");
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
				countSql.append(whereBy);
			}
			
			if(byGroup) {
				searchSql.append(groupBy);
				countSql.append(groupBy);
			}
			
			if(byOrder) {
				searchSql.append(orderBy);
				countSql.append(orderBy);
			}
		}
		
		if(isSave){
			saveKey.deleteCharAt(saveKey.lastIndexOf(","));
			saveValue.deleteCharAt(saveValue.lastIndexOf(","));
			saveValue.append(")");
			
			saveSql.append(saveKey);
			saveSql.append(saveValue);
		}

		if(isUpdate){
			updateSql.deleteCharAt(updateSql.lastIndexOf(","));
			
			if(byWhere)
				updateSql.append(whereBy);
		}

		if(byWhere)
			deleteSql.append(whereBy);
		
		if(log.isDebugEnabled())log.debug("searchSql="+searchSql);
		if(log.isDebugEnabled())log.debug("findSql="+countSql);

		if(log.isDebugEnabled())log.debug("saveSql="+saveSql);
		if(log.isDebugEnabled())log.debug("updateSql="+updateSql);
		if(log.isDebugEnabled())log.debug("deleteSql="+deleteSql);

		if(log.isDebugEnabled())log.debug("whereBy="+whereBy);
		if(log.isDebugEnabled())log.debug("groupBy="+groupBy);
		if(log.isDebugEnabled())log.debug("orderBy="+orderBy);
	}
	
	@Override
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

	@Override
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

	@Override
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
	
	@Override
	public void setGreaterEqual(String field, String value) {
		// TODO Auto-generated method stub
		//((Field)fields.get(field)).setWhereByGreaterEqual(true);
		Field f = new Field(field);
		f.setWhereByGreaterEqual(true);
		f.setWhereByEqual(false);
		
		fields.put(field, f);
		columnValues.put(field, value);
	}

	@Override
	public void setLessEqual(String field, String value) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setGroupBy(String field) {
		((Field)fields.get(field)).setOrderBy(true);
	}

	@Override
	public void setOrderBy(String field, String type) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setRecord(int recordStart, int recordEnd) {
		// TODO Auto-generated method stub
	}

	public void addGroupBy(String field) {
		((Field)fields.get(field)).setGroupBy(true);
	}

	public void addOrderBy(String field) {
		((Field)fields.get(field)).setOrderBy(true);
	}

	@Override
	public String addSQL() {
		beanFillField();

		if(fields == null || columnValues == null)
			return null;
		
		Field field;
		String key,value;
		
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

	@Override
	public String deleteSQL() {
		beanFillField();

		Field field;
		String key,value;
		
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

	@Override
	public String updateSQL() {
		beanFillField();

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

	@Override
	public String searchSQL() {
		
		beanFillField();
		
		if(fields == null || columnValues == null)
			return null;
		
		Field field;
		String key,value;
		
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

	@Override
	public String countSQL() {
		
		beanFillField();

		if(fields == null || columnValues == null)
			return null;
		
		Field field;
		String key,value;
		
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
	
	public void beanFillField(){
		try {
			List<String> methods = ReflectUtil.getMethodNames(this.getClass());
			
			setTable(StringUtils.upperToPrefix(this.getClass().getSimpleName()));
			
			initialization();
			
			String method;
			String field;
			
			for (Iterator<String> it = methods.iterator(); it.hasNext();) {
				method = it.next();
				if(method.indexOf("get")==0){
					
					Method getMethod;
					getMethod = this.getClass().getMethod(method);
					
					Object obj = getMethod.invoke(this);
					
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