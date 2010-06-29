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


import com.mooo.mycoz.action.ActionServlet;
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
import com.mooo.mycoz.dbobj.util.IDGenerator;
import com.mooo.mycoz.request.Input;
import com.mooo.mycoz.util.DBLoad;
import com.mooo.mycoz.util.DBMap;
import com.mooo.mycoz.util.DBNode;
import com.mooo.mycoz.util.MysqlConnection;
import com.mooo.mycoz.util.PigConfigNode;
import com.mooo.mycoz.util.PigLoad;
import com.mooo.mycoz.util.PigMap;
import com.mooo.mycoz.util.PigNode;

public class AccountingController {

private static Log log = LogFactory.getLog(AccountingController.class);
//list Receivable Payable all ( Credit + Debit + margin = 0 )
public void listStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String var = null;
		ResultSet rs = null;
		String sql = null;
		Input in = new Input();
		// list for this
		in.addValue(request,"JobID",request.getParameter("JobID"));
		in.addValue(request,"JobNoteID",request.getParameter("JobNoteID"));
		in.addValue(request,"Customer",request.getParameter("Customer"));

		in.addHashMapValues(request,"SearchType",IDGenerator.getReportTypes(),request.getParameter("SearchType"));

		var = request.getParameter("StartDate");
		if(var == null) var = IDGenerator.getLastMonthToday();
		in.addValue(request,"StartDate",var);

		var = request.getParameter("EndDate");
		if(var == null) var = IDGenerator.getToday();
		in.addValue(request,"EndDate",var);

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

			in.addValue(request,"ID"+i, rs.getString("jn.ID") );
			in.addValue(request,"NoteNo"+i, rs.getString("jn.NoteNo") );
			in.addValue(request,"Amount"+i, rs.getString("Amount")+"("+rs.getString("cy.ISOCode")+")" );
			in.addValue(request,"Account"+i, rs.getString("ac.Code") );
			in.addValue(request,"AccountID"+i, rs.getString("ac.ID") );
			in.addValue(request,"Date"+i, rs.getString("jn.Date") );
			in.addValue(request,"ChargeTo"+i, rs.getString("us.Name") );

			var = rs.getString("nt.Category");
 			if (var.equals("Debit"))
				in.addValue(request,"Command"+i, "Receipt" );
			else if (var.equals("Credit"))
				in.addValue(request,"Command"+i, "Payment" );
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
		Input in = new Input();
/*
		String[] SelectItem = request.getParameterValues("SelectItem");
if (log.isDebugEnabled()) log.debug("SelectItem = " + request.getParameter("SelectItem"));
if(SelectItem != null)
for (int i =0; i < SelectItem.length; i++) {
if (log.isDebugEnabled()) log.debug("SelectItem = " + SelectItem[i]);
  } */
		// list for this
		in.addValue(request,"JobNoteID",request.getParameter("JobNoteID"));
		in.addValue(request,"NoteNo",request.getParameter("NoteNo"));
if (log.isDebugEnabled()) log.debug("NoteNo: " + request.getParameter("NoteNo"));
		NoteType nt = new NoteType();
		in.addHashMapValues(request,"NoteType",nt.getValues(),request.getParameter("NoteType"));

		OperatorUser ou = new OperatorUser();
		in.addHashMapValues(request,"Operator",ou.getValues(),request.getParameter("Operator"));

		User us = new User();
		in.addHashMapValues(request,"ChargeTo",us.getValues(),request.getParameter("ChargeTo"));

		var = request.getParameter("Date");
		if(var == null) var = IDGenerator.getToday();
		in.addValue(request,"Date",var);

		var = request.getParameter("DueDate");
		if(var == null) var = IDGenerator.getToday();
		in.addValue(request,"DueDate",var);

		in.addValue(request,"Description",request.getParameter("Description"));

		//Add Item
		Account ac = new Account();
		if(request.getParameter("NoteType") != null) {
			var = request.getParameter("NoteType");
			in.addHashMapValues(request,"Account",ac.getValues(var),request.getParameter("Account"));
		} else 
			in.addHashMapValues(request,"Account",ac.getValues(),request.getParameter("Account"));

		var = request.getParameter("Account");
		if((var != null) && !var.equals("0")) {
			in.addValue(request,"Currency",ac.getCurrency(request.getParameter("Account")));
		   }

		in.addValue(request,"ItemName",request.getParameter("ItemName"));
		in.addValue(request,"ItemRate",request.getParameter("ItemRate"));
		in.addValue(request,"ItemUnit",request.getParameter("ItemUnit"));
		in.addValue(request,"ItemQuantity",request.getParameter("ItemQuantity"));

		in.addValue(request,"BillAmount",request.getParameter("BillAmount"));

		//List JobAccounting
		JobNote jn = new JobNote();

		JobAccounting ja = new JobAccounting();
		sql = "SELECT ja.ID,ja.JobNoteID,ja.ItemName,ja.ItemRate,ja.ItemUnit,ja.ItemQuantity,cy.ISOCode,ja.AccountID,ja.ItemRate*ja.ItemQuantity AS Amount,cy.Symbol,ja.CurrencyID FROM JobAccounting AS ja, mycozShared.Currency AS cy  WHERE ja.CurrencyID=cy.ID AND ja.JobNoteID='" + jn.getID(request.getParameter("NoteNo")) +"'";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		rs = ja.getResultSet(sql);
		int i = 0;
		while(rs.next()) {

			in.addValue(request,"ID"+i, rs.getString("ja.ID") );
			in.addValue(request,"AccountID"+i, rs.getString("ja.AccountID"));
			in.addValue(request,"Account"+i, ac.getCode(rs.getString("ja.AccountID")) );

			in.addValue(request,"ItemName"+i, rs.getString("ja.ItemName") );
			in.addValue(request,"ItemRate"+i, rs.getString("ja.ItemRate") );
			in.addValue(request,"ItemUnit"+i, rs.getString("ja.ItemUnit") );
			in.addValue(request,"ItemQuantity"+i, rs.getString("ja.ItemQuantity") );

			in.addValue(request,"CurrencyID"+i, rs.getString("ja.CurrencyID"));
			in.addValue(request,"Currency"+i, rs.getString("cy.ISOCode") );
			in.addValue(request,"BillAmount"+i, rs.getDouble("Amount") + rs.getString("cy.Symbol"));

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
		Input in = new Input();
		// list for this
		in.addValue(request,"JobNoteID",request.getParameter("JobNoteID"));
		in.addValue(request,"NoteNo",request.getParameter("NoteNo"));
if (log.isDebugEnabled()) log.debug("NoteNo: " + request.getParameter("NoteNo"));
		NoteType nt = new NoteType();
		in.addHashMapValues(request,"NoteType",nt.getValues(),request.getParameter("NoteType"));

		OperatorUser ou = new OperatorUser();
		in.addHashMapValues(request,"Operator",ou.getValues(),request.getParameter("Operator"));

		User us = new User();
		in.addHashMapValues(request,"ChargeTo",us.getValues(),request.getParameter("ChargeTo"));

		var = request.getParameter("Date");
		if(var == null) var = IDGenerator.getToday();
		in.addValue(request,"Date",var);

		var = request.getParameter("DueDate");
		if(var == null) var = IDGenerator.getToday();
		in.addValue(request,"DueDate",var);

		in.addValue(request,"Description",request.getParameter("Description"));

		//Add Item
		Account at = new Account();
		in.addHashMapValues(request,"Account",at.getValues(),request.getParameter("Account"));

		in.addValue(request,"ItemName",request.getParameter("ItemName"));
		in.addValue(request,"ItemRate",request.getParameter("ItemRate"));
		in.addValue(request,"ItemUnit",request.getParameter("ItemUnit"));
		in.addValue(request,"ItemQuantity",request.getParameter("ItemQuantity"));

		Currency cy = new Currency();
		in.addHashMapValues(request,"Currency",cy.getValues(),request.getParameter("Currency"));

		in.addValue(request,"BillAmount",request.getParameter("BillAmount"));

		//List JobAccounting
		JobNote jn = new JobNote();

		JobAccounting ja = new JobAccounting();
		sql = "SELECT ja.ID,ja.JobNoteID,ja.ItemName,ja.ItemRate,ja.ItemUnit,ja.ItemQuantity,cy.ISOCode,ja.AccountID,ja.ItemRate*ja.ItemQuantity AS Amount,cy.Symbol FROM JobAccounting AS ja, mycozShared.Currency AS cy  WHERE ja.CurrencyID=cy.ID AND ja.JobNoteID='" + jn.getID(request.getParameter("NoteNo")) +"'";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		rs = ja.getResultSet(sql);
		int i = 0;
		while(rs.next()) {

			in.addValue(request,"ID"+i, rs.getString("ja.ID") );
			in.addValue(request,"Account"+i, at.getCode(rs.getString("ja.AccountID")) );

			in.addValue(request,"ItemName"+i, rs.getString("ja.ItemName") );
			in.addValue(request,"ItemRate"+i, rs.getString("ja.ItemRate") );
			in.addValue(request,"ItemUnit"+i, rs.getString("ja.ItemUnit") );
			in.addValue(request,"ItemQuantity"+i, rs.getString("ja.ItemQuantity") );

			in.addValue(request,"Currency"+i, rs.getString("cy.ISOCode") );
			in.addValue(request,"BillAmount"+i, rs.getDouble("Amount") + rs.getString("cy.Symbol"));
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
	Input in = new Input();

	noteNo = request.getParameter("NoteNo");
	in.addValue(request,"NoteNo",noteNo);
if (log.isDebugEnabled()) log.debug("NoteNo: " + noteNo);
	in.addValue(request,"JobNoteID",request.getParameter("JobNoteID"));

	String accountID = request.getParameter("AccountID");
	in.addValue(request,"AccountID",request.getParameter("AccountID"));

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
		in.addValue(request,"NoteType",rs.getString("nt.Name"));
		in.addValue(request,"Operator",rs.getString("ou.UserName"));
		in.addValue(request,"ChargeTo",rs.getString("us.Name"));
		in.addValue(request,"Date",rs.getString("jn.Date"));
		in.addValue(request,"DueDate",rs.getString("jn.DueDate"));
		in.addValue(request,"Description",rs.getString("jn.Description"));
		in.addValue(request,"Account", at.getCode(rs.getString("ja.AccountID")) );
		in.addValue(request,"Amount", rs.getDouble("Balance") + rs.getString("cy.Symbol"));
		in.addValue(request,"Balance", rs.getString("Balance"));
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
	in.addValue(request,"NoteNo",noteNo);
if (log.isDebugEnabled()) log.debug("NoteNo: " + noteNo);
	// 
	in.addValue(request,"JobNoteID",request.getParameter("JobNoteID"));

	JobNote jn = new JobNote();
	sql = "SELECT jn.ID,jn.NoteNo,jn.Date,jn.DueDate,nt.Name, us.UserName,jn.Description FROM JobNote AS jn,mycozShared.NoteType AS nt,User AS us WHERE jn.NoteTypeID=nt.ID AND jn.ChargeToID=us.ID";

	rs = jn.getResultSet(sql);
	if(rs.first()) {
		in.addValue(request,"NoteType",rs.getString("nt.Name"));
		//in.addValue(request,"Operator",rs.getString("ja.ItemName"));
		in.addValue(request,"ChargeTo",rs.getString("us.UserName"));
		in.addValue(request,"Date",rs.getString("jn.Date"));
		in.addValue(request,"DueDate",rs.getString("jn.DueDate"));
		in.addValue(request,"Description",rs.getString("jn.Description"));
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

		in.addValue(request,"ID"+i, rs.getString("ja.ID") );
		in.addValue(request,"Account"+i, at.getCode(rs.getString("ja.AccountID")) );

		in.addValue(request,"ItemName"+i, rs.getString("ja.ItemName") );
		in.addValue(request,"ItemRate"+i, rs.getString("ja.ItemRate") );
		in.addValue(request,"ItemUnit"+i, rs.getString("ja.ItemUnit") );
		in.addValue(request,"ItemQuantity"+i, rs.getString("ja.ItemQuantity") );

		in.addValue(request,"CurrencyID"+i, rs.getString("ja.CurrencyID"));
		in.addValue(request,"Currency"+i, rs.getString("cy.ISOCode") );
		in.addValue(request,"BillAmount"+i, rs.getDouble("Amount") + rs.getString("cy.Symbol"));

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
		in.addValue(request,"Total"+i, value + cy.getSymbol(key) );
		in.addValue(request,"ISOCode"+i, "Total:"+cy.getISOCode(key) );
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
	in.addValue(request,"NoteNo",noteNo);
if (log.isDebugEnabled()) log.debug("NoteNo: " + noteNo);
	// 
	in.addValue(request,"JobNoteID",request.getParameter("JobNoteID"));

	JobNote jn = new JobNote();
	sql = "SELECT jn.ID,jn.NoteNo,jn.Date,jn.DueDate,nt.Name, us.UserName,jn.Description FROM JobNote AS jn,mycozShared.NoteType AS nt,User AS us WHERE jn.NoteTypeID=nt.ID AND jn.ChargeToID=us.ID";

	rs = jn.getResultSet(sql);
	if(rs.first()) {
		in.addValue(request,"NoteType",rs.getString("nt.Name"));
		//in.addValue(request,"Operator",rs.getString("ja.ItemName"));
		in.addValue(request,"ChargeTo",rs.getString("us.UserName"));
		in.addValue(request,"Date",rs.getString("jn.Date"));
		in.addValue(request,"DueDate",rs.getString("jn.DueDate"));
		in.addValue(request,"Description",rs.getString("jn.Description"));
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

		in.addValue(request,"ID"+i, rs.getString("ja.ID") );
		in.addValue(request,"Account"+i, at.getCode(rs.getString("ja.AccountID")) );

		in.addValue(request,"ItemName"+i, rs.getString("ja.ItemName") );
		in.addValue(request,"ItemRate"+i, rs.getString("ja.ItemRate") );
		in.addValue(request,"ItemUnit"+i, rs.getString("ja.ItemUnit") );
		in.addValue(request,"ItemQuantity"+i, rs.getString("ja.ItemQuantity") );

		in.addValue(request,"CurrencyID"+i, rs.getString("ja.CurrencyID"));
		in.addValue(request,"Currency"+i, rs.getString("cy.ISOCode") );
		in.addValue(request,"BillAmount"+i, rs.getDouble("Amount") + rs.getString("cy.Symbol"));

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
		in.addValue(request,"Total"+i, value + cy.getSymbol(key) );
		in.addValue(request,"ISOCode"+i, "Total:"+cy.getISOCode(key) );
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
