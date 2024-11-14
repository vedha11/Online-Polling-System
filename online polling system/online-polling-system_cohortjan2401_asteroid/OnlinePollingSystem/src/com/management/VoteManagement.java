package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Vote;
import com.model.Voter;
import com.util.ApplicationUtil;

public class VoteManagement 
{
	public boolean addVoteList(List<Vote> voteList) 
    {
		int count = 0;
		try	(Connection con = DBConnectionManager.getConnection())
		{
	        String query = "INSERT INTO Vote (voteId,voterId,nomineeId,votedDate) VALUES (?,?,?,?)";
	        PreparedStatement ps = con.prepareStatement(query);
	        for(Vote vote: voteList)
	        {
		        ps.setString(1, vote.getVoteId());
		        ps.setString(2, vote.getVoterId());  
		        ps.setString(3, vote.getNomineeId());
		        ps.setDate(4, ApplicationUtil.sqlDate(vote.getVotedDate()));         
		        count = ps.executeUpdate();
	        }
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return count>0;
    }
	
	public int generatingVoteID()
	{
		int status = 0;
		try	(Connection con = DBConnectionManager.getConnection())
		{
			String query = "SELECT COUNT(*) FROM Vote";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			status = rs.getInt(1);
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return status;
	}
	
	public boolean loginCheck(String loginId, String passWord)
	{
		boolean count = false;
		try	(Connection con = DBConnectionManager.getConnection())
		{
			String query = "SELECT * FROM Voter WHERE loginId = ? and passWord = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, loginId);
			ps.setString(2, passWord);
			ResultSet rs = ps.executeQuery();
			count = rs.next();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return count;
	}
	
	public boolean checkPollingOrNot(String voterId) 
	{
		int count = 0;
		try	(Connection con = DBConnectionManager.getConnection())
		{
			String query = "SELECT COUNT(voterId) FROM Voter WHERE voterId = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, voterId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count>0;
	}

	public int updateNomineeVoteCount(String nomineeId) 
	{
		int count = 0;
		try	(Connection con = DBConnectionManager.getConnection())
		{
			String query = "SELECT COUNT(nomineeId) FROM vote WHERE nomineeId = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, nomineeId);
			ResultSet rs = ps.executeQuery();
			rs.next(); 
			count = rs.getInt(1);
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	   return count;
	}
   
	public List<Voter> viewVoterDetails(String loginId, String passWord) 
	{
		List<Voter> viewVoterDetails = new ArrayList<Voter>();
		try	(Connection con = DBConnectionManager.getConnection())
		{
	        String query = "SELECT * FROM Voter WHERE loginId = ? AND passWord = ?";
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, loginId);
	        ps.setString(2, passWord);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) 
  	        {
	        	String voterId = rs.getString(1);
		    	String voterName = rs.getString(2);
		    	Date dob = rs.getDate(3);
		    	int age = rs.getInt(4);
		    	loginId = rs.getString(5);
		    	passWord = rs.getString(6);
		    	String address = rs.getString(7);
		    	String district = rs.getString(8);
		    	long mobileNumber = rs.getLong(9);
		    	viewVoterDetails.add(new Voter(voterId, voterName, dob, age, loginId, passWord, address, district, mobileNumber));
	        }   
   		}
	   	catch(SQLException e)
		{
	   		System.out.println(e.getMessage());
		}
   		return viewVoterDetails;
    }  	
	 
	public int noofVotersByDistrict(String district) 
	{
		int count = 0;
		try	(Connection con = DBConnectionManager.getConnection())
		{
	   		String query = "SELECT COUNT(voterId) FROM Voter WHERE district = ?";
	   		PreparedStatement ps = con.prepareStatement(query);
	   		ps.setString(1, district);
	   		count = ps.executeUpdate();	   			
		}
	   	catch(SQLException e)
	   	{
	   		System.out.println(e.getMessage());
	   	}
	   	return count;
	}
	
	public int totalRegisteredVoters(String district)
	{
		int count = 0;
		try	(Connection con = DBConnectionManager.getConnection())
		{
	   		String query = "SELECT COUNT(voteId) FROM vote ve join voter vr on ve.voterId = vr.voterId WHERE voteId is notnull and district = ?";	   		
	   		PreparedStatement ps = con.prepareStatement(query);
	   		ps.setString(1, district);  			   			
		}
	   	catch(SQLException e)
	   	{
	   		System.out.println(e.getMessage());
	   	}
	   	return count;
	}
	
	public Map<String,Float> votingPercentageByDistrict()
	{
		Map<String,Float> votingPercentageByDistrictList = new HashMap<String,Float>();
		try	(Connection con = DBConnectionManager.getConnection())
		{			
            String query = "SELECT district,COUNT(voteId) FROM Vote GROUP BY district";
	   		PreparedStatement ps = con.prepareStatement(query);
	   		ResultSet rs = ps.executeQuery();
	   		while(rs.next())
	   		{
	   			String district = rs.getString(1);
	   			float votePercentage = (totalRegisteredVoters(district)/noofVotersByDistrict(district))*100;
	   			votingPercentageByDistrictList.put(district, votePercentage);
	   		}	   		
		}
	   	catch(SQLException e)
	   	{
	   		System.out.println(e.getMessage());
	   	}
	   	return votingPercentageByDistrictList;
	}
}