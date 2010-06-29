package com.manihot.xpc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class IDGenerator {

	// private static Log log = LogFactory.getLog(IDGenerator.class);

	public static Map<?, ?> getSexs() {
		HashMap<Object, Object> sexs = new HashMap<Object, Object>();
		sexs.put("Male", "Male");
		sexs.put("Female", "Female");
		return sexs;
	}

	public static Map<?, ?> getEnables() {
		HashMap<Object, Object> sexs = new HashMap<Object, Object>();
		sexs.put("Yes", "Yes");
		sexs.put("No", "No");
		return sexs;
	}

	public static String getIdPrifix() {
		TimeZone tz = TimeZone.getDefault();
		Calendar now = Calendar.getInstance(tz);
		String prefix = now.toString();
		int tmp = now.get(Calendar.YEAR);
		prefix = (tmp + "").substring(2, 4);
		tmp = now.get(Calendar.MONTH) + 1;
		if (tmp < 10)
			prefix += "0" + tmp;
		else
			prefix += tmp;

		return prefix;
	}

	public static String getYYMM() {
		return getIdPrifix();
	}

	public static String getYY() {
		TimeZone tz = TimeZone.getDefault();
		Calendar now = Calendar.getInstance(tz);
		String prefix = now.toString();
		int tmp = now.get(Calendar.YEAR);
		prefix = (tmp + "").substring(2, 4);

		return prefix;
	}
	
	public static Date getDate() {
		
		TimeZone tz = TimeZone.getDefault();
		Calendar now = Calendar.getInstance(tz);
		Date date = now.getTime();

		return date;
	}
	
	public static String getNowTime() {
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
	
		return ft.format(getDate());
	}
	
	public static String getNowYear() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy");
	
		return ft.format(getDate());
	}
	
	public static String getNow() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		return ft.format(getDate());
	}
}
