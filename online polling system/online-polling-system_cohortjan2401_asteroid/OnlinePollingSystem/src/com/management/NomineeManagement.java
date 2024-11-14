package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Nominee;

public class NomineeManagement 
{
	public boolean addNomineeList(List<Nominee> nomineeList) 
	{
		int count = 0;
		try (Connection con = DBConnectionManager.getConnection())
		{
			String query = "Insert INTO Nominee (nomineeId,nomineeName,constitution,district,symbol,address) VALUES (?,?,?,?,?,?)";
			for(Nominee nominee: nomineeList) 
			{
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, nominee.getNomineeID());
				ps.setString(2, nominee.getNomineeName());
				ps.setString(3, nominee.getConstitution());
				ps.setString(4, nominee.getDistrict());
				ps.setString(5, nominee.getSymbol());
				ps.setString(6, nominee.getAddress());
				count = ps.executeUpdate();
			}
		}
		catch(SQLException  e) 
		{
			System.out.println(e.getMessage());
		}
		return count>0;
	}
	
	public boolean updateNomineeDetails(String nomineeID,String address) 
	{
		int count = 0;
		try	(Connection con = DBConnectionManager.getConnection())
		{
			String query = "UPDATE Nominee SET address = '"+address+"' WHERE nomineeID = '"+nomineeID+"'";
			PreparedStatement ps = con.prepareStatement(query);
//			ps.setString(1, address);
//			ps.setString(2, nomineeID);
			count = ps.executeUpdate(query);
		}
		catch(SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		return count>0;  
	}
	
	public boolean deleteNomineeId(String nomineeID) 
	{
		int count = 0;
		try (Connection con = DBConnectionManager.getConnection())
		{
			String query = "DELETE FROM Nominee WHERE nomineeID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, nomineeID);
			count = ps.executeUpdate();
		}
		catch(SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		return count>0;
	}
	
	public Nominee viewNomineeIDdetails(String nomineeID) 
	{
		Nominee nominee = null;
		try (Connection con = DBConnectionManager.getConnection()) 
		{
			String query = "SELECT * FROM Nominee WHERE nomineeID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, nomineeID);
			ResultSet rs = ps.executeQuery();		
			while(rs.next()) 
			{
			    nomineeID = rs.getString(1);
				String nomineeName = rs.getString(2);
				String constitution = rs.getString(3);
				String district = rs.getString(4);
				String symbol = rs.getString(5);
				String address = rs.getString(6);
				nominee = new Nominee(nomineeID,nomineeName,constitution,district,symbol,address);
			}
		}
		catch(SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		return nominee;  
	}
	
	public List<Nominee> viewNomineeListByDistrict() 
	{
		List<Nominee> nomineeDistrictList = new ArrayList<Nominee>();
		try (Connection con = DBConnectionManager.getConnection())
		{
			String query ="SELECT * FROM Nominee Order BY district";
			PreparedStatement ps= con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
			{
				String nomineeID = rs.getString(1);
				String nomineeName = rs.getString(2);
				String constitution = rs.getString(3);
				String district = rs.getString(4);
				String symbol = rs.getString(5);
				String address = rs.getString(6);
				nomineeDistrictList.add(new Nominee(nomineeID,nomineeName,constitution,district,symbol,address));  
			}  
		}
		catch(SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		return nomineeDistrictList;
	}
	
		 
	public void WinningConstitution() 
	{		
		try	(Connection con = DBConnectionManager.getConnection())
		{
			String query = "SELECT constitution,count(voteId) as voteCount FROM Nominee n join Vote v on n.nomineeId = v.nomineeId GROUP BY constitution";
			PreparedStatement ps = con.prepareStatement(query);
		   	ResultSet rs = ps.executeQuery();
		   	String winning = null;
		   	int maxVotes = 0;
		   	while(rs.next())
		   	{
		   		String constitutionName = rs.getString(1);
		   		int totalVotes = rs.getInt(2);
		   		if(totalVotes>maxVotes)
		   		{
		   			maxVotes = totalVotes;
		   			winning = constitutionName;
		   		}
		   	}
		   	if(winning!=null)
		   	{
		   		System.out.println("The winning constitution is: "+ winning +" with "+maxVotes+" votes.");
		   	}
		   	else 
		   	{
		   		System.out.println("No votes recorded.");
		   	}	
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public Map<String,Integer> partyWiseVoteCounting()
	{
		Map<String,Integer> partyWiseVoteCountinglist = new HashMap<String,Integer>();
		try	(Connection con = DBConnectionManager.getConnection())
		{
            String query = "SELECT constitution,count(voteId) as voteCount FROM Nominee n join Vote v on n.nomineeId = v.nomineeId GROUP BY constitution";
	   		PreparedStatement ps = con.prepareStatement(query);
	   		ResultSet rs = ps.executeQuery();
	   		while(rs.next())
	   		{
	   			String constitution = rs.getString(1);
	   			int voteCount = rs.getInt(2);
	   			partyWiseVoteCountinglist.put(constitution, voteCount);
	   		}
		}
	   	catch(SQLException e)
	   	{
	   		System.out.println(e.getMessage());
	   	}
		return partyWiseVoteCountinglist;
	}
}
