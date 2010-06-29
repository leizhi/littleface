package com.mooo.mycoz.sockt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClinetTest {

	private static Log log = LogFactory.getLog(ClinetTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int i=0;i<2500;i++) {
			new Client();
			if(log.isDebugEnabled()) log.debug("create client"+i);
		}
	}

}
