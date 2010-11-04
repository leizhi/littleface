package com.mooo.mycoz.example;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozBranch.AddressBook;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozBranch.UserImage;
import com.mooo.mycoz.dbobj.mycozBranch.UserInfo;

public class MultiDBObjectExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultiDBObject mdb = new MultiDBObject();
		mdb.addTable(User.class, "user");
		mdb.addTable(UserInfo.class, "userInfo");
		mdb.addTable(AddressBook.class, "addressBook");
		mdb.addTable(UserImage.class, "userImage");

		mdb.setForeignKey("userInfo", "userId", "user", "id");
		mdb.setForeignKey("addressBook", "userId", "user", "id");
		mdb.setForeignKey("userImage", "userId", "user", "id");

		//mdb.setField("ct.id", "1");
		mdb.setLike("user","name","z");
		
		mdb.setRetrieveField("user", "*");
		mdb.setRetrieveField("userInfo", "*");
		mdb.setRetrieveField("addressBook", "*");
		mdb.setRetrieveField("userImage", "*");

		//mdb.addCustomWhereClause(" user.name LIKE '%"+"z"+"%'");
		
		List<Map> examples = mdb.searchAndRetrieveList();
		for(Map map:examples){
			User user = (User)map.get("user");
			UserInfo userInfo = (UserInfo)map.get("userInfo");
			
			System.out.println("user name"+user.getName()+" user ="+userInfo.getBirthday());
		}
		
		for (Iterator it = examples.iterator(); it.hasNext();) {
			Map map = (Map)it.next();
			
			User user = (User)map.get("user");
			UserInfo userInfo = (UserInfo)map.get("userInfo");
			
			System.out.println("user name"+user.getName()+" user ="+userInfo.getWeight());
		}
		
		System.out.println("sql="+mdb.searchSQL());
		//System.out.println("searchAndRetrieveList="+examples);

		
		//System.out.println("sql="+mdb.searchSQL());

	}

}
