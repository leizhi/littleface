package com.mooo.mycoz.dbobj;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mooo.mycoz.db.sql.DbBulildSQL;
import com.mooo.mycoz.util.ParamUtil;
import com.mooo.mycoz.util.Transaction;

public class DBObject extends DbBulildSQL{
	
	public static Transaction tx = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	public static ResultSetMetaData rsmd = null;

	public DBObject() {
		tx = new Transaction();
	}

	public List<Object> searchAndRetrieveList(String sql, Class<?> obj) {
		List<Object> retrieveList = null;
		try {
			tx.start();
			retrieveList = new ArrayList<Object>();
			
			stmt = tx.getConnection().createStatement();
			
			rs = stmt.executeQuery(sql);
			
			rsmd = rs.getMetaData();
			Object bean;

			while (rs.next()) {
				bean = obj.newInstance();
				
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					//System.out.println("ColumnTypeName=" + rsmd.getColumnTypeName(i+1));
					ParamUtil.bindProperty(bean, ParamUtil.getFunName(rsmd.getColumnName(i + 1).toLowerCase()),
							rs.getString(i + 1), null);
					//System.out.println(rsmd.getColumnName(i + 1).toLowerCase()+"="+ rs.getString(i + 1));
				}
				//System.out.println("name="+((Download)bean).getName());
				retrieveList.add(bean);
			}

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			tx.end();
		}
		
		return retrieveList;
	}

	public List<Object> searchAndRetrieveList(String sql) {
		List<Object> retrieveList = null;
		try {
			tx.start();
			retrieveList = new ArrayList<Object>();
			
			stmt = tx.getConnection().createStatement();
			
			rs = stmt.executeQuery(sql);
			
			rsmd = rs.getMetaData();
			Object bean;

			while (rs.next()) {
				bean = this.getClass().newInstance();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					ParamUtil.bindProperty(bean, ParamUtil.getFunName(rsmd.getColumnName(i + 1).toLowerCase()),
							rs.getString(i + 1), null);
				}
				retrieveList.add(bean);
			}

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			tx.end();
		}
		
		return retrieveList;
	}
	
	public List<Object> searchAndRetrieveList() {
		List<Object> retrieveList = null;
		try {
			tx.start();
			retrieveList = new ArrayList<Object>();
			
			stmt = tx.getConnection().createStatement();
			
			rs = stmt.executeQuery(SearchSQL());
			
			rsmd = rs.getMetaData();
			Object bean;

			while (rs.next()) {
				bean = this.getClass().newInstance();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					ParamUtil.bindProperty(bean, ParamUtil.getFunName(rsmd.getColumnName(i + 1).toLowerCase()),
							rs.getString(i + 1), null);
				}
				retrieveList.add(bean);
			}

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			tx.end();
		}
		
		return retrieveList;
	}
	
	public void add(){
		try {
			tx.start();
			stmt = tx.getConnection().createStatement();
			stmt.execute(AddSQL());
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {

			try {
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			tx.end();
		}
	}
	
	public void delete(){
		try {
			tx.start();
			stmt = tx.getConnection().createStatement();
			stmt.execute(DeleteSQL());
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {

			try {
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			tx.end();
		}
	}
	
	public void update(){
		try {
			tx.start();
			stmt = tx.getConnection().createStatement();
			stmt.execute(UpdateSQL());
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {

			try {
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			tx.end();
		}
	}
	
	public void retrieve(){
		
	}
}
