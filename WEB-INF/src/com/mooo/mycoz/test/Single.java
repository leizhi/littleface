package com.mooo.mycoz.test;


public class Single {

    
    String name;
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private Single(){
    	name="Single";
    }

    private static Object initLock = new Object();
    private static Single factory = null;
	
    public static Single getInstance() {

        if (factory == null) {
            synchronized(initLock) {
                if (factory == null) {
                	factory = new Single();
                }
            }
        }
        return factory;
    }
}
