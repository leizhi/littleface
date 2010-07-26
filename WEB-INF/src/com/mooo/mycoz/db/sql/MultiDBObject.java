package com.mooo.mycoz.db.sql;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import com.mooo.mycoz.cache.*;
import com.mooo.mycoz.db.pool.*;

public class MultiDBObject extends DbMultiBulildSQL implements DbobjMultiSql {

	// public final int CACHE_TYPE = CacheManager.DBOBJ_CACHE;
	public final String CACHE_TYPE = "DBOBJ_CACHE";

	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CacheManager cacheManager;

	public MultiDBObject() {
		super();
		try {
			con = DbConnectionManager.getConnection();
			if (catalog != null)
				con.setCatalog(catalog);

			stmt = null;
			pstmt = null;
			rs = null;

			cacheManager = CacheManager.getInstance();
		} catch (SQLException se) {
			System.err.println("Exception in DbAuthorizationFactory:" + se);
			se.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}
	}

	public void execute(String sql) {

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.execute();
		} catch (SQLException sqle) {
			System.err.println("Exception in DbAuthorizationFactory:" + sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}
	}

	public void executeQuery(String sql) {
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		} catch (SQLException sqle) {
			System.err.println("Exception in DbSeesion:" + sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}

	}

	public int count(String sql) {
		int count = 0;
		try {

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			rs.first();
			count = rs.getInt(1);

		} catch (SQLException sqle) {
			System.err.println("Exception in DbSeesion:" + sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}
		return count;
	}

	public int count() {
		int count = 0;
		try {

			pstmt = con.prepareStatement(buildCountSQL());
			rs = pstmt.executeQuery();

			rs.first();
			count = rs.getInt(1);

		} catch (SQLException sqle) {
			System.err.println("Exception in DbSeesion:" + sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}
		return count;
	}

	public List searchAndRetrieveList(String sql) {
		List resultList = new LinkedList();
		try {
			con = DbConnectionManager.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);

			int n = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Map map = new HashMap();
				for (int i = 1; i <= n; i++) {
					System.out.println(rs.getMetaData().getCatalogName(i));
					map.put(rs.getMetaData().getCatalogName(i), rs.getObject(i));
				}
				resultList.add(map);
			}
		} catch (SQLException sqle) {
			System.err.println("Exception in DbSeesion:" + sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}
		return resultList;
	}

	public List searchAndRetrieveList() {
		List resultList = new LinkedList();
		// DBObjectList mobject = null;
		String querySQL;
		try {
			querySQL = buildSQL();
			if (cacheManager.getInstance().isCacheEnabled()) {
				// if
				// ((mobject=(DBObjectList)cacheManager.get(CACHE_TYPE,querySQL))
				// != null)
				// return mobject.getObjectList();

				stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery(buildSQL());
				String value;
				while (rs.next()) {
					Map map = new HashMap();
					for (Iterator<String> it = retrieveFields.iterator(); it
							.hasNext();) {
						value = (String) it.next();
						map.put(value, rs.getString(value));
					}
					resultList.add(map);
				}

				// cacheManager.add(CACHE_TYPE,querySQL,new
				// DBObjectList(resultList));
			}// enabled
		} catch (SQLException sqle) {
			System.err.println("Exception in DbSeesion:" + sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}
		return resultList;
	}

	public void debug() {

		try {
			con = DbConnectionManager.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(buildSQL());
			String value;
			for (Iterator<String> it = retrieveFields.iterator(); it.hasNext();) {
				value = (String) it.next();
				System.err.println("column:" + value);
				// sql += value + ",";
			}

			// int n = rs.getMetaData().getColumnCount();
			// for (int i = 1; i <= n; i++) {
			// map.put(rs.getMetaData().getCatalogName(i), rs.getObject(i));
			// System.err.println("column:" +
			// rs.getMetaData().getColumnName(i));
			// System.err.println("Lable:" +
			// rs.getMetaData().getColumnLabel(i));

			// }

		} catch (SQLException sqle) {
			System.err.println("Exception in DbSeesion:" + sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} catch (SQLException sqle) {
			System.err.println("Exception in DbSeesion:" + sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
			e.printStackTrace();
		}
	}

	public void finalize() {
		close();
	}

}
