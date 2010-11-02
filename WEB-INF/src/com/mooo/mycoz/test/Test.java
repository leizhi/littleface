package com.mooo.mycoz.test;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.dbobj.mycozBranch.ThreadType;
import com.mooo.mycoz.dbobj.mycozShared.Sex;

public class Test {

	public static void main(String[] args) {
		//long maxLong = 9223372036854775807L;
		//int maxInt = 2147483647;
		DbProcess dbProces = DbFactory.getInstance();
		ThreadType sex = new ThreadType();
		try {
			dbProces.setRecord(2, 1);
			List sexs = dbProces.searchAndRetrieveList(sex);

			for (Iterator<?> it = sexs.iterator(); it.hasNext();) {
				sex = (ThreadType)it.next();
			System.out.println(sex.getId()+"->" + sex.getName()+"->"+sex.getExtension().getTopics());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}