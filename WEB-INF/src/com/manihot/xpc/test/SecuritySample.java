package com.manihot.xpc.test;

import com.manihot.xpc.tools.Caesar;

public class SecuritySample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Caesar ca = new Caesar();
		System.out.println(ca.encryption(args[0],Integer.parseInt(args[1])));// 输出密文
	}

}
