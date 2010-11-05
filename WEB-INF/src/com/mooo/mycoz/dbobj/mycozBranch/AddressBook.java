package com.mooo.mycoz.dbobj.mycozBranch;

public class AddressBook {
	private static final long serialVersionUID = 1L;
	Integer id;
	Integer userId;
	Integer countryId;
	Integer languageId;
	Integer cityId;
	String address;
	String postalCode;
	String tel;
	String mobileNo;
	public Integer getId() {
	return id;
	}
	public void setId(Integer id) {
	 this.id = id;
	}
	public Integer getUserId() {
	return userId;
	}
	public void setUserId(Integer userId) {
	 this.userId = userId;
	}
	public Integer getCountryId() {
	return countryId;
	}
	public void setCountryId(Integer countryId) {
	 this.countryId = countryId;
	}
	public Integer getLanguageId() {
	return languageId;
	}
	public void setLanguageId(Integer languageId) {
	 this.languageId = languageId;
	}
	public Integer getCityId() {
	return cityId;
	}
	public void setCityId(Integer cityId) {
	 this.cityId = cityId;
	}
	public String getAddress() {
	return address;
	}
	public void setAddress(String address) {
	 this.address = address;
	}
	public String getPostalCode() {
	return postalCode;
	}
	public void setPostalCode(String postalCode) {
	 this.postalCode = postalCode;
	}
	public String getTel() {
	return tel;
	}
	public void setTel(String tel) {
	 this.tel = tel;
	}
	public String getMobileNo() {
	return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
	 this.mobileNo = mobileNo;
	}

}
