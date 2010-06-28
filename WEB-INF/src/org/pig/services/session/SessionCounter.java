package org.pig.services.session;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionCounter implements HttpSessionListener {

private static Log log = LogFactory.getLog(SessionCounter.class);
private static int activeSessions = 0;

public void sessionCreated(HttpSessionEvent se) {
	activeSessions++;
}

public void sessionDestroyed(HttpSessionEvent se) {
	if(activeSessions > 0)
	activeSessions--;
}

public static int getActiveSessions() {
	return activeSessions;
}

}
