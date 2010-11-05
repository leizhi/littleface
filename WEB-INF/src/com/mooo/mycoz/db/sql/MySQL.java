package com.mooo.mycoz.db.sql;

import java.sql.Connection;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.mooo.mycoz.util.DbUtil;
import com.mooo.mycoz.util.StringUtils;

public class MySQL {

	public String addSQL(String catalog,String table,Map<String,Object> maps) {
		StringBuilder saveKey = new StringBuilder("(");
		StringBuilder saveValue = new StringBuilder(") VALUES(");
		
		StringBuilder saveSql = new StringBuilder("INSERT INTO ");
		if(catalog!=null)
			saveSql.append(catalog+".");
		if(table!=null)
			saveSql.append(table);
		
		String key = null;
		Object value = null;
		
		for(Map.Entry<String, Object> entry: maps.entrySet()){
			key = entry.getKey();
			value = entry.getValue();
			
			saveKey.append(key+",");

			if(value.getClass().isAssignableFrom(Integer.class) || value.getClass().isAssignableFrom(Double.class) )
				saveValue.append(value+",");
			else if(value.getClass().isAssignableFrom(String.class)){
				if(((String)value).indexOf("MAX(") > -1){
					saveValue.append(value+",");
				} else {
					saveValue.append("'"+value+"',");
				}
			}else if(value.getClass().isAssignableFrom(Date.class)){
				int columnType = DbUtil.type(null,catalog,table,StringUtils.upperToPrefix(key,null));

				if(columnType==Types.TIMESTAMP){
					saveValue.append("date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)value)) +"',");
				} else {
					saveValue.append("date'"+new SimpleDateFormat("yyyy-MM-dd ").format(((Date)value)) +"',");
				}
			}
		}
		
		if(maps.size() > 0){
			saveSql.append(saveKey.substring(0,saveKey.lastIndexOf(",")));
			
			
			saveSql.append(saveValue.substring(0,saveValue.lastIndexOf(",")));
		} else {
			saveSql.append(saveKey);
			saveSql.append(saveValue);
		}
		
		saveSql.append(")");

		return saveSql.toString();
	}
	
	public String querySQL(String catalog,String table,Map<String,Object> maps) {
		return querySQL(catalog,table,maps,null);
	}
	
	public String querySQL(String catalog,String table,Map<String,Object> maps,String customerSQL) {
		StringBuilder whereBy = new StringBuilder(" WHERE ");

		StringBuilder querySql = new StringBuilder("SELECT * FROM ");
		if(catalog!=null)
			querySql.append(catalog+".");
		if(table!=null)
			querySql.append(table);
		
		String key = null;
		Object value = null;
		
		for(Map.Entry<String, Object> entry: maps.entrySet()){
			key = entry.getKey();
			value = entry.getValue();

			if(value.getClass().isAssignableFrom(Integer.class) || value.getClass().isAssignableFrom(Double.class) ){
				whereBy.append(key+"="+value+" AND ");
			}else if(value.getClass().isAssignableFrom(String.class)){
				whereBy.append(key+"='"+value+"' AND ");
			}else if(value.getClass().isAssignableFrom(Date.class)){
				int columnType = DbUtil.type(null,catalog,table,StringUtils.upperToPrefix(key,null));

				if(columnType==Types.TIMESTAMP){
					whereBy.append(key+"=date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)value))+"' AND ");
				} else {
					whereBy.append(key+"=date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)value))+"' AND ");
				}
			}
		}
		
		if(maps.size() > 0){
			querySql.append(whereBy.substring(0,whereBy.lastIndexOf("AND")));
		} 
		
		return querySql.toString();
	}
	
	public String countSQL(Connection connection,String catalog,String table,Map<String,Object> maps,String customerSQL) {
		StringBuilder whereBy = new StringBuilder(" WHERE ");

		StringBuilder querySql = new StringBuilder("SELECT COUNT(*) AS total FROM ");
		if(catalog!=null)
			querySql.append(catalog+".");
		if(table!=null)
			querySql.append(table);
		
		String key = null;
		Object value = null;
		
		for(Map.Entry<String, Object> entry: maps.entrySet()){
			key = entry.getKey();
			value = entry.getValue();

			if(value.getClass().isAssignableFrom(Integer.class) || value.getClass().isAssignableFrom(Double.class) ){
				whereBy.append(key+"="+value+" AND ");
			}else if(value.getClass().isAssignableFrom(String.class)){
				whereBy.append(key+"="+value+" AND ");
			}else if(value.getClass().isAssignableFrom(Date.class)){
				int columnType = DbUtil.type(null,catalog,table,StringUtils.upperToPrefix(key,null));

				if(columnType==Types.TIMESTAMP){
					whereBy.append(key+"=date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)value))+"' AND ");
				} else {
					whereBy.append(key+"=date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)value))+"' AND ");
				}
			}
		}
		
		if(maps.size() > 0){
			querySql.append(whereBy.substring(0,whereBy.lastIndexOf("AND")));
		} 
		
		return querySql.toString();
	}
	
	public String updateSQL(Connection connection,String catalog,String table,Map<String,Object> quers,Map<String,Object> updates,String customerSQL) {
		StringBuilder whereBy = new StringBuilder(" WHERE ");
		StringBuilder updateSet = new StringBuilder(" SET ");

		StringBuilder updateSql = new StringBuilder("UPDATE ");

		if(catalog!=null)
			updateSql.append(catalog+".");
		if(table!=null)
			updateSql.append(table);
		
		String key = null;
		Object value = null;

		for(Map.Entry<String, Object> entry: updates.entrySet()){
			key = entry.getKey();
			value = entry.getValue();

			if(value.getClass().isAssignableFrom(Integer.class) || value.getClass().isAssignableFrom(Double.class) ){
				updateSet.append(key+"="+value+",");
			}else if(value.getClass().isAssignableFrom(String.class)){
				updateSet.append(key+"="+value+",");
			}else if(value.getClass().isAssignableFrom(Date.class)){
				int columnType = DbUtil.type(null,catalog,table,StringUtils.upperToPrefix(key,null));

				if(columnType==Types.TIMESTAMP){
					updateSet.append(key+"=date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)value))+"',");
				} else {
					updateSet.append(key+"=date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)value))+"',");
				}
			}
		}
		
		for(Map.Entry<String, Object> entry: quers.entrySet()){
			key = entry.getKey();
			value = entry.getValue();

			if(value.getClass().isAssignableFrom(Integer.class) || value.getClass().isAssignableFrom(Double.class) ){
				whereBy.append(key+"="+value+" AND ");
			}else if(value.getClass().isAssignableFrom(String.class)){
				whereBy.append(key+"="+value+" AND ");
			}else if(value.getClass().isAssignableFrom(Date.class)){
				int columnType = DbUtil.type(null,catalog,table,StringUtils.upperToPrefix(key,null));

				if(columnType==Types.TIMESTAMP){
					whereBy.append(key+"=date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)value))+"' AND ");
				} else {
					whereBy.append(key+"=date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)value))+"' AND ");
				}
			}
		}
		
		if(updates.size() > 0){
			updateSql.append(updateSet.substring(0,updateSet.lastIndexOf(",")));
		}
		
		if(quers.size() > 0){
			updateSql.append(whereBy.substring(0,whereBy.lastIndexOf("AND")));
		}
		
		return updateSql.toString();
	}
	
	public String deleteSQL(Connection connection,String catalog,String table,Map<String,Object> maps,String customerSQL) {
		StringBuilder whereBy = new StringBuilder(" WHERE ");

		StringBuilder deleteSql = new StringBuilder("DELETE FROM ");
		if(catalog!=null)
			deleteSql.append(catalog+".");
		if(table!=null)
			deleteSql.append(table);
		
		String key = null;
		Object value = null;
		
		for(Map.Entry<String, Object> entry: maps.entrySet()){
			key = entry.getKey();
			value = entry.getValue();

			if(value.getClass().isAssignableFrom(Integer.class) || value.getClass().isAssignableFrom(Double.class) ){
				whereBy.append(key+"="+value+" AND ");
			}else if(value.getClass().isAssignableFrom(String.class)){
				whereBy.append(key+"="+value+" AND ");
			}else if(value.getClass().isAssignableFrom(Date.class)){
				int columnType = DbUtil.type(null,catalog,table,StringUtils.upperToPrefix(key,null));

				if(columnType==Types.TIMESTAMP){
					whereBy.append(key+"=date'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Date)value))+"' AND ");
				} else {
					whereBy.append(key+"=date'"+new SimpleDateFormat("yyyy-MM-dd").format(((Date)value))+"' AND ");
				}
			}
		}
		
		if(maps.size() > 0){
			deleteSql.append(whereBy.substring(0,whereBy.lastIndexOf("AND")));
		} 
		
		return deleteSql.toString();
	}
}
