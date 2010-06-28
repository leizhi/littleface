package org.pig.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Hashtable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionUtil {

	public	void State(String name,String desc);
	public	void addState(String name,String desc);
	public	void exec(HttpServletRequest request,HttpServletResponse response);

}
