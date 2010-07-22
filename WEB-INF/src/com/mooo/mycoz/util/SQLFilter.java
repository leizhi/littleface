package com.mooo.mycoz.util;

public class SQLFilter {

	public static String nullNo(String check) {
		if (check == null)
			return "";
		else
			return check.trim();
	}

	public static String sqlLike(String check) {
		if (check == null)
			return "%%";
		else
			return "%" + check.trim() + "%";
	}

	public static String sqlLike(Integer check) {
		return sqlLike(format(check));
	}

	public static Integer format(String value) {
		if (value != null && !value.equals(""))
			return Integer.valueOf(value).intValue();
		else
			return 0;
	}

	public static String format(Integer value) {
		if (value != null && !value.equals(""))
			return String.valueOf(value);
		else
			return "0";
	}
}
