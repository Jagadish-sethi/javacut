package com.jagadish.callcentre.domain;

import java.util.List;

public class Activity {
	
	Field field;
	String fromValue;
	String toValue;
	
	Activity(Field field, String fromValue,String toValue) {
		this.field = field;
		this.fromValue = fromValue;
		this.toValue = toValue;
	}
	

}

enum Field{
	issue,resolution,status,assignee;
}
