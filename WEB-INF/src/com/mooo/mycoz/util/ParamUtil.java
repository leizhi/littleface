package com.mooo.mycoz.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParamUtil {
	private static Log log = LogFactory.getLog(ParamUtil.class);

	private static String DATE_FORMAT_1 = "yyyy-MM-dd";

	private static String DATE_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";

	private static String DATE_MATCHER_1 = "^\\d{4}(\\-)\\d{1,2}\\1\\d{1,2}$";

	private static String DATE_MATCHER_2 = "^\\d{4}(\\-)\\d{1,2}\\1\\d{1,2}(\\s([0-1]\\d|[2][0-3])\\:[0-5]\\d\\:[0-5]\\d)$";

	private static String FORMAT_NAME = "format_";

	public static String getFunName(String objName) {
		String funName = objName.substring(0, 1).toUpperCase();

		if (objName.length() > 1) {
			//funName += objName.substring(1).toLowerCase();
			funName += objName.substring(1);
		}
		return funName;
	}

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
			String value, String format) throws NoSuchMethodException,
			InvocationTargetException, IllegalAccessException, ParseException,
			InstantiationException {

		// 得到方法名
		String funName = getFunName(propertyName);
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
			Method valueOf = cl.getMethod("valueOf",
					new Class[] { String.class });
			Object valueObj = valueOf.invoke(cl, new Object[] { value });
			setMethod.invoke(bean, new Object[] { valueObj });
		} else if (cl == java.util.Date.class || cl == java.sql.Date.class) {
			java.text.SimpleDateFormat simpleDateFormat = null;
			String formatChar = null;

			// 判断格式参数存在
			if (format != null) {
				formatChar = format;
				// 分别2个默认的格式化规制
			} else if (Pattern.compile(DATE_MATCHER_1).matcher(value).find()) {
				formatChar = DATE_FORMAT_1;
				// 分别2个默认的格式化规制
			} else if (Pattern.compile(DATE_MATCHER_2).matcher(value).find()) {
				formatChar = DATE_FORMAT_2;
			}

			if (formatChar != null) {
				simpleDateFormat = new java.text.SimpleDateFormat(formatChar);
				Date date = simpleDateFormat.parse(value);
				Object dateObj = cl.newInstance();
				Method setTime = cl.getMethod("setTime",new Class[] { long.class });
				setTime.invoke(dateObj, new Object[] { date.getTime() });
				setMethod.invoke(bean, new Object[] { dateObj });
			}
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
			String propertyName, String value, String format)
			throws NoSuchMethodException, InvocationTargetException,
			IllegalAccessException, ParseException, InstantiationException {
		String funName = getFunName(objName);
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
		bindProperty(obj, propertyName, value, format);
		Method setMethod = bean.getClass().getMethod("set" + funName,
				new Class[] { cls });
		// 把对象填充
		setMethod.invoke(bean, new Object[] { obj });
	}

	/**
	 * 动态从request.getParameter里绑定javaBean属性值.
	 * 
	 * @param request
	 *            http响应对象
	 * @param bean
	 *            绑定的javaBean
	 * @param excludes
	 *            过滤参数
	 * @param prefix
	 *            前缀 只取前缀的
	 */
	public static void bindData(HttpServletRequest request, Object bean,
			List<?> excludes, String prefix) {
		@SuppressWarnings("unchecked")
		Enumeration<String> em = request.getParameterNames();

		for (; em.hasMoreElements();) {
			try {
				String name = em.nextElement();
				String value = request.getParameter(name);

				if (value == null) {
					continue;
				}

				if (excludes != null && excludes.contains(value)) {
					continue;
				}

				if (prefix != null) {
					if (name.indexOf(prefix + ".") != 0) {
						continue;
					}
					name = name.replaceFirst(prefix + ".", "");
				}

				value = value.trim();
				if (name.indexOf(".") > -1) {
					int start = name.indexOf(".");
					String objName = name.substring(0, start);
					String propertyName = name.substring(start + 1, name.length());
					bindSubObject(bean, objName, propertyName, value, request.getParameter(FORMAT_NAME + name));
				} else {
					bindProperty(bean, name, value, request.getParameter(FORMAT_NAME + name));
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace(); // To change body of catch statement use
				// File | Settings | File Templates.
			} catch (IllegalAccessException e) {
				e.printStackTrace(); // To change body of catch statement use
				// File | Settings | File Templates.
			} catch (ParseException e) {
				e.printStackTrace(); // To change body of catch statement use
				// File | Settings | File Templates.
			} catch (InstantiationException e) {
				e.printStackTrace(); // To change body of catch statement use
				// File | Settings | File Templates.
			} catch (InvocationTargetException e) {
				e.printStackTrace(); // To change body of catch statement use
				// File | Settings | File Templates.
			}
		}
	}

	/**
	 * 动态从request.getParameter里绑定javaBean属性值.
	 * 
	 * @param request
	 *            http响应对象
	 * @param bean
	 *            绑定的javaBean
	 */
	public static void bindData(HttpServletRequest request, Object bean) {
		bindData(request, bean, null, null);
	}
	
	/**
	 * 动态从request.getParameter里绑定javaBean属性值.
	 * 
	 * @param request
	 *            http响应对象
	 * @param bean
	 *            绑定的javaBean
	 * @param bean
	 *            绑定的javaBran前缀
	 */
	public static void bindData(HttpServletRequest request, Object bean,String prefix) {
		bindData(request, bean, null, prefix);
	}
	
	public static void fillMap(Class<?> c, Object obj, Map<String, Object> params) {
		Method[] methods = c.getDeclaredMethods();
		for (Method m : methods) {
			if (Modifier.isPublic(m.getModifiers())
					&& m.getName().startsWith("get")) {
				try {
					params.put(m.getName().substring(3), m.invoke(obj));
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	public static void fillObject(Class<?> c, Object obj, Map<?, ?> params) {
		for (Object key : params.keySet()) {
			String paramName = (String) key;
			try {
				Method getMethod = c.getMethod("get" + paramName);
				Method setMethod = c.getMethod("set" + paramName, getMethod.getReturnType());
				setMethod.invoke(obj, params.get(key));
				if(log.isDebugEnabled()) log.debug("");
			} catch (NoSuchMethodException e) {
				continue;
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	public static String WriteUTF8(String input) {
		try {
			return new String(input.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	//Transaction
	public static void add(HttpServletRequest request, String table) {
		@SuppressWarnings("unchecked")
		Enumeration<String> em = request.getParameterNames();
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		ResultSet rs = null;
		Transaction tx = new Transaction();
		
		try {
			tx.start();

			StringBuffer sql =  new StringBuffer("INSERT INTO " + table);
			StringBuffer fileds = new StringBuffer();
			StringBuffer values = new StringBuffer();
			String value,colName;
			
			List<String> col = new ArrayList<String>();
			
			stmt = tx.getConnection().createStatement();
			rs = stmt.executeQuery("SELECT * FROM " + table);
			rsmd = rs.getMetaData();

			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				col.add(rsmd.getColumnName(i + 1).toUpperCase());
			}
			
			while (em.hasMoreElements()) {
				String name = em.nextElement();
				StringTokenizer st = new StringTokenizer(name, ".");
				
					if (st.countTokens() > 1) {
						
						if (st.nextToken().equals(table)) {
							
							value = request.getParameter(name);
							colName = st.nextToken();
							
							if (col.contains(colName.toUpperCase())) {
								
								if (fileds.length() > 0) {
									fileds.append("," + colName);
									values.append(",'" + value + "'");
								} else {
									fileds.append("(" + colName);
									values.append(" VALUES ('" + value + "'");
								}
							}
						}
					} // must conform to the rules
			}
			
			if (fileds.length() > 0)
				fileds.append(") ");
			if (values.length() > 0)
				values.append(") ");

			if (sql.length() > 0)
				sql.append(fileds);
			if (sql.length() > 0)
				sql.append(values);
			
			if (log.isDebugEnabled()) log.debug("add sql=" + sql);
			System.out.println("add sql=" + sql);

			stmt = tx.getConnection().createStatement();
			stmt.executeUpdate(sql.toString());
			
			// rs = stmt.executeQuery(sql);
			// while (rs.next()) {
			// System.out.println("Name=" + rs.getString("XZQH_MC"));
			// }
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			tx.end();
		}

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("fullName="+getFunName("saDDDDsafa"));
	}
}
