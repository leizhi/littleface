package com.manihot.xpc.tools;

import java.util.Map;
import java.util.TreeMap;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CallReflection {
	private static void fillMap(Class c, Object obj, Map params) {
		Method[] methods = c.getDeclaredMethods();
		for (Method m : methods) {
			if (Modifier.isPublic(m.getModifiers())
					&& m.getName().startsWith("get")) {
				try {
					params.put(m.getName().substring(3), m.invoke(obj, null));
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	private static void fillObject(Class c, Object obj, Map params) {
		for (Object key : params.keySet()) {
			String paramName = (String) key;
			try {
				Method getMethod = c.getMethod("get" + paramName, null);
				Method setMethod = c.getMethod("set" + paramName, getMethod
						.getReturnType());
				setMethod.invoke(obj, params.get(key));
			} catch (NoSuchMethodException e) {
				continue;
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		Map params = new TreeMap();
		SampleBean bean = new SampleBean();

		bean.setAge(20);
		bean.setDepartment("PD");
		bean.setName("Chris Huang");
		bean.setSchool("Zhejiang University");
		fillMap(SampleBean.class, bean, params);
		for (Object key : params.keySet()) {
			System.out.println(key + ": " + params.get(key));
		}

		params.put("Age", 21);
		params.put("Department", "QA");
		params.put("Name", "Luna X");
		params.put("School", "Nanjing University");
		fillObject(SampleBean.class, bean, params);
		System.out.println("Age = " + bean.getAge());
		System.out.println("Department = " + bean.getDepartment());
		System.out.println("Name = " + bean.getName());
		System.out.println("School = " + bean.getSchool());
	}
}