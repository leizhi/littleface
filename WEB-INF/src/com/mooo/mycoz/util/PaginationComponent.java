package com.mooo.mycoz.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;



import java.lang.reflect.Method;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;
import java.lang.ClassNotFoundException;


import com.mooo.mycoz.action.ActionServlet;
import com.mooo.mycoz.request.Input;
import com.mooo.mycoz.util.DBLoad;
import com.mooo.mycoz.util.DBMap;
import com.mooo.mycoz.util.DBNode;
import com.mooo.mycoz.util.I18n;
import com.mooo.mycoz.util.MysqlConnection;
import com.mooo.mycoz.util.PigConfigNode;
import com.mooo.mycoz.util.PigLoad;
import com.mooo.mycoz.util.PigMap;
import com.mooo.mycoz.util.PigNode;
import com.mooo.mycoz.util.Transition;

public class PaginationComponent  {

    /**
     * Utility method to set up the log for this object
     */
    private static Log log = LogFactory.getLog(PaginationComponent.class);

    int total = 0; 
    int currentPage = 1;
    public int getCurrentPage() {
	return currentPage;
    }
    int offset = 1;
    public int getOffset() {
	return offset;
    }
    int countPerPage = 25;
    public int getCountPerPage() {
	return countPerPage;
    }
    public void setCountPerPage(int i) {
        this.countPerPage = i;
    }

    String listState = ""; 
    public String getListState() {
	return listState;
    }
    public void setListState(String listState) {
	this.listState =  listState;
    }
    String listController = ""; 
    public String getListController() {
	return listController;
    }
    public void setListController(String listController) {
	this.listController =  listController;
    }
    
    HttpServletRequest request; 
    public HttpServletRequest getRequest() {
	return request;
    }
    public void setRequest(HttpServletRequest request) {
	this.request =  request;
    }
    HttpServletResponse response; 
    public HttpServletResponse getResponse() {
	return response;
    }
    public void setResponse(HttpServletResponse response) {
	this.response =  response;
    }
    
    public HttpServletRequest buildComponent(HttpServletRequest request, String listController, String listState, int total) throws ServletException, IOException {
	
	this.total = total;
	this.listController = listController;
	this.listState = listState;
        // allow to set countPerPage by callers
	// this.countPerPage = XpcConstants.RecordsPerPage;
	if(request.getParameter("CurrentPage") != null)
	    currentPage = Integer.parseInt(request.getParameter("CurrentPage"));
	if(request.getParameter("CountPerPage") != null)
	    countPerPage = Integer.parseInt(request.getParameter("CountPerPage"));
	
	try {
	    //handling pagination
	    int totalPage = total / countPerPage;
	    if(countPerPage * totalPage < total)
		totalPage ++;
	    if (currentPage > totalPage)
		currentPage = totalPage;
	    if (currentPage < 1)
		currentPage = 1;
	    offset = (currentPage - 1) * countPerPage;

	    Input in = new Input();
	    in.addValue(request,"CurrentPage",currentPage);
	    in.addValue(request,"CountPerPage",countPerPage);
	    in.addValue(request,"TotalPage",totalPage);

	    in.addSubmit(request,"Next",listController,listState);
	    in.addSubmit(request,"Previous",listController,listState);
	    in.addSubmit(request,"JumpTo",listController,listState);

	    if (log.isDebugEnabled()) {
		log.debug("buildCom class name = " + getClass().getName());
		log.debug("buildCom currentPage = " + currentPage);
		log.debug("buildCom totalPage = " + totalPage);
		log.debug("buildCom offset = " + offset);
	    }
	     /*
		* foward state
		*/
	int pageCurrent = 1;

	if ((request.getParameter("FowardName")).equals("Next") ){ 
        	if (request.getParameter("CurrentPage") != null) {
	    		try {
				pageCurrent = Integer.parseInt(request.getParameter("CurrentPage"));
				pageCurrent ++; 
	    		} catch (Exception e) {
				pageCurrent = 1; 
	    		} 
       		} 
	}else if ((request.getParameter("FowardName")).equals("Previous")) {
        	if(request.getParameter("CurrentPage") != null){
            		try{
                		pageCurrent = Integer.parseInt(request.getParameter("CurrentPage"));
                		pageCurrent --;
            		} catch (Exception e){
                		pageCurrent = 1;
            		}
        	}

        	if(pageCurrent < 1)
            		pageCurrent = 1;
	}//end if


	}catch (Exception e) {
	    throw new ServletException("Exception:" + e.getMessage());
	} finally {
	    return request;
	}
    }

}

