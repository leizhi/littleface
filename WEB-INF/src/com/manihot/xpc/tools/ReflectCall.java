package com.manihot.xpc.tools;

import java.lang.reflect.Method;

public class ReflectCall {
	String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String a() {
		System.out.println("a");
		return "a";
	}

	public void b() {
		System.out.println("b");

	}

	public void c() {
		System.out.println("c");

	}

	public String execute() throws Exception {
		try {
			Class<?> clazz = this.getClass();

			Method[] methods = clazz.getDeclaredMethods();

			for (Method method : methods) {
				// Object obj = (Object) clazz.newInstance();
				Object obj = this;

				Class<?>[] paraTypes = new Class[] {};
				Object paraValues[] = new Object[] {};
				if (this.getAction() == null)
					return "error";
				else if (method.getName().equals(getAction()))
					// clazz.getMethod(method.getName(), null).invoke(obj,
					// null);
					return (String) clazz
							.getMethod(method.getName(), paraTypes).invoke(obj,
									paraValues);

			}
			return "succes";
		} catch (Throwable e) {
			System.err.println(e);
			return "default";
		}

	}
}
