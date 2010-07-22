package com.mooo.mycoz.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtil {

	Class<?> clazz;

	public List<String> getMethodNames(String className)
			throws ClassNotFoundException {

		clazz = Class.forName(className);

		Method[] methods = clazz.getDeclaredMethods();
		List<String> names = new ArrayList<String>();

		for (Method method : methods) {
			names.add(method.getName());
		}

		return names;

	}

}
