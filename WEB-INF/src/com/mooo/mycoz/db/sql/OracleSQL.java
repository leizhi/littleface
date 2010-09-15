package com.mooo.mycoz.db.sql;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mooo.mycoz.util.StringUtils;

public class OracleSQL extends AbstractSQL implements DbSql{

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
	StringBuilder findSql;
	
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
		findSql = new StringBuilder("SELECT COUNT(*) AS total FROM "+table);	
		
		fields= new HashMap();
		columnValues = new HashMap();
	}
	
	public void buildSQL(){
		
		initialization();
		
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
			
			System.out.println(field.getName()+"="+value);
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
				findSql.append(whereBy);
			}
			
			if(byGroup) {
				searchSql.append(groupBy);
				findSql.append(groupBy);
			}
			
			if(byOrder) {
				searchSql.append(orderBy);
				findSql.append(orderBy);
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
		
		
		System.out.println("searchSql="+searchSql);
		System.out.println("findSql="+findSql);

		System.out.println("saveSql="+saveSql);
		System.out.println("updateSql="+updateSql);
		System.out.println("deleteSql="+deleteSql);

		System.out.println("whereBy="+whereBy);
		System.out.println("groupBy="+groupBy);
		System.out.println("orderBy="+orderBy);
	}
	
	@Override
	public void setField(String field, String value) {
		try {
			if (field == null || value == null)
				new Exception("set value is null");

			fields.put(field, new Field(field));
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

			fields.put(field, new Field(field));
			columnValues.put(field, value);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void setField(String field, Double value) {
		try {
			if (field == null || value == null)
				new Exception("set value is null");

			fields.put(field, new Field(field));
			columnValues.put(field, value);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void setField(String field, Date value) {
		try {
			if (field == null || value == null)
				new Exception("set value is null");

			fields.put(field, new Field(field));
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
		//initialization();
		
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
				
				if(obj.getClass().isAssignableFrom(Integer.class))
					saveValue.append(obj+",");
				else if(obj.getClass().isAssignableFrom(String.class)){
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
			//System.out.println(field.getName()+"="+value);
		}
		
		if(isSave){
			saveKey.deleteCharAt(saveKey.lastIndexOf(","));
			saveValue.deleteCharAt(saveValue.lastIndexOf(","));
			saveValue.append(")");
			
			saveSql.append(saveKey);
			saveSql.append(saveValue);
		}

		System.out.println("saveSql="+saveSql);

		return saveSql.toString();
	}

	@Override
	public String deleteSQL() {
		initialization();
		
		setField("REMOTEID","0015");
		setField("TRADE_AMOUNT","1395");
		//setField("SALE_MONEY","3456115.7");
		
		setGreaterEqual("SALE_MONEY","3456115.7");
		
		addOrderBy("SALE_MONEY");
		
		Field field;
		String key,value;
		
		for (Iterator<?> it = fields.keySet().iterator(); it.hasNext();) {
			key = (String) it.next();
			field = (Field) fields.get(key);
			value = (String) columnValues.get(key);

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
			
			System.out.println(field.getName()+"="+value);
		}
		
		if(byWhere)
			whereBy.delete(whereBy.lastIndexOf("AND"),whereBy.lastIndexOf("AND")+3);

		if(byWhere)
			deleteSql.append(whereBy);
		
		System.out.println("deleteSql="+deleteSql);

		return deleteSql.toString();
	}

	@Override
	public String updateSQL() {
		initialization();
		
		setField("REMOTEID","0015");
		setField("TRADE_AMOUNT","1395");
		setField("SALE_MONEY","3456115.7");

		addOrderBy("SALE_MONEY");
		
		Field field;
		String key,value;
		
		for (Iterator<?> it = fields.keySet().iterator(); it.hasNext();) {
			key = (String) it.next();
			field = (Field) fields.get(key);
			value = (String) columnValues.get(key);
			
		
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
			
			System.out.println(field.getName()+"="+value);
		}
		
		if(byWhere)
			whereBy.delete(whereBy.lastIndexOf("AND"),whereBy.lastIndexOf("AND")+3);
			
		if(isUpdate){
			updateSql.deleteCharAt(updateSql.lastIndexOf(","));
			
			if(byWhere)
				updateSql.append(whereBy);
		}

		if(byWhere)
			deleteSql.append(whereBy);

		System.out.println("updateSql="+updateSql);

		return updateSql.toString();
	}

	@Override
	public String searchSQL() {
		
		initialization();

		setField("REMOTEID","0015");
		setField("TRADE_AMOUNT","1395");
		//setField("SALE_MONEY","3456115.7");
		
		setGreaterEqual("SALE_MONEY","3456");
		
		addOrderBy("SALE_MONEY");
		
		Field field;
		String key,value;
		
		for (Iterator<?> it = fields.keySet().iterator(); it.hasNext();) {
			key = (String) it.next();
			field = (Field) fields.get(key);
			value = (String) columnValues.get(key);
						
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
			
			System.out.println(field.getName()+"="+value);
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
				findSql.append(whereBy);
			}
			
			if(byGroup) {
				searchSql.append(groupBy);
				findSql.append(groupBy);
			}
			
			if(byOrder) {
				searchSql.append(orderBy);
				findSql.append(orderBy);
			}
		}
		
		System.out.println("searchSql="+searchSql);
		
		return searchSql.toString();
	}

	@Override
	public String findSQL() {
		
		return findSql.toString();
	}
	


}
