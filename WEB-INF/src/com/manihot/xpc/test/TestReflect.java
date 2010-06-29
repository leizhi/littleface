package com.manihot.xpc.test;

import java.lang.reflect.Method;

public class TestReflect {
	public void a() {
		System.out.println("a");

	}

	public void b() {
		System.out.println("b");

	}

	public void c() {
		System.out.println("c");

	}

	public static void main(String[] args) {
		try {
			Class<?> tr = TestReflect.class;

			Method[] methods = tr.getDeclaredMethods();

			// List<String> names = new ArrayList<String>();

			for (Method method : methods) {
				// names.add(method.getName());
				System.out.println("checkAction=" + method.getName());
				Object obj = (Object) tr.newInstance();
				Class<?>[] paraTypes = new Class[] {};
				Object paraValues[] = new Object[] {};
				if (!method.getName().equals("main"))
					tr.getMethod(method.getName(), paraTypes).invoke(obj,
							paraValues);

			}

		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}
