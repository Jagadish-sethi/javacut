package com.jagadish.mergesort;

import java.util.Arrays;

/**
 * @author jseth3
 * Implement Merge Sort
 */
public class MergeSort {
	
	public static void mergeSort(int [] arr, int start, int end){
		if(start>=end){
			return;
		}
		int mid = start + (end-start)/2;
		mergeSort(arr, start, mid);
		mergeSort(arr, mid+1, end);
		merge(arr,start,mid,end);
	}
	
	private static void merge(int[] arr, int start, int mid, int end) {
		int l_left = mid - start + 1;
		int l_right = end - mid;

		int[] left = new int[l_left];
		int[] right = new int[l_right];

		for (int i = 0; i < l_left; i++) {
			left[i] = arr[start + i];
		}
		for (int i = 0; i < l_right; i++) {
			right[i] = arr[mid + i + 1];
		}
		int i = 0, j = 0, k = start;
		while (i < l_left && j < l_right) {
			if (left[i] < right[j]) {
				arr[k++] = left[i++];
			} else {
				arr[k++] = right[j++];
			}
		}

		while (i < l_left) {
			arr[k++] = left[i++];
		}
		while (j < l_right) {
			arr[k++] = right[j++];

		}
	}

	public static void main(String[] args) {
		int[] arr = {12,32,89,56,78,45,12,56};
		mergeSort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}

}
