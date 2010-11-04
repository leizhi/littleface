package com.mooo.mycoz.dbobj.mycozBranch;

import java.sql.SQLException;
import java.util.List;

import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.dbobj.mycozShared.Country;

/**

 */
public class User {

	private Integer id;
	private String name;
	private String password;
	private String alias;
	private String active;
	public Integer getId() {
	return id;
	}
	public void setId(Integer id) {
	 this.id = id;
	}
	public String getName() {
	return name;
	}
	public void setName(String name) {
	 this.name = name;
	}
	public String getPassword() {
	return password;
	}
	public void setPassword(String password) {
	 this.password = password;
	}
	public String getAlias() {
	return alias;
	}
	public void setAlias(String alias) {
	 this.alias = alias;
	}
	public String getActive() {
	return active;
	}
	public void setActive(String active) {
	 this.active = active;
	}
	
	private List userImages;
	private UserInfo userInfo;
	private AddressBook addressBook;
	public List getUserImages() {
		DbProcess dbProcess = DbFactory.getInstance();
		UserImage userImage = new UserImage();
		userImage.setUserId(getId());
		try {
			userImages = dbProcess.searchAndRetrieveList(userImage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userImages;
	}
	public void setUserImages(List userImages) {
		this.userImages = userImages;
	}
	public UserInfo getUserInfo() {
		DbProcess dbProcess = DbFactory.getInstance();
		userInfo = new UserInfo();
		userInfo.setUserId(getId());
		try {
			dbProcess.retrieve(userInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public AddressBook getAddressBook() {
		DbProcess dbProcess = DbFactory.getInstance();
		addressBook = new AddressBook();
		addressBook.setUserId(getId());
		try {
			dbProcess.retrieve(addressBook);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addressBook;
	}
	public void setAddressBook(AddressBook addressBook) {
		this.addressBook = addressBook;
	}

}
