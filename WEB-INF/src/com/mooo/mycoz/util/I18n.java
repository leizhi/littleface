package com.mooo.mycoz.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Locale;
import java.util.ResourceBundle;
 
public class I18n {

	private static Log log = LogFactory.getLog(I18n.class);

	private static Locale locale = null;
	private static ResourceBundle messages = null;

	public I18n() {
                 locale = Locale.getDefault();
                 messages = ResourceBundle.getBundle("MessageBundle",locale);
		}

	public I18n(String language,String country){
		setMessages(language,country);
		}

	public static void setMessages(String language,String country){
                 locale = new Locale(language, country);
                 messages = ResourceBundle.getBundle("MessageBundle",locale);
		}

	public static String getValue(String key){

		if (messages != null) 
			return  messages.getString(key);
		else if (key != null)
			return key;
		else 
			return "";
		}

}
