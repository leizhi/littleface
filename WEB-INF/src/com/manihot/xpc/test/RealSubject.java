package com.manihot.xpc.test;


public class RealSubject implements Subject {
	public RealSubject() {

	}

	public void request() {
		System.out.println("From real subject.");
	}
}
