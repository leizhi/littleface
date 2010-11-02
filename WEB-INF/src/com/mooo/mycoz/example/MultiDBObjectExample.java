package com.mooo.mycoz.example;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozShared.CodeType;
import com.mooo.mycoz.dbobj.mycozShared.LinearCode;

public class MultiDBObjectExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultiDBObject mdb = new MultiDBObject();
		mdb.addTable(CodeType.class, "ct");
		mdb.addTable(LinearCode.class, "lc");
		
		mdb.setForeignKey("ct", "id", "lc", "typeid");
		mdb.setField("ct.id", "1");
		
		mdb.setRetrieveField("lc", "id");
		mdb.setRetrieveField("lc", "name");

		mdb.setRetrieveField("ct", "id");

		List examples = mdb.searchAndRetrieveList();
		for (Iterator it = examples.iterator(); it.hasNext();) {
			Map map = (Map)it.next();
			CodeType ct = (CodeType)map.get("ct");
			LinearCode lc = (LinearCode)map.get("lc");
			
			System.out.println("ct id="+ct.getId()+" lc name"+lc.getName()+" lc id="+lc.getId());
		}
		
		System.out.println("sql="+mdb.searchSQL());
		//System.out.println("searchAndRetrieveList="+examples);

		
		//System.out.println("sql="+mdb.searchSQL());

	}

}
