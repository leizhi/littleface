package com.mooo.mycoz.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Calendar;
import java.util.TimeZone;

import com.mooo.mycoz.db.pool.DbConnectionManager;

public class IDGenerator {

	public static HashMap<String, String> getReportTypes() {

		HashMap<String, String> lMap = new HashMap<String, String>();
		lMap.put(null, "--select--");
		lMap.put("Debit", "Debit");
		lMap.put("Credit", "Credit");
		lMap.put("Receipt", "Receipt");
		lMap.put("Defrayment", "Defrayment");

		return lMap;
	} // getDCValues()

	public synchronized static String getNextID(String table) {
		String nextID = "";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			int id = 0;
			String sql = "SELECT MAX(ID) AS MaxId FROM "+table;
			
			con = DbConnectionManager.getConnection();
			
			con.setCatalog("mycozBranch");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			//if(rs.first()) id=rs.getInt("MaxId")+1;
			if(rs.next()) id=rs.getInt("MaxId")+1;

			nextID=Integer.toString(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return nextID;
	} // getNextID(String table)


	public static String getLastMonthToday() {

		// get default date in yyyy-mm-dd format
		TimeZone tz = TimeZone.getDefault();
		// tz.setID("Asia/Jakarta");
		Calendar now = Calendar.getInstance(tz);
		String prefix;
		int yy = now.get(Calendar.YEAR);
		int tmp = now.get(Calendar.MONTH);
		if (tmp == 0) {
			prefix = (yy - 1) + "";
			tmp = 12;
		} else {
			prefix = yy + "";
		}
		if (tmp < 10)
			prefix += "-0" + tmp;
		else
			prefix += "-" + tmp;

		tmp = now.get(Calendar.DATE);
		if (tmp < 10)
			prefix += "-0" + tmp;
		else
			prefix += "-" + tmp;

		return prefix;
	} /* getLastMonthToday() */

	public static String getToday() {

		// get default date in yyyy-mm-dd format
		TimeZone tz = TimeZone.getDefault();
		Calendar now = Calendar.getInstance(tz);
		String prefix = now.toString();
		int tmp = now.get(Calendar.YEAR);
		prefix = tmp + "";
		tmp = now.get(Calendar.MONTH) + 1;
		if (tmp < 10)
			prefix += "-0" + tmp;
		else
			prefix += "-" + tmp;

		tmp = now.get(Calendar.DATE);
		if (tmp < 10)
			prefix += "-0" + tmp;
		else
			prefix += "-" + tmp;

		return prefix;
	} /* getToday() */
}
