package com.jagadish.callcentre.domain;

import java.util.List;

public class Activity {
	
	int activityId;
	Call call;
	Employee assignee;
	
	Activity(Call call, Employee employee) {
		this.call = call;
		this.assignee = employee;
	}
	

}
