package com.mooo.mycoz.tools;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {

	private static Object object;

	@SuppressWarnings("static-access")
	public DynamicProxy(Object object) {
		this.object = object;
	}

	/**
	 * 动态代理类工厂方法
	 * 
	 * @param object
	 * @return
	 */
	public static Object getInstance(Object object) {
		Class<?> cls = object.getClass();
		// 生成动态代理类实例
		return Proxy.newProxyInstance(cls.getClassLoader(),
				cls.getInterfaces(), new DynamicProxy(object));
	}

	public static Object getInstance() {
		Class<?> cls = object.getClass();
		// 生成动态代理类实例
		return Proxy.newProxyInstance(cls.getClassLoader(),
				cls.getInterfaces(), new DynamicProxy(object));
	}

	@SuppressWarnings("static-access")
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before invoking....");

		Object object = method.invoke(this.object, args);

		System.out.println("after invoking....");
		return object;// 调用真实对象的代理方法
	}

}