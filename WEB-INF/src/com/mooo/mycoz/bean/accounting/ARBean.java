package com.mooo.mycoz.bean.accounting;

import java.util.*; //import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Types;

import com.mooo.mycoz.db.pool.*;
import com.mooo.mycoz.db.sql.*;

import org.apache.log4j.Category;

public class ARBean {
	private static Category log = null;
	// Singleton
	private static Object initLock = new Object();
	private static ARBean factory = null;

	public static ARBean getInstance() {
		if (factory == null) {
			synchronized (initLock) {
				try {
					factory = new ARBean();
				} catch (Exception e) {
					System.err.println("Exception ARBean." + e.getMessage());
					e.printStackTrace();
					return null;
				}
			}
		}
		return factory;
	}

	// build input
	private String department;
	private String searchFor;
	private String searchValue;
	private String startDate;
	private String endDate;
	private String branchID;
	private String orderBy;
	// Retrieve
	private Date JobDate;
	private String NoteNo;
	private String ClientJobID;
	private String HouseBillNo;
	private String RefJobID;
	private String MasterBillNo;
	private String AddressType;
	private String AddressShortName;
	private Double ARCurr1;
	private Double ARPaid1;
	private String FillNoteNos1;
	private Date LastDate1;
	private Double ARCurr2;
	private Double ARPaid2;
	private String FillNoteNos2;
	private Date LastDate2;
	private Date ETDPOL;
	private Date ETAPOD;

	private Double totARCurr1;
	private Double totARPaid1;
	private Double totARCurr2;
	private Double totARPaid2;

	private Double totAPCurr1;
	private Double totAPPaid1;
	private Double totAPCurr2;
	private Double totAPPaid2;

	/** DATABASE QUERIES * */
	private static final String GET_FORUMS = "SELECT forumID FROM yazdAccounting";
	private static final String GET_FORUMS_BY_FORUM_GROUP_ID = "SELECT forumID FROM yazdAccounting WHERE forumGroupID = ? order by forumorder desc";

	private static final String GET_REPORS = "SELECT ID,BranchID FROM JobNote";
	private static final String GET_REPORS_BY_JOBNOTE_ID = "SELECT ID FROM JobNote WHERE ID=? AND BranchID=?";
	private static final String CREATE_ARAP_BY_SHORTNAME = "SELECT jn.ID,jn.GroupID,jn.BranchID,jn.NoteNo,jn.ClientJobID,jn.RefJobID,jn.Date,jn.ChargeTo,jn.CustomerID,jn.PartnerID,ct.ShortName,p.ShortName,lp.ShortName,bd.JobTypeID,bd.ETDPOL,bd.ETAPOD,rj.JobDate,pt.TypeKey FROM JobNote AS jn,Customer AS ct, LocalPartner AS lp, xpcShared.Partner AS p,xpcShared.PartnerType AS pt,xpcShared.Account AS ac,xpcShared.Currency AS cu,xpcShared.AccountGroup AS ag,xpcShared.AccountType AS at,JobJournal AS jj,RefJob AS rj,BookDetail AS bd WHERE jn.CustomerID=ct.ID AND jn.BranchID=ct.BranchID AND jn.LocalPartnerID=lp.ID AND jn.BranchID=lp.BranchID AND jn.PartnerID=p.ID AND p.PartnerTypeID=pt.ID AND ac.GroupID=ag.ID AND ac.CurrencyID=cu.ID AND ag.TypeID=at.ID AND jj.AccountID=ac.ID AND jn.ID=jj.JobNoteID AND rj.ID=jn.RefJobID AND rj.BranchID=jn.BranchID AND bd.RefJobID=rj.ID AND bd.BranchID=rj.BranchID AND jj.DCType='Debit' AND at.TypeName='Account Receivable'  AND ( ct.ShortName LIKE ?  OR p.ShortName LIKE ? OR lp.ShortName LIKE ?) AND jn.IsPosted <> 'O'  AND jn.Date >= ? AND jn.Date <= '2009-04-23'  GROUP BY jn.GroupID ORDER BY ct.ShortName,p.ShortName,lp.ShortName,jn.Date";

	/*
	 * private static final String CREATE_ARAP_BY_CUSTOMER = "SELECT
	 * jn.ID,jn.GroupID,jn.BranchID,jn.NoteNo,jn.ClientJobID,jn.RefJobID,jn.Date,jn.ChargeTo,jn.CustomerID,jn.PartnerID,ct.ShortName,bd.JobTypeID,bd.ETDPOL,bd.ETAPOD,rj.JobDate,pt.TypeKey
	 * FROM JobNote AS jn,Customer AS ct,xpcShared.PartnerType AS
	 * pt,xpcShared.Account AS ac,xpcShared.Currency AS
	 * cu,xpcShared.AccountGroup AS ag,xpcShared.AccountType AS at,JobJournal AS
	 * jj,RefJob AS rj,BookDetail AS bd WHERE jn.CustomerID=ct.ID AND
	 * jn.BranchID=ct.BranchID AND ac.GroupID=ag.ID AND ac.CurrencyID=cu.ID AND
	 * ag.TypeID=at.ID AND jj.AccountID=ac.ID AND jn.ID=jj.JobNoteID AND
	 * rj.ID=jn.RefJobID AND rj.BranchID=jn.BranchID AND bd.RefJobID=rj.ID AND
	 * bd.BranchID=rj.BranchID AND jj.DCType='Debit' AND at.TypeName='Account
	 * Receivable' AND ct.ShortName LIKE ? AND jn.IsPosted <> 'O' AND jn.Date >= ?
	 * AND jn.Date <= ? AND (jn.ClientJobID like '%?%' OR jn.RefJobID like '%?%' )
	 * GROUP BY jn.GroupID ORDER BY ct.ShortName,jn.Date";
	 */
	/*
	 * private static final String CREATE_ARAP_BY_CUSTOMER = "SELECT
	 * jn.ID,jn.GroupID,jn.BranchID,jn.NoteNo,at.TypeName,jn.ClientJobID,jn.RefJobID,jn.Date,jn.ChargeTo,jn.CustomerID,jn.PartnerID,ct.ShortName,bd.JobTypeID,bd.ETDPOL,bd.ETAPOD,rj.JobDate,pt.TypeKey
	 * FROM JobNote AS jn,Customer AS ct,xpcShared.PartnerType AS
	 * pt,xpcShared.Account AS ac,xpcShared.Currency AS
	 * cu,xpcShared.AccountGroup AS ag,xpcShared.AccountType AS at,RefJob AS
	 * rj,BookDetail AS bd WHERE jn.CustomerID=ct.ID AND jn.BranchID=ct.BranchID
	 * AND ac.GroupID=ag.ID AND ac.CurrencyID=cu.ID AND ag.TypeID=at.ID AND
	 * rj.ID=jn.RefJobID AND rj.BranchID=jn.BranchID AND bd.RefJobID=rj.ID AND
	 * bd.BranchID=rj.BranchID AND ct.ShortName LIKE ? AND jn.IsPosted <> 'O'
	 * AND jn.Date >= ? AND jn.Date <= ? AND (jn.ClientJobID like '%?%' OR
	 * jn.RefJobID like '%?%' ) AND jn.BranchID=? GROUP BY jn.GroupID ORDER BY
	 * ct.ShortName,jn.Date";
	 */
	// private static final String CREATE_ARAP_BY_CUSTOMER = "SELECT
	// jn.GroupID,jn.BranchID FROM JobNote AS jn,Customer AS
	// ct,xpcShared.PartnerType AS pt,xpcShared.Account AS ac,xpcShared.Currency
	// AS cu,xpcShared.AccountGroup AS ag,xpcShared.AccountType AS at,RefJob AS
	// rj,BookDetail AS bd WHERE jn.CustomerID=ct.ID AND jn.BranchID=ct.BranchID
	// AND ac.GroupID=ag.ID AND ac.CurrencyID=cu.ID AND ag.TypeID=at.ID AND
	// rj.ID=jn.RefJobID AND rj.BranchID=jn.BranchID AND bd.RefJobID=rj.ID AND
	// bd.BranchID=rj.BranchID AND ct.ShortName LIKE ? AND jn.IsPosted <> 'O'
	// AND jn.Date >= ? AND jn.Date <= ? AND (jn.ClientJobID like ? OR
	// jn.RefJobID like ? ) AND jn.BranchID=? GROUP BY jn.GroupID ORDER BY
	// ct.ShortName,jn.Date";
	// private static final String CREATE_ARAP_BY_CUSTOMER = "SELECT
	// jn.GroupID,jn.BranchID FROM JobNote AS jn,Customer AS
	// ct,xpcShared.PartnerType AS pt,xpcShared.Account AS ac,xpcShared.Currency
	// AS cu,xpcShared.AccountGroup AS ag,xpcShared.AccountType AS at,RefJob AS
	// rj,BookDetail AS bd WHERE jn.CustomerID=ct.ID AND jn.BranchID=ct.BranchID
	// AND ac.GroupID=ag.ID AND ac.CurrencyID=cu.ID AND ag.TypeID=at.ID AND
	// rj.ID=jn.RefJobID AND rj.BranchID=jn.BranchID AND bd.RefJobID=rj.ID AND
	// bd.BranchID=rj.BranchID AND ct.ShortName LIKE '' AND jn.IsPosted <> 'O'
	// AND jn.Date >= '' AND jn.Date <= '' AND (jn.ClientJobID like '' OR
	// jn.RefJobID like '' ) AND jn.BranchID='' GROUP BY jn.GroupID ORDER BY
	// ct.ShortName,jn.Date";
	/*
	 * private static final String CREATE_ARAP_BY_CUSTOMER = "SELECT
	 * jn.ID,jn.BranchID FROM JobNote AS jn,Customer AS ct,xpcShared.PartnerType
	 * AS pt,xpcShared.Account AS ac,xpcShared.Currency AS
	 * cu,xpcShared.AccountGroup AS ag,xpcShared.AccountType AS at,RefJob AS
	 * rj,BookDetail AS bd
	 * 
	 * WHERE jn.CustomerID=ct.ID AND jn.BranchID=ct.BranchID AND
	 * ac.GroupID=ag.ID AND ac.CurrencyID=cu.ID AND ag.TypeID=at.ID AND
	 * rj.ID=jn.RefJobID AND rj.BranchID=jn.BranchID AND bd.RefJobID=rj.ID AND
	 * bd.BranchID=rj.BranchID AND ct.ShortName LIKE '%%' AND jn.IsPosted <> 'O'
	 * AND (jn.ClientJobID like '%%' OR jn.RefJobID like '%%' ) AND
	 * jn.BranchID=18
	 * 
	 * GROUP BY jn.GroupID ORDER BY ct.ShortName,jn.Date";
	 */
	private ARBean() {
	}

	private ARBean(String department, String searchFor, String searchValue,
			String startDate, String endDate, String branchID, String orderBy) {
		this.department = department;
		this.searchFor = searchFor;
		this.searchValue = searchValue;
		this.startDate = startDate;
		this.endDate = endDate;
		this.branchID = branchID;
		this.orderBy = orderBy;
	}

	public int[][] getJobNoteNo() {
		// Finally, delete the forum itself and all permissions and properties
		// associated with it.
		Connection con = null;
		Statement stmt = null;
		int[][] groups = new int[0][2];
		try {
			DbobjMultiSql ms = MultiSQLFactory.getInstance();
			ms.addTable("JobNote", "jn");
			ms.addTable("Customer", "ct");
			ms.setForeignKey("jn", "CustomerID", "ct", "ID");
			ms.setForeignKey("jn", "BranchID", "ct", "BranchID");

			// ms.addTable("xpcShared.PartnerType", "pt");

			ms.addTable("xpcShared.Account", "ac");
			ms.addTable("xpcShared.Currency", "cu");
			ms.setForeignKey("ac", "CurrencyID", "cu", "ID");

			ms.addTable("xpcShared.AccountGroup", "ag");
			ms.setForeignKey("ac", "GroupID", "ag", "ID");
			ms.addTable("xpcShared.AccountType", "at");
			ms.setForeignKey("ag", "TypeID", "at", "ID");

			ms.addTable("RefJob", "rj");
			ms.addTable("BookDetail", "bd");
			ms.setForeignKey("rj", "ID", "bd", "RefJobID");
			ms.setForeignKey("rj", "BranchID", "bd", "BranchID");

			ms.setForeignKey("jn", "RefJobID", "rj", "ID");
			ms.setForeignKey("jn", "BranchID", "rj", "BranchID");

			ms.setNotEqual("jn", "IsPosted", "0");
			// ms.setLike("ct", "ShortName", "SE");
			// ms.setGreaterEqual("jn", "Date", "2008-08-01");
			// ms.setLessEqual("jn", "Date", "2008-09-01");

			// ms.addCustomWhereClause("( jn.ClientJobID like '%%' OR
			// jn.RefJobID like '%%' )");
			ms.setGroupBy("jn", "GroupID");
			ms.setOrderBy("ct", "ShortName");
			ms.setOrderBy("jn", "Date");

			ms.setRetrieveField("jn", "ID");
			ms.setRetrieveField("jn", "GroupID");
			ms.setRecord(10, 20);

			String sql = ms.buildSQL();
			System.out.println("buildSQL=" + sql);

			long startTime = System.currentTimeMillis();

			con = DbConnectionManager.getConnection();
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getInt(1));
			}

			long finishTime = System.currentTimeMillis();
			long hours = (finishTime - startTime) / 1000 / 60 / 60;
			long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
			long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60
					- minutes * 60;
			System.out.println(finishTime - startTime);
			System.out.println("expends:   " + hours + ":" + minutes + ":"
					+ seconds);
			// ResultSet rs = pstmt.executeQuery();
			// ArrayList groupList = new ArrayList();
			// ArrayList branchList = new ArrayList();
			// while (rs.next()) {
			// groupList.add(new Integer(rs.getInt("ID")));
			// branchList.add(new Integer(rs.getInt("BranchID")));
			// }
			// groups = new int[groupList.size()][2];
			// for (int i = 0; i < groups.length; i++) {
			// groups[i][0] = ((Integer) groupList.get(i)).intValue();
			// groups[i][1] = ((Integer) branchList.get(i)).intValue();
			// }
		} catch (Exception sqle) {
			System.err.println("Error in ARBean:ARBean()-" + sqle);
		} finally {
			try {
				// pstmt.close();
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
		return groups;
	}
}
