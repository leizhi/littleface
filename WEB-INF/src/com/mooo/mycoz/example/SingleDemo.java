package com.mooo.mycoz.example;


public class SingleDemo {

    
    String name;
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private SingleDemo(){
    	name="Single";
    }

    private static Object initLock = new Object();
    private static SingleDemo factory = null;
	
    public static SingleDemo getInstance() {

        if (factory == null) {
            synchronized(initLock) {
                if (factory == null) {
                	factory = new SingleDemo();
                }
            }
        }
        return factory;
    }
}
