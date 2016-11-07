/**
 * 
 */
package com.jagadish.sortMapbyValues;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author jseth3
 * Sort hash map by values
 */
public class SortHashMap {

	public static void main(String[] args) {
		
		Map<Integer,String> map = new HashMap();
		map.put(1, "Jagan");
		map.put(2, "Deban");
		map.put(3, "Kisan");
		map.put(4, "Kamlesh");
		map.put(5, "Vimlesh");
		map.put(6, "Anshik");
		System.out.println("Initialised map is");
		System.out.println(map);
		
		Map<Integer,String> sortedMap = sortMapByValues(map);
		System.out.println("Sorted map by values is");
		System.out.println(sortedMap);
	}

	public static Map<Integer, String> sortMapByValues(Map<Integer, String> map) {
		Map<Integer, String> sortedMap = new LinkedHashMap<Integer, String>();
		
		List<Entry<Integer,String>> list = new ArrayList(map.entrySet());
		
		Collections.sort(list, new Comparator<Entry<Integer,String>>() {

			@Override
			public int compare(Entry<Integer, String> o1,
					Entry<Integer, String> o2) {
				// TODO Auto-generated method stub
				return o1.getValue().compareTo(o2.getValue());
			}
			
		});
		
		for (Entry<Integer, String> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
		
	}

}
