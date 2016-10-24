package com.jagadish.callcentre.domain;

public class Employee {
	
	private int employeeId;
	private String name;
	private Level position;
	private int managerId;
	
	public Employee(int employeeId,String name,Level position,int managerId) {
		this.employeeId = employeeId;
		this.name = name;
		this.position = position;
		this.managerId = managerId;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public Level getPosition() {
		return position;
	}
	public void setPosition(Level position) {
		this.position = position;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
