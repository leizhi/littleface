package com.mooo.mycoz.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozShared.BlogType;

public class AutoComplete {

	private static Log log = LogFactory.getLog(AutoComplete.class);

	public void listUserStateRun(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String sql = "";
			String prefix = request.getParameter("prefix");

			User user = new User();
			ResultSet rs = null;
			sql += "SELECT UserName FROM User WHERE ID > 0 AND UserName LIKE '%"
					+ prefix + "%' LIMIT 10";
			
			if (rs.next()) {
				PrintWriter output = response.getWriter();
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				output.println("<response>");
				output.println("<value>" + rs.getString("UserName")
						+ "</value>");
				while (rs.next()) {
					output.println("<value>" + rs.getString("UserName")
							+ "</value>");
				}
				output.println("</response>");
				output.close();
			} else {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
			if (log.isDebugEnabled())
				log.debug("SQL: " + sql);
		} catch (SQLException sqlEx) {
			if (log.isDebugEnabled())
				log.debug("SQLException: " + sqlEx.getMessage() + "SQLState: "
						+ sqlEx.getSQLState() + "ErrorCode: "
						+ sqlEx.getErrorCode());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
	}

	public void listBlogTypeStateRun(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String sql = "";
			String prefix = request.getParameter("prefix");

			BlogType bt = new BlogType();
			ResultSet rs = null;
			sql += "SELECT Name FROM BlogType WHERE ID > 0 AND Name LIKE '%"
					+ prefix + "%' LIMIT 10";
			bt.getResultSet(sql);
			rs = bt.getResultSet(sql);

			if (rs.next()) {
				PrintWriter output = response.getWriter();
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				output.println("<response>");
				output.println("<value>" + rs.getString("Name") + "</value>");
				while (rs.next()) {
					output.println("<value>" + rs.getString("Name")
							+ "</value>");
				}
				output.println("</response>");
				output.close();
			} else {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
			if (log.isDebugEnabled())
				log.debug("SQL: " + sql);
		} catch (SQLException sqlEx) {
			if (log.isDebugEnabled())
				log.debug("SQLException: " + sqlEx.getMessage() + "SQLState: "
						+ sqlEx.getSQLState() + "ErrorCode: "
						+ sqlEx.getErrorCode());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
	}
}
