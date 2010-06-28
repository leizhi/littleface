package org.pig.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PigNode {

    private static Log log = LogFactory.getLog(PigNode.class);

    public String type = null;
    public String path = null;
    public String defaultState = null;
    public Hashtable forward = null;

public String getType() {
	return type;
	}
public String getPath() {
	return path;
	}
public String getDefaultState() {
	return defaultState;
	}
public Hashtable getForward() {
	return forward;
	}

public void setType(String type) {
	this.type = type;
	}
public void setPath(String path) {
	this.path = path;
	}
public void setDefaultState(String state) {
	this.defaultState = state;
	}

public void addForward(String name,String path) {
	if (forward == null) forward = new Hashtable();
	forward.put(name,path);
	}
}
