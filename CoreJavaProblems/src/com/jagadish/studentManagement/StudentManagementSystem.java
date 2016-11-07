package com.jagadish.studentManagement;

import java.util.HashMap;
import java.util.Map;

import com.jagadish.chess.domain.ChessGame;
import com.jagadish.concurrent.MyConcurrentCounter;
import com.jagadish.concurrent.MyConcurrentDemo;
import com.jagadish.concurrent.MyConcurrentHashMap;
import com.jagadish.findlargestnumbers.FindLargest;
import com.jagadish.genericObjectPool.MyPool;
import com.jagadish.main.Main;
import com.jagadish.mergesort.MergeSort;
import com.jagadish.producerConsumer.NProducerConsumer;
import com.jagadish.producerConsumer.ProducerConsumer;
import com.jagadish.sortMapbyValues.SortHashMap;
import com.jagadish.threadPool.MyThreadPoolExecutor;

public class StudentManagementSystem {
	
	private Map<Long,User> map;
	long studentRollnoIncrementer = 10000;
	
	public StudentManagementSystem() {
		map = new HashMap();
		initialise();
	}
	
	
	public void initialise() {
		map.put(101l, new Employee(101, "Jagan"));
		map.put(102l, new Employee(102, "Jag"));
		
	}
	
	private void addStudent() {
		long rollNo = studentRollnoIncrementer++;
		String name = Main.readString("Enter the Student name");
		int study = Main.readInteger("Enter course code ");
		int branch = Main.readInteger("Enter branch code BTech(1), MBA(2), MCA(3)");
		int year = Main.readInteger("Enter year of joining ");
		
		Student std = new Student(rollNo, name);
		std.setStudy(study);
		std.setBranch(branch);
		std.setYear(year);
		
		map.put(std.getRollNo(), std);
		System.out.println("Student details added and roll no. genereated is "+rollNo);
	}
	
	/*private void updateStudent() {
		
		long rollNo = Main.readLong("Enter roll no");
		Student std = (Student)map.get(rollNo);
		if(std ==null){
			System.out.println("Wrong roll no. entered");
		}
		int ch = Main.readInteger("Enter 1 to change name, ")
		
	}*/
	
	private void viewStudent() {
		long rollNo = Main.readLong("Enter roll no");
		Student std = (Student)map.get(rollNo);
		System.out.println(std);
	}
	
	
	public static void main(String[] args) {
		
		int option = 0;
		
		StudentManagementSystem stmgnt = new StudentManagementSystem();
		while(option!=15){
			Main.readString("Press Enter key to Continue:");
			System.out.println("\n======================Problem Statment====================================\n");
			System.out.println("1. To see student details");
			System.out.println("2. To add Student details");
			System.out.println("15. To Quit");
			option = Main.readInteger("Please enter the corresponding number to perform corresponding operation : ");
			
			switch (option) {
			case 1: stmgnt.viewStudent();	break;
			case 2: stmgnt.addStudent();	break;
			default:
				break;
			}
		}
		
		
	}

}
