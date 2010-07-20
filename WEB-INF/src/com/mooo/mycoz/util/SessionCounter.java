package com.mooo.mycoz.util;

import java.util.HashSet;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {

	public static HashSet<String> m_real = new HashSet<String>();
	private static int activeSessions = 0;

	public void sessionCreated(HttpSessionEvent se) {
		activeSessions++;
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		if (activeSessions > 0)
			activeSessions--;
		m_real.remove(se.getSession().getId());
	}

	public static int getActiveSessions() {
		return activeSessions;
	}

	public static void put(String sessionId) {
		m_real.add(sessionId);
	}

	public static int getRealCount() {
		return m_real.size();
	}

}
