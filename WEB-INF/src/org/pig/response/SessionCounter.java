package org.pig.response;

import java.util.Hashtable;
import java.util.HashSet;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionCounter implements HttpSessionListener {

private static Log log = LogFactory.getLog(SessionCounter.class);
public static HashSet m_real = new HashSet();
private static int activeSessions = 0;

public void sessionCreated(HttpSessionEvent se) {
	activeSessions++;
}

public void sessionDestroyed(HttpSessionEvent se) {
	if(activeSessions > 0)
		activeSessions--;
		m_real.remove(se.getSession().getId()); 
}

public static int getActiveSessions() {
	return activeSessions;
}

public static void put(String sessionId){ 
        m_real.add(sessionId);
    }

public static int getRealCount(){
        return m_real.size();
    }

}
