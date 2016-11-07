package com.jagadish.mergesort;

import static org.junit.Assert.*;

import org.junit.Test;

public class MergeSortTest {

	@Test
	public void test() {
		int[] arr = {6,2,4,3,8,1,5};
		MergeSort.mergeSort(arr, 0, arr.length-1);
		assertEquals(1, arr[0]);
	}

}
