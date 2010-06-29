package com.manihot.xpc.tools;

public class UserClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Subject subject = (Subject) DynamicProxy.getInstance(new RealSubject());
		subject.request();
	}

}
