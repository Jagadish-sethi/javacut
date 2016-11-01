package com.jagadish.studentManagement;

public class User {
	
	int userId;
	String password;
	UserType user;
	
	public User(UserType user) {
		this.user=user;
	}

}

enum UserType {
	STUDENT,ADMIN;
}