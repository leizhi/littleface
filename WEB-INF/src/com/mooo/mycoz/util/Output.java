package com.mooo.mycoz.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Hashtable;

public class Output  {

private static Log log = LogFactory.getLog(Output.class);

     private static Hashtable defaultValues = null;
     private static Hashtable inputValues = null;
     private static Hashtable onClickValues = null;

     public static void setDefault(String property,String key){
		 if (defaultValues==null) 
			defaultValues = new Hashtable();

		 if ( key != null)
		 	defaultValues.put(property,key);

	}

     public static String getDefault(String key) {

		String value = null;
		try {
			if (defaultValues==null) 
				defaultValues = new Hashtable();

			if (defaultValues.containsKey(key))
				value = (String)defaultValues.get(key);
		}catch(Exception e) {
			if (log.isDebugEnabled()) log.error("Exception:"+e.toString());
		}

		return value;
        }

     public static void setValue(String name,String value) {

		if (inputValues==null) inputValues = new Hashtable();

		if(value != null)
			inputValues.put(name,value);
		else
			inputValues.put(name,"");
        }

     public static void setValue(String name,int value) {

		if (inputValues==null) inputValues = new Hashtable();

		inputValues.put(name,Integer.toString(value));
        }

     public static String getValue(String name) {
		return (String)inputValues.get(name);
        }

     public static void setValues(String name,Hashtable value) {
		if (inputValues==null) inputValues = new Hashtable();
		inputValues.put(name,value);
        }

     public static Hashtable getValues(String name) {
		return (Hashtable)inputValues.get(name);
        }

     public static void setOnClick(String name,String url) {

		if (onClickValues==null) 
			onClickValues = new Hashtable();
		if(url != null)
			onClickValues.put(name,url);
        }

     public static String getOnClick(String key) {
		String value = null;
		try {
			if (onClickValues==null) 
				onClickValues = new Hashtable();

			if(key == null) 
				return null;
			else 
				value = (String)onClickValues.get(key);
		}catch(Exception e) {
			if (log.isDebugEnabled()) log.error("Exception ddd:"+e.toString());
		}

		return value;
        }

     public static void clean(){
		defaultValues = null;
		inputValues = null;
	}

}
