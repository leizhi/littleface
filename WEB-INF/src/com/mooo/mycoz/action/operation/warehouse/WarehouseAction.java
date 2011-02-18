package com.mooo.mycoz.action.operation.warehouse;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;

public class WarehouseAction extends BaseSupport {
	
	private static Log log = LogFactory.getLog(WarehouseAction.class);
	
	// default
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled())log.debug("TransactionAction");
		
		Map<String, String> gbar = new HashMap<String, String>();
		gbar.put("AccountElement","");
		gbar.put("AccountCategory","");
		gbar.put("AccountType","");
		gbar.put("AccountGroup","");
		gbar.put("Account","");
		request.setAttribute("gbar", gbar);

		return "success";
	}
	
	public String sale(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled())log.debug("TransactionAction");
		
		Map<String, String> gbar = new HashMap<String, String>();
		gbar.put("AccountElement","");
		gbar.put("AccountCategory","");
		gbar.put("AccountType","");
		gbar.put("AccountGroup","");
		gbar.put("Account","");
		request.setAttribute("gbar", gbar);

		return "success";
	}
	
	public String buy(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled())log.debug("TransactionAction");
		
		Map<String, String> gbar = new HashMap<String, String>();
		gbar.put("AccountElement","");
		gbar.put("AccountCategory","");
		gbar.put("AccountType","");
		gbar.put("AccountGroup","");
		gbar.put("Account","");
		request.setAttribute("gbar", gbar);

		return "success";
	}
}
