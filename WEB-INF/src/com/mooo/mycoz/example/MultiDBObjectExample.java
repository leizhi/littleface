package com.mooo.mycoz.example;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mooo.mycoz.component.Page;
import com.mooo.mycoz.db.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozBranch.AddressBook;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozBranch.UserImage;
import com.mooo.mycoz.dbobj.mycozBranch.UserInfo;

public class MultiDBObjectExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//mdb.addCustomWhereClause(" user.name LIKE '%"+"z"+"%'");
		
		try {
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
			mdb.setGroupBy("user", "id");
			
			mdb.setRetrieveField("user", "*");
			//mdb.setRetrieveField("userInfo", "*");
			//mdb.setRetrieveField("addressBook", "*");
			//mdb.setRetrieveField("userImage", "*");
			Page page = new Page();
			page.setPageSize(8);
			mdb.setRecord(page.getOffset(),page.getPageSize());
			//page.buildComponent(request, mdb.count());
			
			List<Object> examples = mdb.searchAndRetrieveList();
			User bean;

			for(Object map:examples){
//				bean = (User)((List<Object>) map).get("user");
				//UserInfo userInfo = (UserInfo)map.get("userInfo");
				//accounts.add(bean);
				
//				System.out.println("user name"+bean.getName());

				//System.out.println("user name"+user.getName()+" user ="+userInfo.getBirthday());
			}
			
			System.out.println("sql="+mdb.searchSQL());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println("searchAndRetrieveList="+examples);

		
		//System.out.println("sql="+mdb.searchSQL());

	}

}
