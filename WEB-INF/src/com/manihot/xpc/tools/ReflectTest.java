package com.manihot.xpc.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("com.manihot.xpc.tools.Test");
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods = clazz.getDeclaredMethods();

		System.out.println("--------------- 属性如下  -----------------");

		for (Field field : fields) {
			int mod = field.getModifiers();
			System.out.println(Modifier.toString(mod) // 取得修饰符
					+ " " + field.getType().getName() // 取得类型名
					+ " " + field.getName()); // 取得属性名
		}

		System.out.println("--------------- 方法如下  -----------------");

		for (Method method : methods) {
			StringBuffer methodBuffer = new StringBuffer();
			int mod = method.getModifiers();
			methodBuffer.append(Modifier.toString(mod)) // 取得修饰符
					.append(" ").append(method.getReturnType().getName()) // 取得返回值类型
					.append(" ").append(method.getName()) // 取得方法名
					.append("(");

			for (Class<?> ss : method.getParameterTypes()) {
				methodBuffer.append(ss.getName()).append(","); // 取得参数
			}

			if (methodBuffer.lastIndexOf(",") > 0)
				methodBuffer.deleteCharAt(methodBuffer.lastIndexOf(",")); // 去掉最后一个","

			methodBuffer.append(")");
			System.out.println(methodBuffer.toString()); // 打印输出
		}

		System.out.println("--------------- 执行如下  -----------------");
		Class<?> rt = Class.forName("com.manihot.xpc.tools.Test");
		// Class<?> rt = Test.class;
		Class<?>[] paraTypes = new Class[] {};
		Object paraValues[] = new Object[] {};

		Object obj = (Object) rt.newInstance();

		rt.getMethod("sayHello", paraTypes).invoke(obj, paraValues);
		// Method method = methods[]
	}

	public void sayHello() {
		System.out.println("Hello Call me.");

	}
}
