package com.mooo.mycoz.dbobj.mycozBranch;

import java.sql.SQLException;
import java.util.Date;

import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.dbobj.mycozShared.Career;
import com.mooo.mycoz.dbobj.mycozShared.Education;
import com.mooo.mycoz.dbobj.mycozShared.HeightUnit;
import com.mooo.mycoz.dbobj.mycozShared.Married;
import com.mooo.mycoz.dbobj.mycozShared.Sex;
import com.mooo.mycoz.dbobj.mycozShared.WeightUnit;

public class UserInfo {
	private Integer id;
	private Integer userId;
	private Integer sexId;
	private Double height;
	private Integer heightUnitId;
	private Double weight;
	private Integer weightUnitId;
	private Date birthday;
	private Integer careerId;
	private Integer educationId;
	private Integer marriedId;
	private String qq;
	private String secret;
	private String email;
	
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
	public Integer getSexId() {
		return sexId;
	}
	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Integer getHeightUnitId() {
		return heightUnitId;
	}
	public void setHeightUnitId(Integer heightUnitId) {
		this.heightUnitId = heightUnitId;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getWeightUnitId() {
		return weightUnitId;
	}
	public void setWeightUnitId(Integer weightUnitId) {
		this.weightUnitId = weightUnitId;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getCareerId() {
		return careerId;
	}
	public void setCareerId(Integer careerId) {
		this.careerId = careerId;
	}
	public Integer getEducationId() {
		return educationId;
	}
	public void setEducationId(Integer educationId) {
		this.educationId = educationId;
	}
	public Integer getMarriedId() {
		return marriedId;
	}
	public void setMarriedId(Integer marriedId) {
		this.marriedId = marriedId;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	// extended attribute

	private User user;
	private Sex sex;
	private HeightUnit heightUnit;
	private WeightUnit weightUnit;
	private Career career;
	private Education education;
	private Married married;

	public User getUser() {
		DbProcess dbProcess = DbFactory.getInstance();
		user = new User();
		user.setId(getUserId());
		try {
			dbProcess.retrieve(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Sex getSex() {
		DbProcess dbProcess = DbFactory.getInstance();
		try {
			sex = new Sex();
			sex.setId(getSexId());
			
			System.out.println("sexId="+getSexId());
			System.out.println("sexId="+sex.getId());

			dbProcess.retrieve(sex);
			System.out.println("sexId="+sex.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public HeightUnit getHeightUnit() {
		DbProcess dbProcess = DbFactory.getInstance();
		heightUnit = new HeightUnit();
		heightUnit.setId(getHeightUnitId());
		try {
			dbProcess.retrieve(heightUnit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return heightUnit;
	}
	public void setHeightUnit(HeightUnit heightUnit) {
		this.heightUnit = heightUnit;
	}
	public WeightUnit getWeightUnit() {
		DbProcess dbProcess = DbFactory.getInstance();
		weightUnit = new WeightUnit();
		weightUnit.setId(getWeightUnitId());
		try {
			dbProcess.retrieve(weightUnit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return weightUnit;
	}
	public void setWeightUnit(WeightUnit weightUnit) {
		this.weightUnit = weightUnit;
	}
	public Career getCareer() {
		DbProcess dbProcess = DbFactory.getInstance();
		career = new Career();
		career.setId(getCareerId());
		try {
			dbProcess.retrieve(career);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return career;
	}
	public void setCareer(Career career) {
		this.career = career;
	}
	public Education getEducation() {
		DbProcess dbProcess = DbFactory.getInstance();
		education = new Education();
		education.setId(getEducationId());
		try {
			dbProcess.retrieve(education);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	public Married getMarried() {
		DbProcess dbProcess = DbFactory.getInstance();
		married = new Married();
		married.setId(getMarriedId());
		try {
			dbProcess.retrieve(married);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return married;
	}
	public void setMarried(Married married) {
		this.married = married;
	}
	
}
