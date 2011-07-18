package com.mooo.mycoz.db.sql;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mooo.mycoz.common.ReflectUtil;
import com.mooo.mycoz.common.StringUtils;
import com.mooo.mycoz.db.DbUtil;

public class OracleSQL extends AbstractSQL{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3500897378220229889L;
	
	public OracleSQL(){
		setPrefix("_");
	}
	
	public String searchSQL(Object entity) {
		return searchSQL(entity,"_");
	}
	
	public String countSQL(Object entity) {
		return countSQL(entity,"_");
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
						columnType = DbUtil.type(null,this.getCatalog(),this.getTable(),StringUtils.upperToPrefix(field,"_"));
						
						if(obj.getClass().isAssignableFrom(Integer.class))
							setField(StringUtils.upperToPrefix(field,"_"), (Integer)obj);
						else if(obj.getClass().isAssignableFrom(String.class)){
							setField(StringUtils.upperToPrefix(field,"_"), (String)obj);
						}else if(obj.getClass().isAssignableFrom(Date.class)){
							if(columnType == Types.TIMESTAMP){
								setField(StringUtils.upperToPrefix(field,"_"), (Date)obj,Types.TIMESTAMP);
							} else{
								setField(StringUtils.upperToPrefix(field,"_"), (Date)obj,columnType);
							}
						}else if(obj.getClass().isAssignableFrom(Double.class)){
							setField(StringUtils.upperToPrefix(field,"_"), (Double)obj);
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
		
	@Override
	public void setRecord(Integer offsetRecord, Integer maxRecords) {
			setLimitBy(new StringBuilder(" rownum >="+offsetRecord+" AND rownum <="+maxRecords+offsetRecord));
			setByLimit(true);
	}
	
	public void refresh(Object entity){
		refresh(null,StringUtils.upperToPrefix(entity.getClass().getSimpleName(),"_"));
		entityFillField(entity);
	}
	
	public String selfDateSQL(Date date) {
		return "to_date('"+new SimpleDateFormat("yyyy-MM-dd").format(date) +"','yyyy-MM-dd'),";
	}

}
