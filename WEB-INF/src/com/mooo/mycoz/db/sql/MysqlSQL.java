package com.mooo.mycoz.db.sql;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Types;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mooo.mycoz.util.DbUtil;
import com.mooo.mycoz.util.ReflectUtil;
import com.mooo.mycoz.util.StringUtils;

public class MysqlSQL extends AbstractSQL {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8659111122527763888L;

	@Override
	public void setRecord(Integer offsetRecord, Integer maxRecords) {
			if(offsetRecord < 0)
				offsetRecord = 0;
			
			setLimitBy(new StringBuilder(" LIMIT "+offsetRecord+","+maxRecords));
			setByLimit(true);
	}
	
	public void entityFillField(Object entity) {
		try {
			List<String> methods = ReflectUtil.getMethodNames(entity.getClass());
			
			String method;
			String field;
			int columnType = 0;

			for (Iterator<String> it = methods.iterator(); it.hasNext();) {
				method = it.next();
				if(method.indexOf("get")==0){
					
					Method getMethod;
					getMethod = entity.getClass().getMethod(method);
					
					Object obj = getMethod.invoke(entity);
					
					if(obj !=null) {
						field = method.substring(method.indexOf("get")+3);
						columnType = DbUtil.type(null,this.getCatalog(),this.getTable(),StringUtils.upperToPrefix(field,null));
						
						if(obj.getClass().isAssignableFrom(Integer.class))
							setField(StringUtils.upperToPrefix(field,null), (Integer)obj);
						else if(obj.getClass().isAssignableFrom(String.class)){
							setField(StringUtils.upperToPrefix(field,null), (String)obj);
						}else if(obj.getClass().isAssignableFrom(Date.class)){
							if(columnType == Types.TIMESTAMP){
								setField(StringUtils.upperToPrefix(field,null), (Date)obj,Types.TIMESTAMP);
							} else{
								setField(StringUtils.upperToPrefix(field,null), (Date)obj,columnType);
							}
						}else if(obj.getClass().isAssignableFrom(Double.class)){
							setField(StringUtils.upperToPrefix(field,null), (Double)obj);
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