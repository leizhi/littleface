package com.mooo.mycoz.tools;

import java.util.*;

public class PrintUtil {

	public static void print(List<String> list) {
		long l1 = System.currentTimeMillis();
		String value;
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			value = (String) list.get(i);
			index = (int) list.indexOf(value);
			System.out.println("index=" + index + " value=" + value);
		}
		System.out.println("pring millis=" + (System.currentTimeMillis() - l1));
	}

	public static void list1(List<String> list) {
		long l1 = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			String string = list.get(i);
			System.out.println(string);
		}
		System.out.println("1=" + (System.currentTimeMillis() - l1));
	}

	public static void list2(List<String> list) {
		long l1 = System.currentTimeMillis();
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			// String str =
			it.next();
			// System.out.println(str);
		}
		System.out.println("2=" + (System.currentTimeMillis() - l1));
	}

	public static void list3(List<String> list) {
		long l1 = System.currentTimeMillis();
		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			// System.out.println(it.next());
			it.next();
		}
		System.out.println("3=" + (System.currentTimeMillis() - l1));
	}

	public static void list4(List<String> list) {
		long l1 = System.currentTimeMillis();
		int i = 0;
		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			// System.out.println(it.next());
			it.next();
			i++;
		}
		System.out.println("4=" + (System.currentTimeMillis() - l1));
	}

	public static void printMap(Map<String, String> map) {
		long l1 = System.currentTimeMillis();
		int i = 0;
		@SuppressWarnings("unused")
		String key, value;
		for (Iterator<?> it = map.keySet().iterator(); it.hasNext();) {
			key = (String) it.next();
			value = (String) map.get(key);
			System.out.println(key);

			i++;
		}
		System.out.println("4=" + (System.currentTimeMillis() - l1));
	}

	@SuppressWarnings("unchecked")
	public static void printList(List<Map<String, Object>> list) {
		long l1 = System.currentTimeMillis();
		Map one = null;
		String key, value;
		for (Iterator it = list.iterator(); it.hasNext();) {
			one = (Map) it.next();
			for (Iterator<?> im = one.keySet().iterator(); im.hasNext();) {
				key = (String) it.next();
				value = (String) one.get(key);
				System.out.println(key + "=" + value);
			}
		}
		System.out.println("3=" + (System.currentTimeMillis() - l1));
	}
}
