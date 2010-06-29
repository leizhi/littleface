package com.mooo.mycoz.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.manihot.xpc.jdbc.DbConnectionManager;

public class Transaction {
	Connection con = null;
	boolean abortTransaction = false;
	boolean supportsTransactions = false;

	public void start() {
		try {
			con = DbConnectionManager.getConnection();
			supportsTransactions = con.getMetaData().supportsTransactions();
			if (supportsTransactions) {
				con.setAutoCommit(false);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isTransactional() {
		return supportsTransactions;
	}

	public Connection getConnection() {
		return con;
	}

	public void rollback() {
		if (con != null) {
			try {
				if (supportsTransactions) {
					con.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void commit() {
		if (con != null) {
			try {
				if (supportsTransactions) {
					con.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void end() {
		try {
			if (supportsTransactions) {
				con.setAutoCommit(true);
			}
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
