package com.mooo.mycoz.dbobj.mycozBranch;

import java.sql.SQLException;

import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.dbobj.mycozShared.City;
import com.mooo.mycoz.dbobj.mycozShared.Country;
import com.mooo.mycoz.dbobj.mycozShared.Language;

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
	
	// extended attribute

	Country country;
	Language language;
	City city;
	
	public Country getCountry() {
		DbProcess dbProcess = DbFactory.getInstance();
		country = new Country();
		country.setId(getCountryId());
		try {
			dbProcess.retrieve(country);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Language getLanguage() {
		DbProcess dbProcess = DbFactory.getInstance();
		language = new Language();
		language.setId(getLanguageId());
		try {
			dbProcess.retrieve(language);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public City getCity() {
		DbProcess dbProcess = DbFactory.getInstance();
		city = new City();
		city.setId(getCityId());
		try {
			dbProcess.retrieve(city);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
}
