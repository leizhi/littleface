package com.mooo.mycoz.example.reflection;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.mooo.mycoz.util.BeanUtil;

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
				Method setMethod = c.getMethod("set" + paramName, 
						getMethod.getReturnType());
				setMethod.invoke(obj, params.get(key));
			} catch (NoSuchMethodException e) {
				continue;
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		//Map params = new TreeMap();
		SampleBean sampleBean = new SampleBean();
		try {
			BeanUtil.bindProperty(sampleBean, "id", "100");
			BeanUtil.bindProperty(sampleBean, "name", "OK");
			BeanUtil.bindProperty(sampleBean, "createDate", "2010/10/31 11:38:26");
			//BeanUtil.bindProperty(sampleBean, "createDate", new Date());
			BeanUtil.bindSubObject(sampleBean, "subBean","joinDate", "2010/10/1 11:8:26");
			BeanUtil.bindSubObject(sampleBean, "subBean","id", "20");

			System.out.println("Name = " + sampleBean.getId());
			System.out.println("Name = " + sampleBean.getName());
			System.out.println("Name = " + sampleBean.getCreateDate());
			System.out.println("Name = " + sampleBean.getSubBean().getJoinDate());
			System.out.println("Name = " + sampleBean.getSubBean().getId());

		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

/*
		bean.setName("Chris Huang");
		fillMap(SampleBean.class, bean, params);
		for (Object key : params.keySet()) {
			System.out.println(key + ": " + params.get(key));
		}

		params.put("Age", 21);
		params.put("Department", "QA");
		params.put("Name", "Luna X");
		params.put("School", "Nanjing University");
		fillObject(SampleBean.class, bean, params);
		*/
	}
}