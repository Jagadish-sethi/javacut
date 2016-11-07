package com.jagadish.studentManagement;

public class User {
	
	long userId;
	String password;
	UserType user;
	
	public User(UserType user, long userId, String password) {
		this.user=user;
		this.userId=userId;
		this.password=password;
	}

}

enum UserType {
	STUDENT,ADMIN;
}