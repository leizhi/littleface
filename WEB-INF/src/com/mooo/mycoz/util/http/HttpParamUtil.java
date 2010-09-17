package com.mooo.mycoz.util.http;

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
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;

import com.mooo.mycoz.util.BeanUtil;
import com.mooo.mycoz.util.Transaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpParamUtil {
	private static Log log = LogFactory.getLog(HttpParamUtil.class);

	private static String FORMAT_NAME = "format_";

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
					BeanUtil.bindSubObject(bean, objName, propertyName, value, request.getParameter(FORMAT_NAME + name));
				} else {
					BeanUtil.bindProperty(bean, name, value, request.getParameter(FORMAT_NAME + name));
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

}
