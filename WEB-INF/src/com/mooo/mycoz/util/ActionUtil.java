package com.mooo.mycoz.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionUtil {

	public	void State(String name,String desc);
	public	void addState(String name,String desc);
	public	void exec(HttpServletRequest request,HttpServletResponse response);

}
