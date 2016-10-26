package com.jagadish.callcentre.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.jagadish.main.Main;

public class Callcenter {
	
	Queue<Call> calls;
	List<Call> closedCalls;
	Map<Integer,Employee> employees;
	Map<Integer,Call> assigneeMap;
	
	public Callcenter() {
		calls = new LinkedList();
		closedCalls = new ArrayList<Call>();
		employees = new HashMap<Integer, Employee>();
		initialiseDummyData();
		assigneeMap = new HashMap<Integer, Call>();
		
	}
	
	private void initialiseDummyData() {
		employees.put(1, new Employee(1, "Jagan", Level.PRODUCT_MANAGER, 0));
		employees.put(2, new Employee(2, "Deban", Level.TECHNICAL_LEAD, 1));
		employees.put(3, new Employee(3, "Kisan", Level.TECHNICAL_LEAD, 1));
		employees.put(4, new Employee(4, "JJJ", Level.FRESHER, 2));
		employees.put(5, new Employee(5, "AAA", Level.FRESHER, 2));
		employees.put(6, new Employee(6, "BBB", Level.FRESHER, 2));
		employees.put(7, new Employee(7, "CCC", Level.FRESHER, 3));
		employees.put(8, new Employee(8, "DDD", Level.FRESHER, 3));
		employees.put(9, new Employee(9, "KKK", Level.FRESHER, 3));
		
		calls.add(new Call(1, 7894561230l, "Call not connecting1"));
		calls.add(new Call(2, 7894561230l, "Call dfgdfgnot connecting2"));
		calls.add(new Call(3, 7894561230l, "Call not cofdgfdnnecting3"));
		calls.add(new Call(4, 7894561230l, "Call ndfgdfot connecting3"));
		calls.add(new Call(5, 7894561230l, "Call not dfgdfconnecting3"));
		calls.add(new Call(6, 7894561230l, "Call not condfgnecting3"));
		calls.add(new Call(7, 7894561230l, "Call not connecdfgting3"));
		calls.add(new Call(8, 7894561230l, "Call not connfdgecting3"));
		
		
	}
	
	public void raiseCall(){
		long callerNumber = Main.readLong("Enter the mobile number of the caller");
		String issue = Main.readString("Enter the issue");
		calls.add(new Call(calls.size()+1,callerNumber,issue));
	}
	
	public void assignCall(Employee emp, Call call){
		if(call.getAssignee()!=0){
			assigneeMap.remove(call.getAssignee());
		}
		call.setAssignee(emp.getEmployeeId());
		assigneeMap.put(emp.getEmployeeId(),call);
	}

	public static void main(String[] args) {
		
		
		
		
		
	}

}
