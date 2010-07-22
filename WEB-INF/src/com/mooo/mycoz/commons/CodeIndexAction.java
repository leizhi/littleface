package com.mooo.mycoz.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;

public class CodeIndexAction extends BaseSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8661620381677186956L;
	private static Log log = LogFactory.getLog(CodeIndexAction.class);

	
	public String list() throws Exception {
		try {

		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("Exception error:" + e.getMessage());
			e.fillInStackTrace();

		}
		return "list";
	}

	public String processAdd() throws Exception {
		try {

		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("Exception error:" + e.getMessage());
			e.fillInStackTrace();

		}
		return "processAdd";
	}

	public String processUpdate() throws Exception {
		try {

		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("Exception error:" + e.getMessage());
			e.fillInStackTrace();

		}
		return "processUpdate";
	}

	public String processDelete() throws Exception {
		try {

		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("Exception error:" + e.getMessage());
			e.fillInStackTrace();

		}
		return "processDelete";
	}
}
