package com.mooo.mycoz.test;

import com.mooo.mycoz.util.ConfigureUtil;

public class Test {

	public static void main(String[] args) {
		ConfigureUtil cu = ConfigureUtil.getInstance();
		cu.setCacheFile("/home/apache-tomcat-5.5.30/webapps/littleface/WEB-INF/config/myconfig.xml");
		cu.setMvcFile("/home/apache-tomcat-5.5.30/webapps/littleface/WEB-INF/config/myconfig.xml");
		cu.conf();
		cu.printConf();
	}
}