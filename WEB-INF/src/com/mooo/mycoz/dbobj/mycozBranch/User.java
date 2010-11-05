package com.mooo.mycoz.dbobj.mycozBranch;

import java.sql.SQLException;
import java.util.List;

import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.DbProcess;

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
	public List getUserImages() {
		/*DbProcess dbProcess = DbFactory.getInstance();
		UserImage userImage = new UserImage();
		userImage.setUserId(getId());
		try {
			userImages = dbProcess.searchAndRetrieveList(userImage);
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return userImages;
	}
	public void setUserImages(List userImages) {
		this.userImages = userImages;
	}

}
