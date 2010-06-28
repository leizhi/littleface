package org.pig.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Hashtable;

public class PigConfigNode {

	private static Log log = LogFactory.getLog(PigConfigNode.class);

	public static String rootPath = null;
	public static String configPath = null;
	public static String mvcPath = null;

	public static String getRootPath() {
		if (rootPath != null)
			return rootPath;
		else 
			return null;
		}
	public static String getConfigPath() {
		if (configPath != null)
			return configPath;
		else 
			return null;
		}
	public static String getMvcPath() {
		if (configPath != null)
			return mvcPath;
		else 
			return null;
		}

	public static void setRootPath(String str) {
		rootPath = str;
		}
	public static void setConfigPath(String str) {
		configPath = str;
		}
	public static void setMvcPath(String str) {
		mvcPath = str;
	}
}
