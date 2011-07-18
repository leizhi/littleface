package com.mooo.mycoz.test;

import java.util.Date;

import com.mooo.mycoz.common.StringUtils;
import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.db.Transaction;
import com.mooo.mycoz.dbobj.mycozBranch.AddressBook;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozBranch.UserInfo;
import com.mooo.mycoz.util.IDGenerator;

public class InTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Transaction tx = new Transaction();
		try {
			tx.start();

			DbProcess dbProcess = DbFactory.getInstance();
			for (int i = 0; i < 100000; i++) {
				User user = new User();
				user.setId(IDGenerator.getNextID("User").intValue());
				user.setName(i+"");
				user.setPassword(i+"");

				//user.setPassword(StringUtils.hash(user.getPassword()));
				System.out.println("user 1:");

				dbProcess.add(tx.getConnection(), user);
				System.out.println("user 2:");

				UserInfo userInfo = new UserInfo();
				userInfo.setId(IDGenerator.getNextID("UserInfo").intValue());
				userInfo.setUserId(user.getId());
				userInfo.setJoinTime(new Date());

				dbProcess.add(tx.getConnection(), userInfo);
				System.out.println("userInfo:");

				AddressBook addressBook = new AddressBook();
				addressBook.setId(IDGenerator.getNextID("AddressBook").intValue());
				addressBook.setUserId(user.getId());

				dbProcess.add(tx.getConnection(), addressBook);
				System.out.println("addressBook:");

				tx.commit();
			}
		} catch (Exception e) {
			System.out.println("SQLException:"+e.getMessage());
			tx.rollback();
		} finally {
			tx.end();
		}
	}

}
