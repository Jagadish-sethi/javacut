package com.jagadish.testapi;

public class MemoryLeak {
	
	
	public static void main(String[] args) {
		
		int i=0;
		String str = "original";
		//str.notifyAll();
		while(true){
			new String("hello");
			//new MyString(str, new String("hello"));
			System.out.println(""+(i++));
		}
		
		
		
	}

}

class MyString {
	String original;
	String newstring;
	public MyString(String original,String newstring) {
		this.original = original;
		this.newstring = newstring;
	}
	
}