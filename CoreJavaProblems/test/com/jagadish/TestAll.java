package com.jagadish;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.jagadish.findlargestnumbers.FindLargest;
import com.jagadish.mergesort.MergeSort;
import com.jagadish.sortMapbyValues.SortHashMap;

public class TestAll {

	Map<Integer,String> map;
	
	@Before
	public void setUp() {
		map = new HashMap();
		map.put(1, "Jagan");
		map.put(2, "Deban");
		map.put(3, "Kisan");
		map.put(4, "Kamlesh");
		map.put(5, "Vimlesh");
		map.put(6, "Anshik");
		
		
	}
	
	@Test
	public void testMergeSort() {
		int[] arr = {6,2,4,3,8,1,5};
		MergeSort.mergeSort(arr, 0, arr.length-1);
		assertEquals(1, arr[0]);
	}
	
	@Test
	public void testFindTwolargestNumber() {
		int[] arr = {6,2,4,3,8,1,5};
		int []values=FindLargest.findTwolargestNumber(arr);
		assertEquals(values[0], 8);
		assertEquals(values[1], 6);
		
	}
	
	@Test
	public void testSortMapByValues(){
		Map<Integer,String> sortedMap = SortHashMap.sortMapByValues(map);
		Iterator<Entry<Integer,String>> it = sortedMap.entrySet().iterator();
		Entry<Integer,String> entry =it.next();
		assertEquals("Anshik", entry.getValue());
	}
}
