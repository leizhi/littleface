package com.mooo.mycoz.util;

import java.util.Hashtable;

public class ActionNode {

	public String name;
	public String cls;
	public String state;
	public Hashtable<String, String> results;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Hashtable<String, String> getResults() {
		return results;
	}

	public void setResults(Hashtable<String, String> results) {
		this.results = results;
	}

	public void addResult(String name, String path) {
		if (results == null)
			results = new Hashtable<String, String>();
		results.put(name, path);
	}
}
