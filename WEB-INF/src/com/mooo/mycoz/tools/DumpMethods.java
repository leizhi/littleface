package com.mooo.mycoz.tools;

/*
 * import java.lang.reflect.Method; import java.util.ArrayList; import
 * java.util.List;
 * 
 * public class DumpMethods {
 * 
 * public static void main(String args[]) { String className =
 * "com.manihot.xpc.tools.DumpMethods"; try { //Class c =
 * Class.forName(className); Class c = DumpMethods.class;
 * 
 * 
 * Class ptypes[] = new Class[2]; ptypes[0] = Class.forName("java.lang.String");
 * ptypes[1] = Class.forName("java.util.List"); Method m = c.getMethod("func",
 * ptypes); Object obj = (Object) c.newInstance(); Object arg[] = new Object[2];
 * arg[0] = new String("Hello world"); List list = new ArrayList();
 * list.add("val1"); list.add("val2"); arg[1] = list; Object r = m.invoke(obj,
 * arg); System.out.println(r.toString());
 * 
 * Method m = c.getMethod("sayHello", null); Object obj = (Object)
 * c.newInstance(); m.invoke(obj, null);
 * 
 * } catch (Throwable e) { System.err.println(e); }
 * 
 * }
 * 
 * public String func(String s, List list) { System.out.println("func invoked");
 * for (int i = 0; i < list.size(); i++) { s = s + " " + list.get(i).toString();
 * } return s;
 * 
 * }
 * 
 * public void sayHello(){ System.out.println("Hello Call me.");
 * 
 * } }
 */