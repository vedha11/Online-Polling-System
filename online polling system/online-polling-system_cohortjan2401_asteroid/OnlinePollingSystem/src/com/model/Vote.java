package com.model;

import java.util.Date;

public class Vote 
{	
	private String voteId;
	private String voterId;
	private String nomineeId;
	private Date votedDate;
	
	public Vote() 
	{
		super();
	}

	public Vote(String voteId, String voterId, String nomineeId, Date votedDate) 
	{
		super();
		this.voteId = voteId;
		this.voterId = voterId;
		this.nomineeId = nomineeId;
		this.votedDate = votedDate;
	}
	
	public String getVoteId() {
		return voteId;
	}
	public String getVoterId() {
		return voterId;
	}
	public String getNomineeId() {
		return nomineeId;
	}
	public Date getVotedDate() {
		return votedDate;
	}
	
	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public void setNomineeId(String nomineeId) {
		this.nomineeId = nomineeId;
	}
	public void setVotedDate(Date votedDate) {
		this.votedDate = votedDate;
	}
}
