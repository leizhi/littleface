package com.mooo.mycoz.dbobj.mycozBranch;

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
import java.util.Calendar;
import java.util.TimeZone;
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

import com.mooo.mycoz.dbobj.DBObject;
import com.mooo.mycoz.dbobj.mycozShared.NoteType;




import com.mooo.mycoz.util.ActionServlet;

import com.mooo.mycoz.util.SAXParserConf;
import com.mooo.mycoz.util.ActionMap;


/**

 */
public class JobNote extends DBObject{
private static Log log = LogFactory.getLog(JobNote.class);
    /**
     */
    public JobNote() 
	throws SQLException{
	getConection();
	getStatement();
    } /* JobNote() */

    public int getNextID()
        throws SQLException {

		int id = 0;
		ResultSet rs = null;
		String sql = "SELECT MAX(ID) AS MaxId FROM JobNote";
		rs = getResultSet(sql);
		if(rs.first()) id=rs.getInt("MaxId")+1;

	return id;
    }/* getNextID() */

    public String getNextNoteNo(String noteTypeID) {

      	String prefix = "";
      	String nextNo = "";
	String sql = "";
      	ResultSet rs = null;

	try {

            	TimeZone tz = TimeZone.getDefault();
            	Calendar now = Calendar.getInstance(tz);
            	int iYear = now.get(Calendar.YEAR);
            	String defStr = "" + iYear;
            	int idx = defStr.length();
            	if (idx > 2)
              		defStr = defStr.substring(2, idx);
            	int iy = Integer.parseInt(defStr); // one year digit before 2010
            	int idCount = 0;

		NoteType nt = new NoteType();
		prefix = nt.getCode(noteTypeID);

              	sql = "SELECT COUNT(ID) FROM JobNote WHERE NoteTypeID="+noteTypeID+" AND  NoteNo LIKE '"+prefix+iy+"%' ";

            	rs = getResultSet(sql);
            	if(rs.next()) {
                	idCount = rs.getInt("COUNT(ID)") + 1;
            	} else {
                	idCount = 1;
            	}

            	nextNo = "" + idCount;
            	defStr = "00000";
            	idx = defStr.length() - nextNo.length();
            	if (idx < 0) throw new Exception("jobNoteID.length > 5");
            	nextNo = defStr.substring(0,idx) + nextNo;
            	nextNo = prefix + iy + nextNo;
            	boolean isValid = false;
            	int k = 0;
            	// validating nextNo, if not increase by 1
            	// up to 10 times
            	int maxCount = 10;

           	while (!isValid && k < maxCount) { // try 5 times

			sql = "SELECT COUNT(ID) FROM JobNote WHERE NoteNo='"+nextNo+"'";
			rs = getResultSet(sql);

              		//if (log.isDebugEnabled())log.debug("validat "+nextNo+" at k=" + k);
              		if (rs.next()) {
                		//log.warn("Duplicated NoteNo="+nextNo);
                		k++;
                		nextNo = "" + (idCount + k);
                		defStr = "00000";
                		idx = defStr.length() - nextNo.length();
                		if (idx < 0) throw new Exception("jobNoteID.length > 5");
                		nextNo = defStr.substring(0,idx) + nextNo;
                		nextNo = prefix + iy + nextNo;
                		//log.debug("Try NoteNo="+nextNo);
              		} else {
                		isValid = true;
              		}
            	}

	}catch (SQLException se) {
               if (log.isDebugEnabled()) log.error("SQLException: " + se.getMessage());
	}catch (Exception e) {
               if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
	}finally {
		rs = null;
	}


	return nextNo;
    }/* getNextNoteNo() */

    public String getID(String noteNo)
        throws SQLException {
		ResultSet rs = null;
		String sql = "SELECT ID FROM JobNote WHERE NoteNo='"+noteNo+"'";
		rs = getResultSet(sql);
		if(rs.first()) noteNo = rs.getString("ID");

	return noteNo;
    } /* getID(String) */
}
