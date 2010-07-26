package com.mooo.mycoz.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;

import com.mooo.mycoz.dbobj.DBObject;
import com.mooo.mycoz.dbobj.TestBean;
import com.mooo.mycoz.dbobj.mycozBranch.Download;
import com.mooo.mycoz.util.ParamUtil;
import com.mooo.mycoz.util.ReflectUtil;

public class ReflectTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		//DBObject db = new DBObject();
		//Download bean = new Download();
		TestBean bean = new TestBean();
		List dl = bean.searchAndRetrieveList("select * from Download");
		
		for (Iterator it = dl.iterator(); it.hasNext();) {
			bean = (TestBean)it.next();
			System.out.println("name:"+bean.getName());
		}
		
		/*
		ReflectUtil ru = new ReflectUtil();
		ru.getMethodNames(Download.class);
		*/
		/*
		Download bean1;
		Download bean2;
		Download bean3;

		Object o1 = Download.class.newInstance();
		Object o2 = Download.class.newInstance();
		Object o3 = Download.class.newInstance();

		bean1 = (Download)o1;
		bean1.setName("name1");
		System.out.println(bean1.getName());
		bean2 = (Download)o2;
		bean2.setName("name2");
		System.out.println(bean2.getName());
		bean3 = (Download)o3;
		bean3.setName("name3");
		System.out.println(bean3.getName());
		
		System.out.println(bean1.getName());
		System.out.println(bean2.getName());
		System.out.println(bean3.getName());
*/
		/*
		Download bean = new Download();
		ParamUtil.bindProperty(bean, "Typename", "very good", null);
		ParamUtil.bindProperty(bean, "Name", "verdsafsdafd", null);

		System.out.println("fff"+bean.getTypename());
		System.out.println("fff"+bean.getName());
*/
		/*
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
		 */
	}

	public void sayHello() {
		System.out.println("Hello Call me.");

	}
}
