package com.mooo.mycoz.test;

import com.mooo.mycoz.util.ConfigureUtil;

public class Test {

	public static void main(String[] args) {
		ConfigureUtil cu = ConfigureUtil.getInstance();
		cu.conf();
		cu.printConf();
	}
}
