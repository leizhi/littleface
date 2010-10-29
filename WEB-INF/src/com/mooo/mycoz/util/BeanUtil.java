package com.mooo.mycoz.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

public class BeanUtil {
	//private static Log log = LogFactory.getLog(BeanUtil.class);

	/**
	 * 动态赋值给系统对象 String Integer Long Float Date 等
	 * 
	 * @param bean
	 *            执行对象
	 * @param propertyName
	 *            属性
	 * @param value
	 *            参数
	 * @param format
	 *            参数格式化规格
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws ParseException
	 */
	public static void bindProperty(Object bean, String propertyName,
			String value) throws NoSuchMethodException,
			InvocationTargetException, IllegalAccessException, ParseException,
			InstantiationException {

		// 得到方法名
		String funName = StringUtils.getFunName(propertyName);
		// get方法
		Method getMethod = bean.getClass().getMethod("get" + funName);
		// 得到参数类型
		Class<?> cl = getMethod.getReturnType();
		// set方法
		Method setMethod = bean.getClass().getMethod("set" + funName,new Class[] { cl });

		// 当参数为空时直接赋予NULL值
		if (value == null || value.trim().equals("")) {
			setMethod.invoke(bean, new Object[] { null });
			return;
		}

		// 根据不同的系统对象转换
		if (cl == String.class) {
			setMethod.invoke(bean, new Object[] { value });
			return;
		} else if (cl == Integer.class || cl == Float.class || cl == Long.class
				|| cl == Double.class || cl == Byte.class
				|| cl == Boolean.class || cl == Character.class) {
			Method valueOf = cl.getMethod("valueOf",new Class[] { String.class });
			Object valueObj = valueOf.invoke(cl, new Object[] { value });
			setMethod.invoke(bean, new Object[] { valueObj });
		}
	}

	public static void bindProperty(Object bean, String propertyName,
			Date date) throws NoSuchMethodException,
			InvocationTargetException, IllegalAccessException, ParseException,
			InstantiationException {

		// 得到方法名
		String funName = StringUtils.getFunName(propertyName);
		// get方法
		Method getMethod = bean.getClass().getMethod("get" + funName);
		// 得到参数类型
		Class<?> cl = getMethod.getReturnType();
		// set方法
		Method setMethod = bean.getClass().getMethod("set" + funName,new Class[] { cl });

		// 当参数为空时直接赋予NULL值
		if (date == null) {
			setMethod.invoke(bean, new Object[] { null });
			return;
		}
		
		if (cl == java.util.Date.class || cl == java.sql.Date.class) {
			Object dateObj = cl.newInstance();
			Method setTime = cl.getMethod("setTime", new Class[] { long.class });
			setTime.invoke(dateObj, new Object[] { date.getTime() });
			setMethod.invoke(bean, new Object[] { dateObj });
		}
	}
	/**
	 * 动态赋值给自定义对象 member.city.id = 110 bean = member objName = city propertyName
	 * = id value = 110
	 * 
	 * @param bean
	 *            执行对象
	 * @param objName
	 *            做参数的对象
	 * @param propertyName
	 *            做参数的对象的属性名称
	 * @param value
	 *            参数
	 * @param format
	 *            参数格式化规格
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws ParseException
	 * @throws InstantiationException
	 */
	public static void bindSubObject(Object bean, String objName,
			String propertyName, String value)
			throws NoSuchMethodException, InvocationTargetException,
			IllegalAccessException, ParseException, InstantiationException {
		String funName = StringUtils.getFunName(objName);
		Method getMethod = bean.getClass().getMethod("get" + funName);
		Class<?> cls = getMethod.getReturnType();
		Object obj = getMethod.invoke(bean);

		// 判断参数为空,直接设置NULL值.
		if (value.trim().equals("")) {
			if (obj != null) {
				Method setMethod = bean.getClass().getMethod("set" + funName,
						new Class[] { cls });
				setMethod.invoke(bean, new Object[] { null });
			}
			return;
		}

		// 如果对象未初次化
		if (obj == null) {
			obj = cls.newInstance();
		}

		// 设置普通系统对象的属性
		BeanUtil.bindProperty(obj, propertyName, value);
		Method setMethod = bean.getClass().getMethod("set" + funName,
				new Class[] { cls });
		// 把对象填充
		setMethod.invoke(bean, new Object[] { obj });
	}
	
	public static void noNull(Object obj) throws NullPointerException{
		if(obj == null){
			throw new NullPointerException("cant no null");
		}
	}
}
