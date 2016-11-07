package com.jagadish.concurrent;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



public class MyConcurrentCounterTest {
	
	MyConcurrentCounter counter;
	
	@Before
	public void setUp() {
		counter = new MyConcurrentCounter();
	}

	@Test
	public void test() {
		counter.increment();
		assertEquals(1, counter.getValue());
		counter.decrement();
		assertEquals(0, counter.getValue());	
	}

}
