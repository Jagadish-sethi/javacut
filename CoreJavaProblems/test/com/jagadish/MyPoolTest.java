package com.jagadish;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jagadish.genericObjectPool.MyPool;

public class MyPoolTest {

	@Test
	public void test() {
		MyPool<String> stringPools = new MyPool<String>(5);
		
		System.out.println("Pool of size 5 is initialised");
		
		System.out.println("3 new objects are added");
		assertTrue(stringPools.addObject("Jagan"));
		assertTrue(stringPools.addObject("Deban"));
		assertTrue(stringPools.addObject("Kisan"));
		
		System.out.println("Trying to borrow 4 objects");
		assertEquals("Jagan",stringPools.borrowObject());
		assertEquals("Deban",stringPools.borrowObject());
		assertEquals("Kisan",stringPools.borrowObject());
		assertNull(stringPools.borrowObject());
		
		
		System.out.println("Trying to add 3 new objects");
		assertTrue(stringPools.addObject("Anshik"));
		assertTrue(stringPools.addObject("Kamlesh"));
		assertFalse(stringPools.addObject("Suresh"));
		
		assertEquals("Anshik",stringPools.borrowObject());
		assertEquals("Kamlesh",stringPools.borrowObject());
		assertNull(stringPools.borrowObject());
		
		
		
		System.out.println("Returning 2nd and 5th object");
		assertTrue(stringPools.returnObject("Deban"));
		assertTrue(stringPools.returnObject("Kamlesh"));
		
		System.out.println("Trying to borrow 2 objects");
		assertEquals("Deban",stringPools.borrowObject());
		assertEquals("Kamlesh",stringPools.borrowObject());
		
	}

}
