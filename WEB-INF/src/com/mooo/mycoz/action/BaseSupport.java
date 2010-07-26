package com.mooo.mycoz.action;

import java.util.List;

import com.mooo.mycoz.commons.Pager;

/**
 * Base Action class for the controller package.
 */

public class BaseSupport {
	
	private static final long serialVersionUID = 1L;
	
	public static final String USER_SESSION_KEY = "UserSessionKey";
	
	/*
	 * configure general action
	 */
	public Pager pager;
	public List<?> generalItems;

	/*
	 * configure state
	 */

	public Boolean list;
	public Boolean promptAdd;
	public Boolean processAdd;
	public Boolean promptUpdate;
	public Boolean processUpdate;
	public Boolean processDelete;
	public Boolean preview;
	
	public String layout;
	
	// public Authentication auth;
	
	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public BaseSupport() {
		//pager = new Pager();
		this.setLayout("skin/office/default");
	}
	
}
