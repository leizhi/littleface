package com.mooo.mycoz.db.sql;

import java.sql.Types;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.DbAbstract;
import com.mooo.mycoz.db.Field;
import com.mooo.mycoz.db.pool.DbConnectionManager;

public class DbGeneral extends DbAbstract implements SQLProcess {

	private static Log log = LogFactory.getLog(DbGeneral.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 4971778169729898320L;
	
	public DbGeneral(){
		connection = DbConnectionManager.getConnection();
		closeCon = true;
	}
	
	public void buildSQL(){
		
		entityFillField(null);
		
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
	public String addSQL() {
		return addSQL(null);
	}

	@Override
	public String deleteSQL() {
		return deleteSQL(null);
	}

	@Override
	public String updateSQL() {
		return updateSQL(null);
	}

	@Override
	public String searchSQL() {
		return searchSQL(null);
	}

	@Override
	public String countSQL() {	
		return countSQL(null);
	}
	
}
