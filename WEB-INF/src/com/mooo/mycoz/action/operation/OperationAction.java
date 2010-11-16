package com.mooo.mycoz.action.operation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OperationAction {
	public String menu(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> gbar = new HashMap<String, String>();
		gbar.put("Activity","Activity.do");
		gbar.put("File","");

		request.setAttribute("gbar", gbar);
		return "success";
	}
}
