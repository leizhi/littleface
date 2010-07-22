package com.mooo.mycoz.controller.service;

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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.text.DecimalFormat;

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


import com.mooo.mycoz.dbobj.mycozBranch.JobAccounting;
import com.mooo.mycoz.dbobj.mycozBranch.JobJournal;
import com.mooo.mycoz.dbobj.mycozBranch.JobNote;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozShared.Account;
import com.mooo.mycoz.dbobj.mycozShared.Country;
import com.mooo.mycoz.dbobj.mycozShared.Currency;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.dbobj.mycozShared.NoteType;
import com.mooo.mycoz.dbobj.mycozShared.OperatorUser;
import com.mooo.mycoz.jdbc.DBLoad;
import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;
import com.mooo.mycoz.jdbc.MysqlConnection;
import com.mooo.mycoz.util.ActionServlet;
import com.mooo.mycoz.util.IDGenerator;


import com.mooo.mycoz.util.SAXParserConf;
import com.mooo.mycoz.util.ActionMap;


public class AccountingController {

private static Log log = LogFactory.getLog(AccountingController.class);
//list Receivable Payable all ( Credit + Debit + margin = 0 )
public void listStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String var = null;
		ResultSet rs = null;
		String sql = null;
		
		// list for this
		request.setAttribute("JobID",request.getParameter("JobID"));
		request.setAttribute("JobNoteID",request.getParameter("JobNoteID"));
		request.setAttribute("Customer",request.getParameter("Customer"));

		request.setAttribute("SearchType",IDGenerator.getReportTypes());

		var = request.getParameter("StartDate");
		if(var == null) var = IDGenerator.getLastMonthToday();
		request.setAttribute("StartDate",var);

		var = request.getParameter("EndDate");
		if(var == null) var = IDGenerator.getToday();
		request.setAttribute("EndDate",var);

		JobAccounting ja = new JobAccounting();

		sql = "SELECT jn.ID,jn.NoteNo,jn.Date,jn.DueDate,us.Name,SUM(ja.ItemRate*ja.ItemQuantity) AS Amount, cy.ISOCode,ja.AccountID,ac.Code,ac.ID,nt.Category FROM JobNote AS jn, JobAccounting AS ja,User AS us, mycozShared.Currency AS cy, mycozShared.Account AS ac,mycozShared.AccountGroup AS ag,mycozShared.AccountType AS at,mycozShared.NoteType AS nt WHERE ja.CurrencyID=cy.ID AND ja.JobNoteID=jn.ID AND jn.ChargeToID=us.ID AND ja.AccountID=ac.ID AND ac.GroupID=ag.ID AND ag.TypeID=at.ID AND at.NoteTypeID=nt.ID";

		var = request.getParameter("NoteNo");
		if(var != null && !var.equals(""))
			sql += " AND jn.NoteNo LIKE '%" + var + "%'";

		var = request.getParameter("SearchType");
		if(var != null && var.equals("Debit")) sql += " AND nt.Category='Debit' AND jn.IsPosted!='Y'";
		else if (var != null && var.equals("Credit")) sql += " AND nt.Category='Credit' AND jn.IsPosted!='Y'";
		else if (var != null && var.equals("Receipt")) sql += " AND nt.Category='Debit' AND jn.IsPosted='Y'";
		else if (var != null && var.equals("Defrayment")) sql += " AND nt.Category='Credit' AND jn.IsPosted='Y'";

		sql += " GROUP BY ac.ID";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		rs = ja.getResultSet(sql);
		int i = 0;
		while(rs.next()) {

			request.setAttribute("ID"+i, rs.getString("jn.ID") );
			request.setAttribute("NoteNo"+i, rs.getString("jn.NoteNo") );
			request.setAttribute("Amount"+i, rs.getString("Amount")+"("+rs.getString("cy.ISOCode")+")" );
			request.setAttribute("Account"+i, rs.getString("ac.Code") );
			request.setAttribute("AccountID"+i, rs.getString("ac.ID") );
			request.setAttribute("Date"+i, rs.getString("jn.Date") );
			request.setAttribute("ChargeTo"+i, rs.getString("us.Name") );

			var = rs.getString("nt.Category");
 			if (var.equals("Debit"))
				request.setAttribute("Command"+i, "Receipt" );
			else if (var.equals("Credit"))
				request.setAttribute("Command"+i, "Payment" );
			i++; 
	   	} /* End JobAccounting */

		in.addIterateValue(request,"List",i+"");

if (log.isDebugEnabled()) log.debug("i: " + i);
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
  }

public void promptNewNoteStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String var = "";
		String sql = "";
		ResultSet rs = null;
		
/*
		String[] SelectItem = request.getParameterValues("SelectItem");
if (log.isDebugEnabled()) log.debug("SelectItem = " + request.getParameter("SelectItem"));
if(SelectItem != null)
for (int i =0; i < SelectItem.length; i++) {
if (log.isDebugEnabled()) log.debug("SelectItem = " + SelectItem[i]);
  } */
		// list for this
		request.setAttribute("JobNoteID");
		request.setAttribute("NoteNo");
if (log.isDebugEnabled()) log.debug("NoteNo: " + request.getParameter("NoteNo"));
		NoteType nt = new NoteType();
		request.setAttribute("NoteType",nt.getValues());

		OperatorUser ou = new OperatorUser();
		request.setAttribute("Operator",ou.getValues());

		User us = new User();
		request.setAttribute("ChargeTo",us.getValues());

		var = request.getParameter("Date");
		if(var == null) var = IDGenerator.getToday();
		request.setAttribute("Date",var);

		var = request.getParameter("DueDate");
		if(var == null) var = IDGenerator.getToday();
		request.setAttribute("DueDate",var);

		request.setAttribute("Description");

		//Add Item
		Account ac = new Account();
		if(request.getParameter("NoteType") != null) {
			var = request.getParameter("NoteType");
			request.setAttribute("Account",ac.getValues(var));
		} else 
			request.setAttribute("Account",ac.getValues());

		var = request.getParameter("Account");
		if((var != null) && !var.equals("0")) {
			request.setAttribute("Currency",ac.getCurrency(request.getParameter("Account")));
		   }

		request.setAttribute("ItemName");
		request.setAttribute("ItemRate");
		request.setAttribute("ItemUnit");
		request.setAttribute("ItemQuantity");

		request.setAttribute("BillAmount");

		//List JobAccounting
		JobNote jn = new JobNote();

		JobAccounting ja = new JobAccounting();
		sql = "SELECT ja.ID,ja.JobNoteID,ja.ItemName,ja.ItemRate,ja.ItemUnit,ja.ItemQuantity,cy.ISOCode,ja.AccountID,ja.ItemRate*ja.ItemQuantity AS Amount,cy.Symbol,ja.CurrencyID FROM JobAccounting AS ja, mycozShared.Currency AS cy  WHERE ja.CurrencyID=cy.ID AND ja.JobNoteID='" + jn.getID(request.getParameter("NoteNo")) +"'";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		rs = ja.getResultSet(sql);
		int i = 0;
		while(rs.next()) {

			request.setAttribute("ID"+i, rs.getString("ja.ID") );
			request.setAttribute("AccountID"+i, rs.getString("ja.AccountID"));
			request.setAttribute("Account"+i, ac.getCode(rs.getString("ja.AccountID")) );

			request.setAttribute("ItemName"+i, rs.getString("ja.ItemName") );
			request.setAttribute("ItemRate"+i, rs.getString("ja.ItemRate") );
			request.setAttribute("ItemUnit"+i, rs.getString("ja.ItemUnit") );
			request.setAttribute("ItemQuantity"+i, rs.getString("ja.ItemQuantity") );

			request.setAttribute("CurrencyID"+i, rs.getString("ja.CurrencyID"));
			request.setAttribute("Currency"+i, rs.getString("cy.ISOCode") );
			request.setAttribute("BillAmount"+i, rs.getDouble("Amount") + rs.getString("cy.Symbol"));

			i++;
               	}

		in.addIterateValue(request,"List",i+"");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
     		}
  }

public void processNewNoteStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	int id=0;
	String noteNo = null;
	String sql = null;

	noteNo = request.getParameter("NoteNo");
if (log.isDebugEnabled()) log.debug("NoteNo: " + noteNo);
	Currency cy = new Currency();
	JobNote jn = new JobNote();

	if(noteNo == null || noteNo.equals("")) {

		id = jn.getNextID();

		sql = "INSERT INTO JobNote(ID,NoteNo,NoteTypeID,OperatorID,ChargeToID,IsPosted,PostDate,PrintCount,Date,DueDate,Description) VALUES(";
		sql += id+",";
		noteNo = jn.getNextNoteNo(request.getParameter("NoteType"));

		sql += "'"+noteNo+"',";

		sql += request.getParameter("NoteType")+",";
		sql += request.getParameter("Operator")+",";
		sql += request.getParameter("ChargeTo")+",";

		sql += "'N',";
		sql += "NULL,";
		sql += "0,";
		sql += "'"+request.getParameter("Date")+"',";
		sql += "'"+request.getParameter("DueDate")+"',";
		sql += "'"+request.getParameter("Description")+"')";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		jn.execute(sql);
	} 

		JobAccounting ja = new JobAccounting();
		sql = "INSERT INTO JobAccounting(ID,JobNoteID,ItemName,ItemRate,ItemUnit,ItemQuantity,CurrencyID,AccountID) VALUES(";
		id = ja.getNextID();
		sql += id+",";
		sql += "'"+jn.getID(noteNo)+"',";
		sql += "'"+request.getParameter("ItemName")+"',";
		sql += request.getParameter("ItemRate")+",";
		sql += "'"+request.getParameter("ItemUnit")+"',";
		sql += request.getParameter("ItemQuantity")+",";

		sql += cy.getID(request.getParameter("Currency"))+",";

		Account at = new Account();
		sql += request.getParameter("Account")+")";

		if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		ja.execute(sql);

	response.sendRedirect(request.getContextPath()+"/Accounting.do?state=promptNewNote&NoteNo="+noteNo+"&NoteType="+request.getParameter("NoteType")+"&Operator="+request.getParameter("Operator")+"&ChargeTo="+request.getParameter("ChargeTo")+"&Account="+request.getParameter("Account")+"&Currency="+request.getParameter("Currency"));
     } catch (Exception e) {
      	if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
	response.sendRedirect(request.getContextPath()+"/Accounting.do?state=promptNewNote");
    }
  }

public void promptUpdateNoteStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String var = "";
		String sql = "";
		ResultSet rs = null;
		
		// list for this
		request.setAttribute("JobNoteID");
		request.setAttribute("NoteNo");
if (log.isDebugEnabled()) log.debug("NoteNo: " + request.getParameter("NoteNo"));
		NoteType nt = new NoteType();
		request.setAttribute("NoteType",nt.getValues());

		OperatorUser ou = new OperatorUser();
		request.setAttribute("Operator",ou.getValues());

		User us = new User();
		request.setAttribute("ChargeTo",us.getValues());

		var = request.getParameter("Date");
		if(var == null) var = IDGenerator.getToday();
		request.setAttribute("Date",var);

		var = request.getParameter("DueDate");
		if(var == null) var = IDGenerator.getToday();
		request.setAttribute("DueDate",var);

		request.setAttribute("Description");

		//Add Item
		Account at = new Account();
		request.setAttribute("Account",at.getValues());

		request.setAttribute("ItemName");
		request.setAttribute("ItemRate");
		request.setAttribute("ItemUnit");
		request.setAttribute("ItemQuantity");

		Currency cy = new Currency();
		request.setAttribute("Currency",cy.getValues());

		request.setAttribute("BillAmount");

		//List JobAccounting
		JobNote jn = new JobNote();

		JobAccounting ja = new JobAccounting();
		sql = "SELECT ja.ID,ja.JobNoteID,ja.ItemName,ja.ItemRate,ja.ItemUnit,ja.ItemQuantity,cy.ISOCode,ja.AccountID,ja.ItemRate*ja.ItemQuantity AS Amount,cy.Symbol FROM JobAccounting AS ja, mycozShared.Currency AS cy  WHERE ja.CurrencyID=cy.ID AND ja.JobNoteID='" + jn.getID(request.getParameter("NoteNo")) +"'";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		rs = ja.getResultSet(sql);
		int i = 0;
		while(rs.next()) {

			request.setAttribute("ID"+i, rs.getString("ja.ID") );
			request.setAttribute("Account"+i, at.getCode(rs.getString("ja.AccountID")) );

			request.setAttribute("ItemName"+i, rs.getString("ja.ItemName") );
			request.setAttribute("ItemRate"+i, rs.getString("ja.ItemRate") );
			request.setAttribute("ItemUnit"+i, rs.getString("ja.ItemUnit") );
			request.setAttribute("ItemQuantity"+i, rs.getString("ja.ItemQuantity") );

			request.setAttribute("Currency"+i, rs.getString("cy.ISOCode") );
			request.setAttribute("BillAmount"+i, rs.getDouble("Amount") + rs.getString("cy.Symbol"));
if (log.isDebugEnabled()) log.debug("getAmount=" + rs.getDouble("Amount"));
			i++;
               	}

		in.addIterateValue(request,"List",i+"");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
     		}
  }

public void processUpdateNoteStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	int id=0;
	String noteNo = null;
	String sql = null;

	noteNo = request.getParameter("NoteNo");
if (log.isDebugEnabled()) log.debug("NoteNo: " + noteNo);

	JobNote jn = new JobNote();

	if(noteNo == null || noteNo.equals("")) {

		id = jn.getNextID();

		sql = "INSERT INTO JobNote(ID,NoteNo,NoteTypeID,OperatorID,ChargeToID,IsPosted,PostDate,PrintCount,Date,DueDate,Description) VALUES(";
		sql += id+",";
		noteNo = jn.getNextNoteNo(request.getParameter("NoteType"));

		sql += "'"+noteNo+"',";

		sql += request.getParameter("NoteType")+",";
		sql += request.getParameter("Operator")+",";
		sql += request.getParameter("ChargeTo")+",";

		sql += "'N',";
		sql += "NULL,";
		sql += "0,";
		sql += "'"+request.getParameter("Date")+"',";
		sql += "'"+request.getParameter("DueDate")+"',";
		sql += "'"+request.getParameter("Description")+"')";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		jn.execute(sql);
	} 

		JobAccounting ja = new JobAccounting();
		sql = "INSERT INTO JobAccounting(ID,JobNoteID,ItemName,ItemRate,ItemUnit,ItemQuantity,CurrencyID,AccountID) VALUES(";
		id = ja.getNextID();
		sql += id+",";
		sql += "'"+jn.getID(noteNo)+"',";
		sql += "'"+request.getParameter("ItemName")+"',";
		sql += request.getParameter("ItemRate")+",";
		sql += "'"+request.getParameter("ItemUnit")+"',";
		sql += request.getParameter("ItemQuantity")+",";

		sql += request.getParameter("Currency")+",";

		Account at = new Account();
		sql += request.getParameter("Account")+")";

		if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		ja.execute(sql);

	response.sendRedirect(request.getContextPath()+"/Accounting.do?state=promptNewNote&NoteNo="+noteNo+"&NoteType="+request.getParameter("NoteType")+"&Operator="+request.getParameter("Operator")+"&ChargeTo="+request.getParameter("ChargeTo")+"&Account="+request.getParameter("Account")+"&Currency="+request.getParameter("Currency"));
     } catch (Exception e) {
      	if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
	response.sendRedirect(request.getContextPath()+"/Accounting.do?state=promptNewNote");
    }
  }

public void promptReceiptStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

try {
	String noteNo = null;
	String sql = null;
	ResultSet rs = null;
	

	noteNo = request.getParameter("NoteNo");
	request.setAttribute("NoteNo",noteNo);
if (log.isDebugEnabled()) log.debug("NoteNo: " + noteNo);
	request.setAttribute("JobNoteID");

	String accountID = request.getParameter("AccountID");
	request.setAttribute("AccountID");

	/* Receipt */
	Account at = new Account();
	JobNote jn = new JobNote();
	sql = "SELECT jn.ID,jn.NoteNo,jn.Date,jn.DueDate,jn.Description,us.Name,ou.UserName,SUM(ja.ItemRate*ja.ItemQuantity) AS Balance, cy.Symbol,cy.ISOCode,ja.AccountID,at.Code,nt.Name FROM JobNote AS jn, JobAccounting AS ja,User AS us,mycozShared.OperatorUser AS ou, mycozShared.Currency AS cy, mycozShared.Account AS ac,mycozShared.AccountGroup AS ag,mycozShared.AccountType AS at,mycozShared.NoteType AS nt WHERE ja.CurrencyID=cy.ID AND ja.JobNoteID=jn.ID AND jn.ChargeToID=us.ID AND ja.AccountID=ac.ID AND ac.GroupID=ag.ID AND ag.TypeID=at.ID AND at.NoteTypeID=nt.ID AND jn.OperatorID=ou.ID";

	sql += " AND ja.JobNoteID='" + jn.getID(noteNo) +"'";
	sql += " AND ac.ID=" + accountID;

	sql += " GROUP BY ac.ID";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
	rs = jn.getResultSet(sql);
	if(rs.first()) {
		request.setAttribute("NoteType",rs.getString("nt.Name"));
		request.setAttribute("Operator",rs.getString("ou.UserName"));
		request.setAttribute("ChargeTo",rs.getString("us.Name"));
		request.setAttribute("Date",rs.getString("jn.Date"));
		request.setAttribute("DueDate",rs.getString("jn.DueDate"));
		request.setAttribute("Description",rs.getString("jn.Description"));
		request.setAttribute("Account", at.getCode(rs.getString("ja.AccountID")) );
		request.setAttribute("Amount", rs.getDouble("Balance") + rs.getString("cy.Symbol"));
		request.setAttribute("Balance", rs.getString("Balance"));
	} // end Receipt

     } catch (Exception e) {
      	if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
    }
  }

public void processReceiptStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	int id=0;
	String noteNo = null;
	String sql = null;
	ResultSet rs = null;

	noteNo = request.getParameter("NoteNo");
if (log.isDebugEnabled()) log.debug("NoteNo: " + noteNo);

	String accountID = null;
	String jobNoteID = null;
	String dcType = null;
	String amount = null;
	String currencyID = null;
	String jobAccountingID = null;
	//String exchangeRate = null;

	JobNote jn = new JobNote();
	sql = "SELECT ja.AccountID,ja.JobNoteID,nt.Category,ja.CurrencyID,ja.ID,ja.ItemRate*ja.ItemQuantity AS Amount FROM JobNote AS jn,JobAccounting AS ja,mycozShared.NoteType AS nt WHERE jn.ID=ja.JobNoteID AND jn.NoteTypeID=nt.ID";
	sql += " AND NoteNo='" +noteNo+"'";
	//sql += " ORDER BY ja.ID DESC" ;
	sql += " LIMIT 1" ;
if (log.isDebugEnabled()) log.debug("SQL: " + sql);
	rs = jn.getResultSet(sql);
	if(rs.next()) {
		accountID = rs.getString("ja.AccountID");
		jobNoteID = rs.getString("ja.JobNoteID");
		dcType = rs.getString("nt.Category");
		amount = rs.getString("Amount");
		currencyID = rs.getString("ja.CurrencyID");
		jobAccountingID = rs.getString("ja.ID");
		}

	/*******************
	********************/

	JobJournal jj = new JobJournal();

	id = jj.getNextID();

	sql = "INSERT INTO JobJournal(ID,AccountID,JobNoteID,DCType,Amount,OrgCurrencyID,OrgRate,JobAccountingID,ExchangeRate) VALUES(";
	sql += id+",";
	sql += accountID+",";
	sql += "'"+dcType+"',";
	sql += amount+",";
	sql += currencyID+",";
	sql += "1,";
	sql += jobAccountingID+",";
	sql += "1 )";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
	jj.execute(sql);
     } catch (Exception e) {
      	if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
	response.sendRedirect(request.getContextPath()+"/Accounting.do");
    }
  }

public void promptPaymentStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	int id=0;
	String noteNo = null;
	String sql = null;
/*
	int id=0;
	String key = null;
	String value = null;
	String noteNo = null;
	String sql = null;
	ResultSet rs = null;

	noteNo = request.getParameter("NoteNo");
	request.setAttribute("NoteNo",noteNo);
if (log.isDebugEnabled()) log.debug("NoteNo: " + noteNo);
	// 
	request.setAttribute("JobNoteID");

	JobNote jn = new JobNote();
	sql = "SELECT jn.ID,jn.NoteNo,jn.Date,jn.DueDate,nt.Name, us.UserName,jn.Description FROM JobNote AS jn,mycozShared.NoteType AS nt,User AS us WHERE jn.NoteTypeID=nt.ID AND jn.ChargeToID=us.ID";

	rs = jn.getResultSet(sql);
	if(rs.first()) {
		request.setAttribute("NoteType",rs.getString("nt.Name"));
		//request.setAttribute("Operator",rs.getString("ja.ItemName"));
		request.setAttribute("ChargeTo",rs.getString("us.UserName"));
		request.setAttribute("Date",rs.getString("jn.Date"));
		request.setAttribute("DueDate",rs.getString("jn.DueDate"));
		request.setAttribute("Description",rs.getString("jn.Description"));
	}

	// List Receipt
	double subTotal = 0.0;
	Hashtable total = new Hashtable();

	Currency cy = new Currency();
	Account at = new Account();
	JobAccounting ja = new JobAccounting();
	ResultSet cyResult = null;
	sql = "SELECT ID FROM Currency WHERE ID > 0";
	cyResult = cy.getResultSet(sql);
	int i = 0;
	while (cyResult.next()) {
	sql = "SELECT ja.ID,ja.JobNoteID,ja.ItemName,ja.ItemRate,ja.ItemUnit,ja.ItemQuantity,cy.ISOCode,ja.AccountID,ja.ItemRate*ja.ItemQuantity AS Amount,cy.Symbol,ja.CurrencyID FROM JobAccounting AS ja, mycozShared.Currency AS cy  WHERE ja.CurrencyID=cy.ID AND ja.JobNoteID='" + jn.getID(noteNo) +"'";
	sql += " AND ja.CurrencyID="+cyResult.getInt("ID");

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
	rs = ja.getResultSet(sql);
	while(rs.next()) {

		request.setAttribute("ID"+i, rs.getString("ja.ID") );
		request.setAttribute("Account"+i, at.getCode(rs.getString("ja.AccountID")) );

		request.setAttribute("ItemName"+i, rs.getString("ja.ItemName") );
		request.setAttribute("ItemRate"+i, rs.getString("ja.ItemRate") );
		request.setAttribute("ItemUnit"+i, rs.getString("ja.ItemUnit") );
		request.setAttribute("ItemQuantity"+i, rs.getString("ja.ItemQuantity") );

		request.setAttribute("CurrencyID"+i, rs.getString("ja.CurrencyID"));
		request.setAttribute("Currency"+i, rs.getString("cy.ISOCode") );
		request.setAttribute("BillAmount"+i, rs.getDouble("Amount") + rs.getString("cy.Symbol"));

		subTotal += rs.getDouble("Amount");
if (log.isDebugEnabled()) log.debug("subTotal: " + subTotal);
		i++;
	    } // end JobAccounting
		DecimalFormat df = new DecimalFormat("###0.0000");
		if(subTotal > 0.0)
		total.put(cyResult.getString("ID"), df.format(subTotal));

		subTotal = 0.0;
	} // end Currency

	in.addIterateValue(request,"List",i+"");

	i = 0;
	Enumeration e = total.keys();
	while(e.hasMoreElements()){  

		key = (String)e.nextElement();
		value = (String)total.get(key);
		request.setAttribute("Total"+i, value + cy.getSymbol(key) );
		request.setAttribute("ISOCode"+i, "Total:"+cy.getISOCode(key) );
	if (log.isDebugEnabled()) log.debug("value="+value);
		i++;
  	}
if (log.isDebugEnabled()) log.debug("ok");
	in.addIterateValue(request,"Total",i+"");
*/

     } catch (Exception e) {
      	if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
    }
  }

public void processPaymentStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	int id=0;
	String noteNo = null;
	String sql = null;

/*
	int id=0;
	String key = null;
	String value = null;
	String noteNo = null;
	String sql = null;
	ResultSet rs = null;

	noteNo = request.getParameter("NoteNo");
	request.setAttribute("NoteNo",noteNo);
if (log.isDebugEnabled()) log.debug("NoteNo: " + noteNo);
	// 
	request.setAttribute("JobNoteID");

	JobNote jn = new JobNote();
	sql = "SELECT jn.ID,jn.NoteNo,jn.Date,jn.DueDate,nt.Name, us.UserName,jn.Description FROM JobNote AS jn,mycozShared.NoteType AS nt,User AS us WHERE jn.NoteTypeID=nt.ID AND jn.ChargeToID=us.ID";

	rs = jn.getResultSet(sql);
	if(rs.first()) {
		request.setAttribute("NoteType",rs.getString("nt.Name"));
		//request.setAttribute("Operator",rs.getString("ja.ItemName"));
		request.setAttribute("ChargeTo",rs.getString("us.UserName"));
		request.setAttribute("Date",rs.getString("jn.Date"));
		request.setAttribute("DueDate",rs.getString("jn.DueDate"));
		request.setAttribute("Description",rs.getString("jn.Description"));
	}

	// List Receipt
	double subTotal = 0.0;
	Hashtable total = new Hashtable();

	Currency cy = new Currency();
	Account at = new Account();
	JobAccounting ja = new JobAccounting();
	ResultSet cyResult = null;
	sql = "SELECT ID FROM Currency WHERE ID > 0";
	cyResult = cy.getResultSet(sql);
	int i = 0;
	while (cyResult.next()) {
	sql = "SELECT ja.ID,ja.JobNoteID,ja.ItemName,ja.ItemRate,ja.ItemUnit,ja.ItemQuantity,cy.ISOCode,ja.AccountID,ja.ItemRate*ja.ItemQuantity AS Amount,cy.Symbol,ja.CurrencyID FROM JobAccounting AS ja, mycozShared.Currency AS cy  WHERE ja.CurrencyID=cy.ID AND ja.JobNoteID='" + jn.getID(noteNo) +"'";
	sql += " AND ja.CurrencyID="+cyResult.getInt("ID");

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
	rs = ja.getResultSet(sql);
	while(rs.next()) {

		request.setAttribute("ID"+i, rs.getString("ja.ID") );
		request.setAttribute("Account"+i, at.getCode(rs.getString("ja.AccountID")) );

		request.setAttribute("ItemName"+i, rs.getString("ja.ItemName") );
		request.setAttribute("ItemRate"+i, rs.getString("ja.ItemRate") );
		request.setAttribute("ItemUnit"+i, rs.getString("ja.ItemUnit") );
		request.setAttribute("ItemQuantity"+i, rs.getString("ja.ItemQuantity") );

		request.setAttribute("CurrencyID"+i, rs.getString("ja.CurrencyID"));
		request.setAttribute("Currency"+i, rs.getString("cy.ISOCode") );
		request.setAttribute("BillAmount"+i, rs.getDouble("Amount") + rs.getString("cy.Symbol"));

		subTotal += rs.getDouble("Amount");
if (log.isDebugEnabled()) log.debug("subTotal: " + subTotal);
		i++;
	    } // end JobAccounting
		DecimalFormat df = new DecimalFormat("###0.0000");
		if(subTotal > 0.0)
		total.put(cyResult.getString("ID"), df.format(subTotal));

		subTotal = 0.0;
	} // end Currency

	in.addIterateValue(request,"List",i+"");

	i = 0;
	Enumeration e = total.keys();
	while(e.hasMoreElements()){  

		key = (String)e.nextElement();
		value = (String)total.get(key);
		request.setAttribute("Total"+i, value + cy.getSymbol(key) );
		request.setAttribute("ISOCode"+i, "Total:"+cy.getISOCode(key) );
	if (log.isDebugEnabled()) log.debug("value="+value);
		i++;
  	}
if (log.isDebugEnabled()) log.debug("ok");
	in.addIterateValue(request,"Total",i+"");
*/

     } catch (Exception e) {
      	if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
    }
  }

}
