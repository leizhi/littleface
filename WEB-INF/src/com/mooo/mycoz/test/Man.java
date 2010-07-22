package com.mooo.mycoz.test;

public class Man implements People {

	String id = null;
	public Man(String id) {
		this.id = id;
	}
	public void think(){
		System.out.println(id + " Man think");
	}

	public void say() {
		System.out.println(id + " Man say");
	}
}
