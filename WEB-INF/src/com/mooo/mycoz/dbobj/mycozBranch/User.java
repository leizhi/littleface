package com.mooo.mycoz.dbobj.mycozBranch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mooo.mycoz.db.pool.DbConnectionManager;

/**

 */
public class User {

	private static Log log = LogFactory.getLog(User.class);

	Integer id;
	Integer stateid;
	String name;
	String password;
	
	public Integer getId() {
	return id;
	}
	public void setId(Integer id) {
	 this.id = id;
	}
	public Integer getStateid() {
	return stateid;
	}
	public void setStateid(Integer stateid) {
	 this.stateid = stateid;
	}
	public String getName() {
	return name;
	}
	public void setName(String name) {
	 this.name = name;
	}
	public String getPassword() {
	return password;
	}
	public void setPassword(String password) {
	 this.password = password;
	}

	public boolean loginCheck() {

		if (name == null || name.equals("") || password == null
				|| password.equals(""))
			
			return false;
		
		String sql;
		Connection con = DbConnectionManager.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		sql = "SELECT ID,Name,Password FROM User";
		sql += " WHERE Password=Password('" + password.replaceAll("'", "")
				+ "')";
		boolean isLogin = false;
		
		try {
			if (log.isDebugEnabled()) log.debug("SQL=" + sql);

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.first()) {

				if (log.isDebugEnabled()) log.debug("pas=" + rs.getString(3));
				if (log.isDebugEnabled()) log.debug("id=" + rs.getInt(1));

				setId(rs.getInt(1));

				if (rs.getString(2).equals(name))
					isLogin = true;

			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return isLogin;
	}

}
