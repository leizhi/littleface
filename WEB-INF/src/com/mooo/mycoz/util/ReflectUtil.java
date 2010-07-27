package com.mooo.mycoz.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtil {

	public static Class<?> clazz;

	public static List<String> getMethodNames(String className) {
		List<String> names = new ArrayList<String>();

		try {

			clazz = Class.forName(className);

			Method[] methods = clazz.getDeclaredMethods();

			for (Method method : methods) {
				names.add(method.getName());
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return names;

	}

	public static List<String> getMethodNames(Class<?> clazz) {

		List<String> names = new ArrayList<String>();
		Method[] methods = clazz.getDeclaredMethods();

		for (Method method : methods) {
			names.add(method.getName());
		}
		
		return names;
	}
	
}
