package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.management.NomineeManagement;
import com.model.Nominee;
import com.util.ApplicationUtil;

public class NomineeService 
{
	List<Nominee> nomineeList = new ArrayList<Nominee>();
	
	public List<Nominee> getNomineeList(){
		return nomineeList;
	}
	public void setNomineeList(List<Nominee> nomineeList) {
		this.nomineeList=nomineeList;
	}
	
	ApplicationUtil appUtil = new ApplicationUtil();
	NomineeManagement nm = new NomineeManagement();
	
	public List<Nominee> BuildNomineeList(String[] nomineedetails)
	{
		List<Nominee> nomineeList = new ArrayList<Nominee>();
		for(String 	x: nomineedetails) 
		{
			Nominee nomineeObj = appUtil.extractNomineeDetails(x);	
			nomineeList.add(nomineeObj);
		}
		return nomineeList;
	}

	public boolean addNomineeList(List<Nominee> nomineeList) 
	{
		return nm.addNomineeList(nomineeList);
	}
	
	public boolean updateNomineeDetails(String nomineeID, String address) 
	{
		return nm.updateNomineeDetails(nomineeID, address);
	}
	
	public boolean deleteNomineeId(String nomineeID)
	{
		return nm.deleteNomineeId(nomineeID);
	}
	
	public Nominee viewNomineeIDdetails(String nomineeID)
	{
		return nm.viewNomineeIDdetails(nomineeID);
	}
	
	public List<Nominee> viewNomineeListByDistrict()
	{
		return nm.viewNomineeListByDistrict();
	}
	
	public void WinningConstitution() 
	{
		nm.WinningConstitution();
	}
	
	public Map<String,Integer> partyWiseVoteCounting()
	{
		return nm.partyWiseVoteCounting();
	}
}