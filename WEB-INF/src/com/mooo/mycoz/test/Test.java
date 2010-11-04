package com.mooo.mycoz.test;

import java.util.Date;
import java.util.List;

import oracle.jdbc.dbaccess.DBStatement;

import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.db.sql.MysqlSQL;
import com.mooo.mycoz.dbobj.mycozBranch.AccessLog;
import com.mooo.mycoz.dbobj.mycozBranch.UserInfo;

public class Test {

	public static void main(String[] args) {
		//long maxLong = 9223372036854775807L;
		//int maxInt = 2147483647;
		DbProcess dbProces = DbFactory.getInstance();
//		ThreadType sex = new ThreadType();
		try {
			UserInfo al = new UserInfo();
			al.setHeightUnitId(56);
			al.setId(3);
/*
			dbProces.refresh(al);
			dbProces.entityFillField(al);
			
			dbProces.addGroupBy("id,ip");
			dbProces.addOrderBy("id,ip");
			dbProces.setLike("Ip");
			
			List sexs = dbProces.searchAndRetrieveList(al);
			System.out.println("size->" + sexs.size());

			al.setId(11);
			dbProces.refresh(al);
			dbProces.entityFillField(al);
			dbProces.setGreaterEqual("Id");
			dbProces.setLike("Id");
			dbProces.setLike("Ip");
*/
			dbProces.refresh(al);
			dbProces.setLessEqual("id");
			dbProces.setExtent("joinTime", new Date(), new Date());
			dbProces.setExtent("id", 1, 10);

			dbProces.setRecord(0, 5);
			System.out.println("size->" + dbProces.count(al,DbProcess.OPEN_QUERY));

			List sexs = dbProces.searchAndRetrieveList(al,DbProcess.OPEN_QUERY);
			System.out.println("size->" + sexs.size());

//			dbProces.setRecord(2, 1);
//			List sexs = dbProces.searchAndRetrieveList(sex);
//
//			for (Iterator<?> it = sexs.iterator(); it.hasNext();) {
//				sex = (ThreadType)it.next();
//			System.out.println(sex.getId()+"->" + sex.getName()+"->"+sex.getExtension().getTopics());
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}