package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.model.Voter;
import com.util.ApplicationUtil;
 
public class VoterManagement 
{
	public boolean addVoterList(List<Voter> voterList) 
	{
		int count = 0;
		try (Connection con = DBConnectionManager.getConnection())
		{
			String query = "Insert INTO Voter (voterId,voterName,dob,age,loginId,passWord,address,district,mobileNumber) VALUES (?,?,?,?,?,?,?,?,?)";
			for(Voter voter: voterList)
			{
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, voter.getVoterId());
				ps.setString(2, voter.getVoterName());
				ps.setDate(3, ApplicationUtil.sqlDate(voter.getDob()));
				ps.setInt(4, voter.getAge());
				ps.setString(5, voter.getLoginId());
				ps.setString(6, voter.getPassWord());
				ps.setString(7, voter.getAddress());
				ps.setString(8, voter.getDistrict());
				ps.setLong(9, voter.getMobileNumber());
				count = ps.executeUpdate();
				
				System.out.println("Hi "+voter.getVoterName()+", Here is Your VoterId "+voter.getVoterId());
			}
		}
		catch(SQLException  e) 
		{
			System.out.println(e.getMessage());
		}
		return count>0;
	}
	
	public int generatingVoterID()
	{
		int count = 0;
		try	(Connection con = DBConnectionManager.getConnection())
		{
			String query = "SELECT COUNT(*) FROM Voter";
			PreparedStatement ps = con.prepareStatement(query);
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
	
	public boolean updateVoterDetails(String voterId, long mobileNumber) 
	{
		int count = 0;
		try	(Connection con = DBConnectionManager.getConnection())
		{
			String query = "UPDATE Voter SET mobileNumber = ? WHERE voterId = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(2, voterId);
			ps.setLong(1, mobileNumber);
			count = ps.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return count>0;
	}
	
	public boolean updateVoterDetails(String voterId, String address) 
	{
		int count = 0;
		try	(Connection con = DBConnectionManager.getConnection())
		{
			String query = "UPDATE Voter SET address = ? WHERE voterId = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(2, voterId);
			ps.setString(1, address);
			count = ps.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return count>0;
	}
	
	public boolean deleteVoter(String voterId) 
	{
		int count = 0;
		try (Connection con = DBConnectionManager.getConnection())
		{
			String query = "DELETE FROM Voter WHERE voterId = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, voterId);
			count = ps.executeUpdate();
		}
		catch(SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		return count>0;
	}
	
	public Voter viewVoterByVoterId(String voterId)
	{
		Voter voter = null;
		try (Connection con = DBConnectionManager.getConnection())
		{
			String query = "SELECT * FROM Voter WHERE voterId = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,voterId);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				voterId = rs.getString(1);
		    	String voterName = rs.getString(2);
		    	Date dob = rs.getDate(3);
		    	int age = rs.getInt(4);
		    	String loginId = rs.getString(5);
		    	String passWord = rs.getString(6);
		    	String address = rs.getString(7);
		    	String district = rs.getString(8);
		    	long mobileNumber = rs.getLong(9);
		    	voter = new Voter(voterId, voterName, dob, age, loginId, passWord, address, district, mobileNumber);
			}
		}
		catch(SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		return voter;
	}
	
	public List<Voter> viewVoterListByDistrict(String district)
	{
		List<Voter> viewVoterListByDistrict = new ArrayList<Voter>();
		try (Connection con = DBConnectionManager.getConnection())
		{
			String query = "SELECT * FROM Voter WHERE district = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,district);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				String voterId = rs.getString(1);
		    	String voterName = rs.getString(2);
		    	Date dob = rs.getDate(3);
		    	int age = rs.getInt(4);
		    	String loginId = rs.getString(5);
		    	String passWord = rs.getString(6);
		    	String address = rs.getString(7);
		    	district = rs.getString(8);
		    	long mobileNumber = rs.getLong(9);
		    	viewVoterListByDistrict.add(new Voter(voterId, voterName, dob, age, loginId, passWord, address, district, mobileNumber));
			}
		}
		catch(SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		return viewVoterListByDistrict;
	}
}