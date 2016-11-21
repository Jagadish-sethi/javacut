package com.jagadish.testapi;

import java.util.LinkedList;
import java.util.Queue;

public class StringCombination {
	
	
	public static void main(String[] args) {
		
		String str = "abcd";
		System.out.println("String permutation with recursion");
		stringPermutationR(str,0,str.length()-1);
		System.out.println("String permutation without recursion");
		stringPermutationI(str);
		
	}
	
	private static void stringPermutationI(String value) {
		// TODO Auto-generated method stub
		Queue<MyString> queue = new LinkedList<>();
		queue.add(new MyString(value, 0));

		while (!queue.isEmpty()) {
			MyString tempValue = queue.poll();
			if (tempValue.indexFixed == value.length() - 1) {
				System.out.println(tempValue.value);
			} else {
				for (int i = tempValue.indexFixed; i < value.length(); i++) {
					String temp = swapCharacter(tempValue.value,
							tempValue.indexFixed, i);
					queue.add(new MyString(temp, tempValue.indexFixed + 1));
				}
			}
		}

	}
	
	static class MyString{
		String value;
		int indexFixed;
		public MyString(String value, int indexFixed) {
			this.value = value;
			this.indexFixed = indexFixed;
		}
		
	}

	public static void stringPermutationR(String value, int start, int end) {
		if (start == end) {
			System.out.println(value);
		} else {
			for (int i = start; i <= end; i++) {
				value = swapCharacter(value, start, i);
				stringPermutationR(value, start + 1, end);
				value = swapCharacter(value, start, i);
			}
		}

	}

	private static String swapCharacter(String value, int i, int j) {
		// TODO Auto-generated method stub
		if(i==j){
			return value;
		}
		String s1 = value.substring(0, i);
		//System.out.println(s1);
		String s2 = value.substring(i+1, j);
		//System.out.println(s2);
		String s3 = value.substring(j+1);
		//System.out.println(s3);
		return s1+value.charAt(j)+s2+value.charAt(i)+s3;
	}

	
	
}
