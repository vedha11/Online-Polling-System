package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.model.Nominee;
import com.model.Vote;
import com.model.Voter;
import com.service.VoteService;
import com.service.VoterService;


public class ApplicationUtil 
{
	public java.util.Date stringToDate(String date)
	{
		java.util.Date dobj = null;
		try 
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dobj = sdf.parse(date);
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		return  dobj;
	}
	
	public static java.sql.Date sqlDate(java.util.Date date)
	{
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}
    	
    public Voter extractVoterDetails(String voterDetails)
    {
    	Voter voterObj = null;
    	String[] extract = voterDetails.split(":");
    	String voterId = generatingVoterID();
    	String voterName = extract[0];
    	Date dob = stringToDate(extract[1]);
    	int age = Integer.parseInt(extract[2]);
    	String loginId = extract[3];
    	String passWord = extract[4];
    	String address = extract[5];
    	String district = extract[6];
    	long mobileNumber = Long.parseLong(extract[7]);
    	voterObj = new Voter(voterId, voterName, dob, age, loginId, passWord, address, district, mobileNumber);
		return voterObj;
    }
    static  int voterId = 0;
    public static String generatingVoterID()
    {
		VoterService voterService = new VoterService();
    	if(voterId==0) 
    	{
    		voterId = voterService.generatingVoterID();
    	}
    	voterId++;
    	String tnrId = "TNR";
    	String strId = voterId+"";
    	int len = strId.length();
    	if(len==1)
    	{
    		tnrId += "00"+strId;
    	}
    	else if(len==2)
    	{
    		tnrId += "0"+strId;
    	}
    	else
    	{
    		tnrId += strId;
    	}
		return tnrId;
    }
    
    public Vote extractVoteDetails(String VoteDetails)
    {
    	VoteService voteService = new VoteService();
    	Vote VoteObj = null;
		String[] extract = VoteDetails.split(":");
		String voteId = generatingVoteID();
		String voterId = extract[0];
		String nomineeId = extract[1];
		Date votedDate = stringToDate(extract[2]);
		if(voteService.checkPollingOrNot(voterId))
		{
			VoteObj = new Vote(voteId,voterId,nomineeId,votedDate);	
		}
		else
		{
			System.out.println("Your vote has been polled already...!");
			
		}
		return VoteObj;
    }
    
    static  int VoteId = 1;
    public static String generatingVoteID()
    {
    	VoteService voteService = new VoteService();
    	if(VoteId==1) 
    	{
    		VoteId = voteService.generatingVoteID();
    	}
    	VoteId++;
    	String tnpId = "TNP";
    	String strId = VoteId+"";
    	int len = strId.length();
    	if(len==1)
    	{
    		tnpId += "00"+strId;
    	}
    	else if(len==2)
    	{
    		tnpId += "0"+strId;
    	}
    	else
    	{
    		tnpId += strId;
    	}
		return tnpId;
    }
    
    public Nominee extractNomineeDetails(String nomineeDetails)
    {
    	Nominee nomineeObj = null;
		String extract[] = nomineeDetails.split(":");
		String nomineeID = extract[0];
		String nomineeName = extract[1];
		String constituition = extract[2];
		String district = extract[3];
		String symbol = extract[4];
		String address = extract[5];
		nomineeObj = new Nominee(nomineeID, nomineeName, constituition, district, symbol, address );
		return nomineeObj;
    }   
}