package com.jagadish.studentManagement;

public class Employee extends User {

	private long empNo;
	private String name;
	
	public Employee(long empNo, String name) {
		super(UserType.ADMIN,empNo,name+empNo%1000);
		this.empNo = empNo;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getEmpNo() {
		return empNo;
	}

	@Override
	public String toString() {
		return "Employee Details :"
				+ "\nempNo=" + empNo + "\nname=" + name;
	}
	
	
	
}
