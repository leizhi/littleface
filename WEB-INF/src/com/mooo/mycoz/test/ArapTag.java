package com.mooo.mycoz.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mooo.mycoz.db.pool.*;
import com.mooo.mycoz.db.sql.*;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class ArapTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Log log = LogFactory.getLog(ArapTag.class);

	public int doStartTag() throws JspTagException {
		Connection con = null;
		Statement stmt = null;
		try {

			//DbobjMultiSql ms = MultiSQLFactory.getInstance();
			
			DbobjMultiSql ms = new MultiDBObject();

			ms.addTable("JobNote", "jn");
			ms.addTable("Customer", "ct");
			ms.setForeignKey("jn", "CustomerID", "ct", "ID");
			ms.setForeignKey("jn", "BranchID", "ct", "BranchID");

			ms.addTable("LocalPartner", "lp");
			ms.setForeignKey("jn", "LocalPartnerID", "lp", "ID");
			ms.setForeignKey("jn", "BranchID", "lp", "BranchID");
			ms.addTable("xpcShared.Partner", "p");
			ms.addTable("xpcShared.PartnerType", "pt");
			ms.setForeignKey("jn", "PartnerID", "p", "ID");
			ms.setForeignKey("p", "PartnerTypeID", "pt", "ID");

			ms.addTable("xpcShared.Account", "ac");
			ms.addTable("xpcShared.Currency", "cu");
			ms.setForeignKey("ac", "CurrencyID", "cu", "ID");

			ms.addTable("xpcShared.AccountGroup", "ag");
			ms.setForeignKey("ac", "GroupID", "ag", "ID");
			ms.addTable("xpcShared.AccountType", "at");
			ms.setForeignKey("ag", "TypeID", "at", "ID");

			ms.addTable("JobJournal", "jj");
			ms.setForeignKey("jj", "AccountID", "ac", "ID");
			ms.setForeignKey("jn", "ID", "jj", "JobNoteID");

			ms.setNotEqual("jn", "IsPosted", "0");

			ms.addCustomWhereClause("(at.TypeName='Account Receivable' OR at.TypeName='Account Payable' )");

			// ms.setLike("ct", "ShortName", "SE");
			// ms.setGreaterEqual("jn", "Date", "2008-08-01");
			// ms.setLessEqual("jn", "Date", "2008-09-01");

			// ms.addCustomWhereClause("( jn.ClientJobID like '%%' OR
			// jn.RefJobID like '%%' )");
			// ms.setGroupBy("jn","GroupID");
			// ms.setOrderBy("ct","ShortName");
			// ms.setOrderBy("jn","Date");

			// ms.setRetrieveField("jn", "ID");
			// ms.setRetrieveField("jn", "GroupID");
			// ms.setRecord(10, 20);

			ms.setGroupBy("ct", "ShortName");
			ms.setGroupBy("p", "ShortName");
			ms.setGroupBy("lp", "ShortName");
			ms.setGroupBy("jn", "GroupID");

			ms.setOrderBy("jn", "Date");

			// ms.setRetrieveField("jn", "ID");
			// ms.setRetrieveField("jn", "GroupID");
			// ms.setRecord(10, 20);

			ms.setRetrieveField("jn", "ID");
			ms.setRetrieveField("jn", "GroupID");
			ms.setRetrieveField("ct", "ShortName");
			ms.setRetrieveField("p", "ShortName");
			ms.setRetrieveField("lp", "ShortName");
			ms.setRetrieveField("pt", "TypeKey");
			ms.setRetrieveField("jn", "NoteNo");
			ms.setRetrieveField("jn", "Date");

			ms.setRowcount(10000);

			//String sql = ms.S();
			//System.out.println("buildSQL=" + sql);

			long startTime = System.currentTimeMillis();

			pageContext
					.getOut()
					.write(
							"<table border=\"0\" cellspacing=\"0\" cellpadding=\"1\" width=\"100%\" bgcolor=\"#ffffff\" id=\"small\">");
			pageContext.getOut().write("<tr bgcolor=\"#649caa\">");
			pageContext.getOut().write(
					"<th nowrap><bean:message key=\"JobDate\"  /></th>");
			pageContext.getOut().write(
					"<th nowrap><bean:message key=\"NoteNo\"  /></th>");
			pageContext.getOut().write(
					"<th nowrap><bean:message key=\"AddressType\"  /></th>");
			pageContext.getOut().write(
					"<th nowrap><bean:message key=\"ShortName\"  /></th>");
			pageContext.getOut().write(
					"<th nowrap><bean:message key=\"ARCurr1\"  /></th>");
			pageContext.getOut().write(
					"<th nowrap><bean:message key=\"ARPaid1\"  /></th>");
			pageContext.getOut().write(
					"<th nowrap><bean:message key=\"ARCurr2\"  /></th>");
			pageContext.getOut().write(
					"<th nowrap><bean:message key=\"ARPaid2\"  /></th>");
			pageContext.getOut().write(
					"<th nowrap><bean:message key=\"APCurr1\"  /></th>");
			pageContext.getOut().write(
					"<th nowrap><bean:message key=\"APPaid1\"  /></th>");
			pageContext.getOut().write(
					"<th nowrap><bean:message key=\"APCurr2\"  /></th>");
			pageContext.getOut().write(
					"<th nowrap><bean:message key=\"APPaid2\"  /></th>");
			pageContext.getOut().write("</tr>");

			con = DbConnectionManager.getConnection();
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("");
			int i = 0;
			while (rs.next()) {
				if (i++ % 2 == 0)
					pageContext.getOut().write("<tr bgcolor=\"#eeeeee\"");
				else

					pageContext.getOut().write("<tr>");

				pageContext.getOut().write(
						"<td align=center nowrap>" + rs.getInt(1) + "</td>");
				// System.out.println(rs.getInt(1));
				pageContext.getOut().write("</tr>");
			}

			long finishTime = System.currentTimeMillis();
			long hours = (finishTime - startTime) / 1000 / 60 / 60;
			long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
			long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60
					- minutes * 60;
			System.out.println(finishTime - startTime);
			System.out.println("expends:   " + hours + ":" + minutes + ":"
					+ seconds);

			/*
			 * HttpServletRequest request =
			 * (HttpServletRequest)pageContext.getRequest(); String iterateMax =
			 * null;
			 * 
			 * if(total == 0 && property != null) { iterateMax =
			 * (String)request.getAttribute("Iterate"+property); if (iterateMax !=
			 * null) total = new Integer(iterateMax).intValue(); else total = 0; }
			 * 
			 * if( total > 0) return EVAL_BODY_INCLUDE;//count body content else
			 * return SKIP_BODY;
			 */
			return EVAL_BODY_INCLUDE;

		} catch (SQLException sqle) {
			if (log.isDebugEnabled())
				log.error(" Error in getting results" + sqle.toString());
			return EVAL_PAGE;
		} catch (Exception e) {
			System.err.println("Error in ARBean:ARBean()-" + e);
			return EVAL_PAGE;
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspTagException {
		return EVAL_PAGE;
	}

}
