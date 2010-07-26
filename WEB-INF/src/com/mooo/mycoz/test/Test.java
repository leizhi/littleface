package com.mooo.mycoz.test;

import java.util.StringTokenizer;

import com.mooo.mycoz.util.ConfigureUtil;

public class Test {

	public static void main(String[] args) {
/*
		ConfigureUtil cu = ConfigureUtil.getInstance();
		cu.setCacheFile("/home/apache-tomcat-5.5.30/webapps/littleface/WEB-INF/config/myconfig.xml");
		cu.setMvcFile("/home/apache-tomcat-5.5.30/webapps/littleface/WEB-INF/config/myconfig.xml");
		cu.conf();
		cu.printConf();
		*/
		String name = "name";

		StringTokenizer st = new StringTokenizer(name, ".");
		st.countTokens();
		while (st.hasMoreElements()) {
			System.out.println(st.countTokens());

			// System.out.println(st.nextToken());
			if (st.countTokens() > 1) {
				if (st.nextToken().equals("Example")) {
					System.out.println(st.nextToken());
				}
			} else {
				System.out.println(st.nextToken());
			}

		}
	}
}