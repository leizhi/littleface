package com.mooo.mycoz.util;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {

	private static int activeSessions = 0;

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		System.out.println("sessionCreated datetime = " + (session.getLastAccessedTime()-session.getCreationTime()));
		activeSessions++;
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		System.out.println("sessionDestroyed datetime = " + (session.getLastAccessedTime()-session.getCreationTime()));
		
		/*
		AccessLog al = new AccessLog();
		al.setIp(getClinetIp(request));
		al.setStartdate(new Date(session.getCreationTime()));
		dbProcess.retrieve(al);
		
		al.setEnddate(new Date(session.getLastAccessedTime()));
		dbProcess.update(al);
		*/
		if (activeSessions > 0)
			activeSessions--;
	}
	
	public static int getCount(){
		return activeSessions;
	}
	
	public static void login(){
		activeSessions++;
	}
	
	public static void logout(){
		if (activeSessions > 0)
			activeSessions--;
	}
}
