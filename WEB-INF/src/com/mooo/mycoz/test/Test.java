package com.mooo.mycoz.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.dbaccess.DBStatement;

import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.DbMy;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.db.sql.MySQL;
import com.mooo.mycoz.db.sql.MysqlSQL;
import com.mooo.mycoz.dbobj.mycozBranch.AccessLog;
import com.mooo.mycoz.dbobj.mycozBranch.UserInfo;
import com.mooo.mycoz.util.IDGenerator;

public class Test {

	public static void main(String[] args) {
		long maxLong = 9223372036854775807L;
		int maxInt = 2147483647;
		try {
			long startTime = System.currentTimeMillis();

			DbProcess dbProcess = DbFactory.getInstance();
			for(int i=0;i<100;i++){
				AccessLog al = new AccessLog();
				al.setId(IDGenerator.getNextID("AccessLog").intValue());
				al.setIp(i+".ip");
				al.setStartdate(new Date());
				dbProcess.add(al);
			}
			
			long finishTime = System.currentTimeMillis();
			long hours = (finishTime - startTime) / 1000 / 60 / 60;
			long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
			long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
			
			System.out.println(finishTime - startTime);
			System.out.println("dbProcess save 100 expends:   " + hours + ":" + minutes + ":" + seconds);
			
			startTime = System.currentTimeMillis();
			
			DbMy dbMy = new DbMy();

			for(int i=0;i<100;i++){
				Map accessLog = new HashMap();
				accessLog.put("id", IDGenerator.getNextID("AccessLog").intValue());
				accessLog.put("ip", i+".ip");
				accessLog.put("startdate", new Date());
				dbMy.save(null, null, "AccessLog", accessLog);
			}
			
			finishTime = System.currentTimeMillis();
			hours = (finishTime - startTime) / 1000 / 60 / 60;
			minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
			seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
			
			System.out.println(finishTime - startTime);
			System.out.println("MySQL save 100 expends:   " + hours + ":" + minutes + ":" + seconds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}