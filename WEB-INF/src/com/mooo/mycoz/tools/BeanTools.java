package com.mooo.mycoz.tools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.util.StringUtils;

public class BeanTools {
	
	public void build(String catalog, String table){
		Connection con = null;
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		String sql = "";
		try {
		StringBuilder buffer = new StringBuilder();
		buffer.append("import java.util.*;\n");
		//buffer.append("import com.mooo.mycoz.dbobj.DBObject;\n");
		buffer.append("public class ");
		
		ResultSet rs = null;
		//mypool
		con = DbConnectionManager.getConnection();
		con.setCatalog(catalog);
		
		System.out.println("打开连接-------------");
		System.out.println(con);
		
		DatabaseMetaData db =  DbConnectionManager.getConnection().getMetaData();
		rs = db.getPrimaryKeys(catalog, null, StringUtils.upperToPrefix(table, null));
		rsmd = rs.getMetaData();
		
		while (rs.next()) {
			for (int i = 1; i < rsmd.getColumnCount()+1; i++) {
				System.out.print(rs.getString(i) + " ");
			}
			System.out.println();
		}
		
		// 表信息
		String[] t = { "TABLE", "VIEW" };
		ResultSet tableRs = db.getTables(catalog, null, table, t);
		while (tableRs.next()) {
		for (int i = 1; i < 6; i++) {
		System.out.print(tableRs.getString(i) + " ");
		}
		System.out.println("+++++++++++++++++++++++++++++++++");
		}
		// 列信息
		rs = db.getColumns(catalog, null, table, null);
		while (rs.next()) {
		for (int i = 1; i < 19; i++) {
		System.out.print(rs.getString(i) + " ");
		}
		System.out.println("+++++++++++++++++++++++++++++++++");

		}
		//主键信息
		ResultSet pkRs = db.getPrimaryKeys(catalog, null, table);
		while (pkRs.next()) {
		for (int i = 1; i < 6; i++) {
		System.out.print(pkRs.getString(i) + " ");
		}
		System.out.println("+++++++++++++++++++++++++++++++++");

		} 
		
		sql = "SELECT  * FROM "+table;
		System.out.println(sql);
		buffer.append(StringUtils.prefixToUpper(table));
		//buffer.append(" extends DBObject {\n");
		buffer.append(" {\n");
		buffer.append("\tprivate static final long serialVersionUID = 1L;\n");

		System.out.println("beanName="+StringUtils.upperToPrefix(table));

		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		rsmd = rs.getMetaData();
		
		int type=0;
		int precision=0;
		int scale=0;
		String columnName="";

		StringBuilder gsMethod = new StringBuilder();
		
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			type = rsmd.getColumnType(i+1);
			precision = rsmd.getPrecision(i+1);
			scale = rsmd.getScale(i+1);
			
			columnName = StringUtils.prefixToUpperNot(rsmd.getColumnName(i + 1));
			
			System.out.println(columnName+" Precision: "+ precision+" Scale: "+scale);

			if(type == Types.VARCHAR || type == Types.LONGVARCHAR || type == Types.CHAR || type ==Types.LONGNVARCHAR){
				buffer.append("\tString "+ columnName + ";\n");
				gsMethod.append(StringUtils.createMethod(columnName, "String"));
			}else if (type==Types.NUMERIC){
				if(scale ==0){
					buffer.append("\tInteger "+ columnName + ";\n");
					gsMethod.append(StringUtils.createMethod(columnName, "Integer"));
				} else{
					buffer.append("\tDouble "+ columnName + ";\n");
					gsMethod.append(StringUtils.createMethod(columnName, "Double"));
				}
			}else if (type == Types.INTEGER){
				buffer.append("\tInteger "+ columnName + ";\n");
				gsMethod.append(StringUtils.createMethod(columnName, "Integer"));
			}else if (type==Types.DECIMAL){
				buffer.append("\tDouble "+ columnName + ";\n");
				gsMethod.append(StringUtils.createMethod(columnName, "Double"));
			}else if (type == Types.DATE){
				buffer.append("\tDate "+ columnName + ";\n");
				gsMethod.append(StringUtils.createMethod(columnName, "Date"));
			}else if (type == Types.TIMESTAMP){
				buffer.append("\tDate "+ columnName + ";\n");
				gsMethod.append(StringUtils.createMethod(columnName, "Date"));
				System.out.println("TIMESTAMP");

			}else if (type == Types.DOUBLE){
				buffer.append("\tDouble "+ columnName + ";\n");
			}
		}
		
		buffer.append(gsMethod.toString());
		buffer.append("}");

		System.out.println(buffer);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void buildInsert(String table){
		Connection con = null;
		Statement stmt = null;
		ResultSetMetaData rsmd = null;
		String sql = "";
		try {

		ResultSet rs = null;
		//mypool
		con = DbConnectionManager.getConnection();
		System.out.println("打开连接-------------");
		System.out.println(con);
		
		sql = "SELECT  * FROM "+table;
		System.out.println(sql);

		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		rsmd = rs.getMetaData();
		String columnName="";

		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			columnName = rsmd.getColumnName(i + 1).toLowerCase();
			System.out.print(columnName + ",");
		}
		System.out.println();
		
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BeanTools bd = new BeanTools();
		//bd.build("bus_remotes");
		//bd.build("bus_samples");
		//bd.build("marketmoniter","buffer_price");
		//bd.build("buffer_traffic");
		//bd.build("mycozBranch","Example");
		//bd.build("mycozBranch","FileInfo");
		//bd.build("mycozBranch","AccessLog");
		//bd.build("mycozBranch","User");
		//bd.build("mycozBranch","Account");
		bd.build("mycozBranch","UserInfo");

		//bd.build("mycozShared","CodeType");

		//bd.buildInsert("buffer_traffic");

	}

}
