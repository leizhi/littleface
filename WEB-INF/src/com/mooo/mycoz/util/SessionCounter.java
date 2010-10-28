package com.mooo.mycoz.util;

import java.util.HashSet;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {

	public static HashSet<String> users = new HashSet<String>();
	private static int activeSessions = 0;

	public void sessionCreated(HttpSessionEvent se) {
		activeSessions++;
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		if (activeSessions > 0)
			activeSessions--;
		users.remove(se.getSession().getId());
	}

	public static int getActiveSessions() {
		return activeSessions;
	}

	public static void put(String sessionId) {
		users.add(sessionId);
	}

	public static int getRealCount() {
		return users.size();
	}

	public static void remove(String sessionId) {
		if (activeSessions > 0)
			activeSessions--;
		users.remove(sessionId);
	}
}
