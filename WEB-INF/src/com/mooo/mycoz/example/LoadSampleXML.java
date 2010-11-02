package com.mooo.mycoz.example;

import java.util.*;
import java.io.*;
public class LoadSampleXML {
	
    public void start() throws InvalidPropertiesFormatException, IOException{
        InputStream fis = getClass().getResourceAsStream("/ok.xml");

        Properties prop = new Properties();
        prop.loadFromXML(fis);
        prop.list(System.out);
        System.out.println("\nThe foo property: " + prop.getProperty("foo"));
    }
    
  public static void main(String args[]) throws Exception {
	  LoadSampleXML lx = new LoadSampleXML();
	  lx.start();
  }
}

