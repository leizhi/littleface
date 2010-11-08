package com.mooo.mycoz.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mooo.mycoz.db.pool.DbConnectionManager;

public class JdbcTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection=null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			long startTime = System.currentTimeMillis();

			connection = DbConnectionManager.getConnection();
			stmt = connection.createStatement();
			
			//ex.setTable("Example");
			result = stmt.executeQuery("SELECT * FROM User u,UserInfo ui WHERE ui.userId=u.id LIMIT 4");
			
			while(result.next()){
				System.out.println("id="+result.getString("u.id")+" name="+result.getString("u.name"));
			}
			
			long finishTime = System.currentTimeMillis();
			long hours = (finishTime - startTime) / 1000 / 60 / 60;
			long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
			long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
			
			System.out.println(finishTime - startTime);
			System.out.println("expends:   " + hours + ":" + minutes + ":" + seconds);
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException:"+e.getMessage());
		}finally {
			try {
				System.out.println("finally befon");
				
				System.out.println("result:"+result.isClosed());
				System.out.println("stmt:"+stmt.isClosed());
				System.out.println("connection:"+connection.isClosed());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
//			try {
//				if (result != null) {
//					result.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		try {
			System.out.println("end");

			System.out.println("result:"+result.isClosed());
			System.out.println("stmt:"+stmt.isClosed());
			System.out.println("connection:"+connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
