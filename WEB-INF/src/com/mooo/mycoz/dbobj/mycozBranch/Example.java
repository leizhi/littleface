package com.mooo.mycoz.dbobj.mycozBranch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mooo.mycoz.db.pool.DbConnectionManager;

public class Example {
	/** DATABASE QUERIES **/
	private static final String EXAMPLE_COUNT = "SELECT count(*) FROM EXAMPLE";
	private static final String EXAMPLE_LIST = "SELECT * FROM EXAMPLE";
	private static final String EXAMPLE_ADD = "INSERT INTO EXAMPLE(id, name, SCHOOL, AGE) VALUES (?,?,?,?)";

	Integer id;
	String name;
	String school;
	String age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	// Lock for saving state to database.
	private Object saveLock = new Object();
	private int order;

    public int count() {
        int count = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbConnectionManager.getConnection();
            pstmt = con.prepareStatement(EXAMPLE_COUNT);
            //pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            count = rs.getInt(1 /*"messageCount"*/);
        }
        catch( SQLException sqle ) {
            System.err.println("DbForum:getMessageCount() failed: " + sqle);
        }
        finally {
            try {  pstmt.close(); }
            catch (Exception e) { e.printStackTrace(); }
            try {  con.close();   }
            catch (Exception e) { e.printStackTrace(); }
        }
        return count;
    }
    
	public void add() {
		boolean abortTransaction = false;
		boolean supportsTransactions = false;
		// Add message to db
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbConnectionManager.getConnection();
			supportsTransactions = con.getMetaData().supportsTransactions();
			if (supportsTransactions) {
				con.setAutoCommit(false);
			}

			pstmt = con.prepareStatement(EXAMPLE_ADD);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, school);
			pstmt.setString(4, age);
			pstmt.executeUpdate();
			pstmt.close();

			// Now, insert the thread into the database.
			// ((ForumThreadProxy)thread).insertIntoDb(con);
		} catch (Exception e) {
			e.printStackTrace();
			abortTransaction = true;
			return;
		} finally {
			try {
				if (supportsTransactions) {
					if (abortTransaction == true) {
						con.rollback();
					} else {
						con.commit();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (supportsTransactions) {
					con.setAutoCommit(true);
				}
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
