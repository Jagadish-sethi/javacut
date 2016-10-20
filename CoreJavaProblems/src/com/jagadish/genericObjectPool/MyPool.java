package com.jagadish.genericObjectPool;

import java.util.ArrayList;
import java.util.List;

public class MyPool<T> {
	
	List<T> objects;
	boolean[] isUsed;
	int usedCount;
	int size;
	
	public MyPool(int size) {
		objects = new ArrayList(size);
		isUsed = new boolean[size];
		usedCount = 0;
		this.size = size;
	}
	
	public boolean addObject(T object){
		if(objects.size()<size){
			objects.add(object);
			return true;
		}else{
			return false;
		}
	}

	public T borrowObject() {
		T object = null;
		if(usedCount<objects.size()) {
			for (int i = 0; i < objects.size(); i++) {
				if(!isUsed[i]){
					object = objects.get(i);
					isUsed[i]=true;
					usedCount++;
					break;
				}
			}
		}
		return object;
	}
	
	public boolean returnObject(T obj) {
		if(usedCount>0) {
			for (int i = 0; i < isUsed.length; i++) {
				if(objects.get(i)==obj){
					isUsed[i]=false;
					usedCount--;
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyPool<String> stringPools = new MyPool<String>(5);
		
		System.out.println(stringPools.addObject("Jagan"));
		System.out.println(stringPools.addObject("Deban"));
		System.out.println(stringPools.addObject("Kisan"));
		
		String obj1 = stringPools.borrowObject();
		String obj2 = stringPools.borrowObject();
		String obj3 = stringPools.borrowObject();
		String obj4 = stringPools.borrowObject();
		
		System.out.println(obj1);
		System.out.println(obj2);
		System.out.println(obj3);
		System.out.println(obj4);
		
		System.out.println(stringPools.addObject("Anshik"));
		System.out.println(stringPools.addObject("Kamlesh"));
		System.out.println(stringPools.addObject("Suresh"));
		
		String obj5 = stringPools.borrowObject();
		String obj6 = stringPools.borrowObject();
		String obj7 = stringPools.borrowObject();
		
		System.out.println(obj5);
		System.out.println(obj6);
		System.out.println(obj7);
		
		stringPools.returnObject(obj2);
		stringPools.returnObject(obj5);
		
		String obj8 = stringPools.borrowObject();
		String obj9 = stringPools.borrowObject();
		
		System.out.println(obj8);
		System.out.println(obj9);
		

	}

}
