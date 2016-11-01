package com.jagadish.studentManagement;

public class Student extends User{
	
	int rollNo;
	String name;
	String branch;
	int year;
	Study study;
	
	public Student(int rollNo, String name) {
		super(UserType.STUDENT);
		this.rollNo = rollNo;
		this.name = name;
	}

}


enum Study {
	BTech, MBA, MCA;
}