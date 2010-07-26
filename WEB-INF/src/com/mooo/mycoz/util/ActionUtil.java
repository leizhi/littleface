package com.mooo.mycoz.util;

public class ActionUtil {

	public static String execPath(String accessPath) {

		String execPath = "";

		if (accessPath == null || accessPath.equals(""))
			return execPath;

		if (accessPath.indexOf(".") > 0) {

			if (accessPath.indexOf("/") > -1)
				execPath = accessPath.substring(1, accessPath.indexOf("."));
			else
				execPath = accessPath.substring(0, accessPath.indexOf("."));

		} else {
			if (accessPath.indexOf("/") > -1)
				execPath = accessPath.substring(1);
		}

		return execPath;
	}
	

}
