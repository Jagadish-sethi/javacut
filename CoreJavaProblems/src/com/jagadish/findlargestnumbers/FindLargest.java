package com.jagadish.findlargestnumbers;

import java.util.Arrays;

/**
 * @author jseth3
 * Write program to find largest and second largest element in an unsorted array
 */
public class FindLargest {
	
	public static void findTwolargestNumber(int[] arr){
		
		int max = Integer.MIN_VALUE;
		int secondMax = Integer.MIN_VALUE;
		
		for (int i = 0; i < arr.length; i++) {
			if(max<arr[i]){
				secondMax = max;
				max=arr[i];
			}else if(secondMax<arr[i]){
				secondMax = arr[i];
			}
		}
		
		System.out.println("Largest element in the array is : "+ max);
		System.out.println("Second Largest element in the array is : "+ secondMax);
		
		
	}
	
	public static void main(String[] args) {
		int[] arr = {45,12,56,32,89,56,78};
		System.out.println(Arrays.toString(arr));
		findTwolargestNumber(arr);
	}

}