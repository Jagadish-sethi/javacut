package com.jagadish.sortMapbyValues;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.jagadish.concurrent.MyConcurrentCounter;

public class SortHashMapTest {
	
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
	public void testSortMapByValues(){
		Map<Integer,String> sortedMap = SortHashMap.sortMapByValues(map);
		Iterator<Entry<Integer,String>> it = sortedMap.entrySet().iterator();
		Entry<Integer,String> entry =it.next();
		assertEquals("Anshik", entry.getValue());
	}

}
