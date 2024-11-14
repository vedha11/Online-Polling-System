package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.management.VoteManagement;
import com.model.Vote;
import com.model.Voter;
import com.util.ApplicationUtil;

public class VoteService 
{	
	public List<Vote> VoteLists=new ArrayList<Vote>();
	
	public List<Vote> getVoteList() {
		return VoteLists;
	}
	public void setVoteList(List<Vote> VoteLists) {
		this.VoteLists = VoteLists;
	}
	
	
	ApplicationUtil app = new ApplicationUtil();
	VoteManagement vm = new VoteManagement();
	
	public List<Vote> buildingVoteList(String voteDetails)
	{
		List<Vote> voteList = new ArrayList<Vote>();
		Vote voteObj = app.extractVoteDetails(voteDetails);
		if(voteObj!=null)
		{
			voteList.add(voteObj);
		}
		return voteList;
	}
	public boolean addVoteList(List<Vote> voteList)
	{
		return vm.addVoteList(voteList);
	}
	
	public int generatingVoteID()
	{
		return vm.generatingVoteID();
	}
	
	public boolean loginCheck(String loginId, String passWord)
	{
		return vm.loginCheck(loginId, passWord);
	}
	
	public boolean checkPollingOrNot(String voterId)
	{
		return vm.checkPollingOrNot(voterId);
	}
	
	public int updateNomineeVoteCount(String nomineeId)  
	{
		return vm.updateNomineeVoteCount(nomineeId);
	}
	
	public List<Voter> viewVoterDetails(String loginId, String passWord) 
	{
		return vm.viewVoterDetails(loginId,passWord);
	}
	
	public Map<String,Float> votingPercentageByDistrict()
	{
		return vm.votingPercentageByDistrict();
	}	
}
