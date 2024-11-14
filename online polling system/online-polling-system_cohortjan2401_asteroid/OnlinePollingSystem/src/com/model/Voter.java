package com.model;

import java.util.Date;

public class Voter 
{
	private String voterId;
	private String voterName;
	private Date dob;
	private int age;
	private String loginId;
	private String passWord;
	private String address;
	private String district;
	private long mobileNumber;
	
	public Voter() 
	{
		super();
	}

	public Voter(String voterId, String voterName, Date dob, int age, String loginId, String passWord, String address, String district, long mobileNumber) 
	{
		super();
		this.voterId = voterId;
		this.voterName = voterName;
		this.dob = dob;
		this.age = age;
		this.loginId = loginId;
		this.passWord = passWord;
		this.address = address;
		this.district = district;
		this.mobileNumber = mobileNumber;
	}

	public String getVoterId() {
		return voterId;
	}
	public String getVoterName() {
		return voterName;
	}
	public Date getDob() {
		return dob;
	}
	public int getAge() {
		return age;
	}
	public String getLoginId() {
		return loginId;
	}
	public String getPassWord() {
		return passWord;
	}
	public String getAddress() {
		return address;
	}
	public String getDistrict() {
		return district;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}	
}
