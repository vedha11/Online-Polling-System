package com.service;

import java.util.ArrayList;
import java.util.List;

import com.management.VoterManagement;
import com.model.Voter;
import com.util.ApplicationUtil;


public class VoterService 
{
	List<Voter> voterList = new ArrayList<Voter>();
	
	public List<Voter> getVoterList() {
		return voterList;
	}
	public void setVoterList(List<Voter> voterList) {
		this.voterList = voterList;
	}

	ApplicationUtil appUtil = new ApplicationUtil();
	VoterManagement vm = new VoterManagement();
	
	public List<Voter> buildingVoterList(String[] voterDetails)
	{
		List<Voter> voterList = new ArrayList<Voter>();
		for(String x: voterDetails)
		{
			Voter voterObj = appUtil.extractVoterDetails(x);
			voterList.add(voterObj);
		}
		return voterList;
	}
	
	public boolean addVoterList(List<Voter> voterList)
	{
		return vm.addVoterList(voterList);
	}
	
	public int generatingVoterID()
	{
		return vm.generatingVoterID();
	}
	
	public boolean loginCheck(String loginId, String passWord)
	{
		return vm.loginCheck(loginId,passWord);
	}
	
	public boolean updateVoterDetails(String voterId, long mobileNumber) 
	{
		return vm.updateVoterDetails(voterId, mobileNumber);
	}
	
	public boolean updateVoterDetails(String voterId, String address) 
	{
		return vm.updateVoterDetails(voterId, address);
	}
	
	public boolean deleteVoter(String voterId)
	{
		return vm.deleteVoter(voterId);
	}
	
	public Voter viewVoterByVoterId(String voterId)
	{
		return vm.viewVoterByVoterId(voterId);
	}
	
	public List<Voter> viewVoterListByDistrict(String district)
	{
		return vm.viewVoterListByDistrict(district);
	}
}