package com.model;

public class Nominee 
{
	private String nomineeID;
	private String nomineeName;
	private String constitution;
	private String district;
	private String symbol;
	private String address;
    private int voteCount;
	
	public Nominee() 
	{
		super();
	}
	
	public Nominee(String nomineeID, String nomineeName, String constitution, String district, String symbol, String address) 
	{
		super();
		this.nomineeID = nomineeID;
		this.nomineeName = nomineeName;
		this.constitution = constitution;
		this.district = district;
		this.symbol = symbol;
		this.address = address;
	}

	public String getNomineeID() {
		return nomineeID;
	}
	public String getNomineeName() {
		return nomineeName;
	}
	public String getConstitution() {
		return constitution;
	}
	public String getDistrict() {
		return district;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getAddress() {
		return address;
	}
	public int getVoteCount() {
		return voteCount;
	}

	public void setNomineeID(String nomineeID) {
		this.nomineeID = nomineeID;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
}
