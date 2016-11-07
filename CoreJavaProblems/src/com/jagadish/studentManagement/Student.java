package com.jagadish.studentManagement;

public class Student extends User{
	
	private long rollNo;
	private String name;
	private Branch branch;
	private int year;
	private Study study;
	
	public Student(long rollNo, String name) {
		super(UserType.STUDENT,rollNo,name+rollNo%1000);
		this.rollNo = rollNo;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	public void setBranch(int branch) {
		switch (branch) {
		case 1: this.branch = Branch.CSE; break;
		case 2: this.branch = Branch.ECE; break;
		case 3: this.branch = Branch.IT; break;
		}
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}
	
	public void setStudy(int study) {
		
		switch (study) {
		case 1: this.study = Study.BTech; break;
		case 2: this.study = Study.MBA; break;
		case 3: this.study = Study.MCA; break;
		}
		
	}

	public long getRollNo() {
		return rollNo;
	}

	@Override
	public String toString() {
		return "Student Details : \nrollNo=" + rollNo + "\nname=" + name + "\nbranch="
				+ branch + "\nyear=" + year + "\nstudy=" + study;
	}
	
	

}


enum Study {
	BTech(1), MBA(2), MCA(3);
	int value;
	Study(int i){
		value=i;
	}
}

enum Branch {
	IT("Information Technology",1), CSE("Computer Sciencce",2), ECE("Electronic Communication",3);
	private String name;
	private int value;
	
	private Branch(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
}