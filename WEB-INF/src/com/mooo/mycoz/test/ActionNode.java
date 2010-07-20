package com.mooo.mycoz.test;

import java.util.HashMap;

public class ActionNode {

	public static String name = null;
	public static String className = null;
	public static HashMap<String, String> results = null;
	
	public ActionNode(){
		name = "";
		className = "";
		results = new HashMap<String, String>();
	}
	
	public static String getName() {
		return name;
	}
	
	public static void setName(String name) {
		ActionNode.name = name;
	}
	
	public static String getClassName() {
		return className;
	}
	
	public static void setClassName(String className) {
		ActionNode.className = className;
	}
	
	public static HashMap<String, String> getResults() {
		return results;
	}
	
	public static void setResults(HashMap<String, String> results) {
		ActionNode.results = results;
	}

}
