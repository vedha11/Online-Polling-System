package com.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.service.*;
import com.model.*;

public class UserInterface 
{
	public static void main(String[] args) 
	{
		NomineeService nomineeService = new NomineeService();
		VoteService voteService = new VoteService();
		VoterService voterService = new VoterService();
		
		try (Scanner sc = new Scanner(System.in)) 
		{
			while (true)
			{
				System.out.println("1.Voter Registration");
				System.out.println("2.Nominee Registration");
				System.out.println("3.Voting Process");
				System.out.println("4.Result Publication");
				System.out.println("5.Report Generation");
				System.out.println("6.Exit\n");
				System.out.println("Enter your option");
				int option = sc.nextInt();
					 
			    if(option==1) 
			    {
			    	while (true) 
			    	{
				    	System.out.println("1.New Voter Registration");
				    	System.out.println("2.Update Voter Details");
				    	System.out.println("3.Delete Voter Details"); 
				    	System.out.println("4.View Voter Details"); 
				    	System.out.println("5.Back\n");
				    	System.out.println("Enter your choice");
				    	int choice = sc.nextInt();sc.nextLine();
				    	if(choice==1)
				    	{
				    		System.out.println("Enter the no of voter details");
				    		int n = sc.nextInt();sc.nextLine();
				    		String[] voterDetails = new String[n];
				    		System.out.println("Enter the Voter Details");
				    		System.out.println("Example Input Format");
				    		System.out.println("VoterName:DOB(DD/MM/YYYY):Age:LoginId:PassWord:Address:District:MobileNumber");
				    		for(int i=0; i<n; i++)
				    		{
				    			voterDetails[i] = sc.nextLine();
				    		}
				    		List<Voter> voterList = new ArrayList<Voter>();
				    		voterList = voterService.buildingVoterList(voterDetails);
				    		if(voterService.addVoterList(voterList))
				    		{
				    			System.out.println("Voter Registration Successfully");
				    		}
				    		else
				    		{
				    			System.out.println("Your Registration Failed... Please Try Again!!!");
				    		}
				    	}
				    	else if(choice==2)
				    	{
				    		System.out.println("1.Update Voter Mobile Number");
				    		System.out.println("2.Update Voter Address\n");
				    		System.out.println("Enter your choice");
					    	int choose = sc.nextInt();sc.nextLine();
					    	System.out.println("Enter Your LoginId");
					    	String loginId = sc.nextLine();	
					    	System.out.println("Enter Your PassWord");
					    	String passWord = sc.nextLine();	
					    	if(voterService.loginCheck(loginId,passWord))
					    	{
					    		System.out.println("Enter Your VoterId");
						    	String voterId = sc.nextLine();
					    		if(choose==1)
						    	{
						    		System.out.println("Enter Your New Mobile Number");
						    		long mobileNumber = sc.nextLong();
						    		if(voterService.updateVoterDetails(voterId,mobileNumber))
						    		{
						    			System.out.println("Your Moblie Number Updated Successfully");
						    		}
						    		else
						    		{
						    			System.out.println("Failed To Update Your Moblie Number... Sorry Try Again!!!");
						    		}
						    	}
						    	else if(choose==2)
						    	{
						    		System.out.println("Enter Your New Address");
						    		String address = sc.nextLine();
						    		if(voterService.updateVoterDetails(voterId,address))
						    		{
						    			System.out.println("Your Address Updated Successfully");
						    		}
						    		else
						    		{
						    			System.out.println("Failed To Update Your Address... Sorry Try Again!!!");
						    		}
						    	}
					    	}
					    	else
					    	{
					    		System.out.println("Sorry...Please Check Your LoginId or PassWord..!");
					    	}
					    }
				    	else if(choice==3)
				    	{
				    		System.out.println("Enter Your LoginId");
					    	String loginId = sc.nextLine();	
					    	System.out.println("Enter Your PassWord");
					    	String passWord = sc.nextLine();	
					    	if(voterService.loginCheck(loginId,passWord))
					    	{
					    		System.out.println("Enter Your VoterId");
					    		String voterId = sc.nextLine();
					    		if(voterService.deleteVoter(voterId))
					    		{
					    			System.out.println("Your Details Deleted Successfully");
					    		}
					    		else
					    		{
					    			System.out.println("Failed To Delete Your details... Sorry Try Again!!!");
					    		}
					    	}
					    	else
					    	{
					    		System.out.println("Sorry...Please Check Your LoginId or PassWord..!");
					    	}
				    	}
				    	else if(choice==4)
				    	{
				    		System.out.println("1.Show My Details");
				    		System.out.println("2.Show Voter Details By District\n");
				    		System.out.println("Enter your choice");
					    	int choose = sc.nextInt();sc.nextLine();				    
					    	if(choose==1)
					    	{
					    		System.out.println("Enter Your LoginId");
						    	String loginId = sc.nextLine();	
						    	System.out.println("Enter Your PassWord");
						    	String passWord = sc.nextLine();
						    	if(voterService.loginCheck(loginId,passWord)) 
						    	{
							    	System.out.println("Enter Your VoterId");
							    	String voterId = sc.nextLine();
							    	Voter voter = voterService.viewVoterByVoterId(voterId);
							    	System.out.println("Voter Id      : "+voter.getVoterId());
							    	System.out.println("Voter Name    : "+voter.getVoterName());
							    	System.out.println("Date Of Birth : "+voter.getDob());
							    	System.out.println("Age           : "+voter.getAge());
							    	System.out.println("Address       : "+voter.getAddress());
							    	System.out.println("District      : "+voter.getDistrict());
							    	System.out.println("Mobile Number : "+voter.getMobileNumber());		
							    	System.out.println();
						    	}
						    	else
						    	{
						    		System.out.println("Sorry...Please Check Your LoginId or PassWord..!");
						    	}
					    	}
						    else if(choose==2) 
						    {
						    	System.out.println("Enter The District");
						    	String district = sc.nextLine();
						    	List<Voter> voterList = voterService.viewVoterListByDistrict(district);
						    	System.out.println();
						    	for(Voter voter: voterList) 
						    	{
							    	System.out.println("Voter Id      : "+voter.getVoterId());
							    	System.out.println("Voter Name    : "+voter.getVoterName());
							    	System.out.println("Date Of Birth : "+voter.getDob());
							    	System.out.println("Age           : "+voter.getAge());
							    	System.out.println("Address       : "+voter.getAddress());
							    	System.out.println("District      : "+voter.getDistrict());
							    	System.out.println("Mobile Number : "+voter.getMobileNumber());
							    	System.out.println();				    		
							    }
						    }
				    	}
				    	else if(choice==5)
				    	{
				    		break;
				    	}
				    }
			    }
			    else if(option==2) 
			    {
			    	while (true)
			    	{
				    	System.out.println("1.New Nominee Registration");
				    	System.out.println("2.Update Nominee Address");
				    	System.out.println("3.View Nominee Details");
				    	System.out.println("4.Delete Nominee Registration");
				    	System.out.println("5.Back\n");
				    	System.out.println("Enter your choice");
				    	int choice = sc.nextInt();sc.nextLine();
				    	if(choice==1)
				    	{
				    	
				    		System.out.println("Enter the number of nominee details");
				    		int n = sc.nextInt();sc.nextLine();
				    		String[] nomineeDetails = new String[n];
				    		System.out.println("Enter the Nominee Details");
				    		System.out.println("Example input format");
	                        System.out.println("NomineeID:NomineeName:Contitution:District:Symbol:Address");
	                        for(int i=0; i<n; i++) 
	                        {
	                        	nomineeDetails[i] = sc.nextLine();
	                        }
	                        List<Nominee> nomineeList = new ArrayList<Nominee>();
	                        nomineeList = nomineeService.BuildNomineeList(nomineeDetails);
	                        if(nomineeService.addNomineeList(nomineeList))
	                        {
	                        	System.out.println("Nominee Registration Successfully");
	                        }
	                        else 
	                        {
	                        	System.out.println("Your Registration Failed... Please Try Again!!!");
	                        }
				    	}
				    	else if(choice==2)
				    	{
				    		System.out.println("Enter Your NomineeID");
				    		String nomineeId = sc.nextLine();
				    		System.out.println("Enter Your New Address");
				    		String address = sc.nextLine();
				    		if(nomineeService.updateNomineeDetails(nomineeId, address))
				    		{
				    			System.out.println("Your Address Updated Successfully");
				    		}
				    		else
				    		{
				    			System.out.println("Failed To Update Your Address...Sorry Try Again");
				    		}
				    	}
				    	else if(choice==3)
				    	{
				    		System.out.println("Enter Your NomineeID");
				    		String nomineeId = sc.nextLine();
				    		Nominee nominee = nomineeService.viewNomineeIDdetails(nomineeId);
				    		System.out.println("Nominee Id   : "+nominee.getNomineeID());
				    		System.out.println("Nominee Name : "+nominee.getNomineeName());
				    		System.out.println("Constitution : "+nominee.getConstitution());
				    		System.out.println("District     : "+nominee.getDistrict());
				    		System.out.println("Symbol       : "+nominee.getSymbol());
				    		System.out.println("Address      : "+nominee.getAddress());
				    		System.out.println();
				    	}
				    	else if(choice==4) 
				    	{
				    		System.out.println("Enter the Nominee Id");
				    		String nomineeID = sc.next();
				    		if(nomineeService.deleteNomineeId(nomineeID))
				    		{
				    			System.out.println("Nominee Details  Deleted successfully !!!");
				    		}
				    		else
				    		{
				    			System.out.println("Failed To Delete Nominee Details...Please Try Again");
				    		}
				    	}
				    	else if(choice==5)
				    	{
				    		break;
				    	}
				    }
			    }
			    else if(option==3)
			    {
			    	while (true)
			    	{
				    	System.out.println("1.Vote Polling");
				    	System.out.println("2.Update Nominee Vote Count");
				    	System.out.println("3.View Voter Details"); 
				    	System.out.println("4.Back\n");
				    	System.out.println("Enter your choice");
				    	int choice = sc.nextInt();sc.nextLine();
				    	if(choice==1)
				    	{
				    		System.out.println("Enter the vote Details");
				    		System.out.println("Example input format");
	                        System.out.println("VoterId:NomineeId:VoteDate(DD/MM/YYYY)");                        
	                        String voteDetails = sc.nextLine();
	                        List<Vote> voteList = new ArrayList<Vote>();
				    		voteList = voteService.buildingVoteList(voteDetails);
				    		if(voteService.addVoteList(voteList))
				    		{
				    			System.out.println("Your Votes Added Successfully");
				    		}
				    		else
				    		{
				    			System.out.println("Your Vote Failed  To Add...Please Try Again!!!");
				    		}                                
				    	}
				    	else if(choice==2)
				    	{
				    		System.out.println("Enter the NomineeId");
				    		String nomineeId = sc.next();
				    		int count = voteService.updateNomineeVoteCount(nomineeId);
				    		if(count>0)
				    		{
				    			System.out.println("Vote counted SuccessFully!!!");
				    		}
				    		else
				    		{
				    			System.out.println("Vote Count Failed...Please Try Again!!!");
				    		}			    		
				    	}
				    	else if(choice==3)
				    	{
				    		System.out.println("Enter Your LoginId");
					    	String loginId = sc.nextLine();	
					    	System.out.println("Enter Your PassWord");
					    	String passWord = sc.nextLine();
					    	List<Voter> list = new ArrayList<Voter>();
					    	list= voteService.viewVoterDetails(loginId,passWord);
					    	for(Voter voter: list) 
					    	{
						    	System.out.println("Voter Id      : "+voter.getVoterId());
						    	System.out.println("Voter Name    : "+voter.getVoterName());
						    	System.out.println("Date Of Birth : "+voter.getDob());
						    	System.out.println("Age           : "+voter.getAge());
						    	System.out.println("Address       : "+voter.getAddress());
						    	System.out.println("District      : "+voter.getDistrict());
						    	System.out.println("Mobile Number : "+voter.getMobileNumber());
						    	System.out.println();
					    	}
				    	}	
				    	else if(choice==4)
				    	{
				    		break;
				    	}
				    }
			    }
			    else if(option==4) 
			    {
			    	nomineeService.WinningConstitution();
			    }	
			    else if(option==5) 
			    {
			    	while (true)
			    	{
				    	System.out.println("1.Percentage of Voting in Each District");
				    	System.out.println("2.Party-Wise Voting Count");
				    	System.out.println("3.District-Wise Nominee List");
				    	System.out.println("4.Back\n");
				    	System.out.println("Enter your choice");
				    	int choice = sc.nextInt();sc.nextLine();
				    	if(choice==1)
				    	{
				    		Map<String,Float> list = new HashMap<String,Float>();
				    		list = voteService.votingPercentageByDistrict();
				    		System.out.println("District"+" "+"vote Percentage");
				    		for( Map.Entry<String,Float> x: list.entrySet()) 
				    		{
				    			System.out.println(x.getKey()+" "+x.getValue());
				    		}
				    	}
				    	else if(choice==2)
				    	{
				    		Map<String,Integer> list = new HashMap<String,Integer>();
				    		list = nomineeService.partyWiseVoteCounting();
				    		System.out.println("constitution"+" "+"vote Count");
				    		for( Map.Entry<String,Integer> x: list.entrySet()) 
				    		{
				    			System.out.println(x.getKey()+" "+x.getValue());
				    		}
				    	}
				    	else if(choice==3)
				    	{
				    		List<Nominee> nomineeList = nomineeService.viewNomineeListByDistrict();
				    		for(Nominee nominee: nomineeList) 
				    		{
					    		System.out.println("Nominee Id   : "+nominee.getNomineeID());
					    		System.out.println("Nominee Name : "+nominee.getNomineeName());
					    		System.out.println("Constitution : "+nominee.getConstitution());
					    		System.out.println("District     : "+nominee.getDistrict());
					    		System.out.println("Symbol       : "+nominee.getSymbol());
					    		System.out.println("Address      : "+nominee.getAddress());
					    		System.out.println();
			    	        }
				    	}	
				     	else if(choice==4)
				    	{ 
				    		break;   
				    	}    
				    }
			    }
			    else if(option==6) 
			    {
			    	System.out.println("Thanks for using our application");
			    	break;
			    }	
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
