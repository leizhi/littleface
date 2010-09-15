package com.mooo.mycoz.test;

import java.sql.SQLException;
import java.util.Date;

import com.mooo.mycoz.db.sql.OracleSQL;
import com.mooo.mycoz.dbobj.marketmoniter.BufferTraffic;

public class Test {

	public static void main(String[] args) {

		System.out.println(com.mooo.mycoz.util.IDGenerator.getNowTime());
		System.out.println(com.mooo.mycoz.util.IDGenerator.getNow());
		System.out.println(com.mooo.mycoz.util.IDGenerator.getNowYear());
		
		BufferTraffic bufferTraffic = new BufferTraffic();
		bufferTraffic.setRemoteid("sdfsaf08");
		bufferTraffic.setOperDate(new Date());
		
		try {
			bufferTraffic.add();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}