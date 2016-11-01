package com.jagadish.studentManagement;

public class Employee extends User {

	int empNo;
	String name;
	
	public Employee(int empNo, String name) {
		super(UserType.ADMIN);
		this.empNo = empNo;
		this.name = name;
	}
}
