package com.jagadish.callcentre.domain;

import java.util.ArrayList;
import java.util.List;

public class Call {
	
	private int callId;
	private long callerNumber;
	private String issue;
	private String resolution;
	private CallStatus status;
	private int assignee;
	
	private List<Activity> activities;
	
	public Call(int callId, long callerNumber, String issue) {
		this.callId = callId;
		this.callerNumber = callerNumber;
		this.issue = issue;
		this.activities = new ArrayList<Activity>();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return callId+">: Caller Number : "+callerNumber;
	}

	public long getCallerNumber() {
		return callerNumber;
	}

	public void setCallerNumber(long callerNumber) {
		this.callerNumber = callerNumber;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		Activity activity = new Activity(Field.issue, this.issue, issue);
		this.issue = issue;
		activities.add(activity);
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		Activity activity = new Activity(Field.resolution, this.resolution, resolution);
		this.resolution = resolution;
		activities.add(activity);
	}

	public CallStatus getStatus() {
		return status;
	}

	public void setStatus(CallStatus status) {
		Activity activity = new Activity(Field.status, this.status.toString(), status.toString());
		this.status = status;
		activities.add(activity);
	}

	public int getAssignee() {
		return assignee;
	}

	public void setAssignee(int assignee) {
		Activity activity = new Activity(Field.assignee, ""+this.assignee, ""+assignee);
		this.assignee = assignee;
		activities.add(activity);
	}
	
	

}
