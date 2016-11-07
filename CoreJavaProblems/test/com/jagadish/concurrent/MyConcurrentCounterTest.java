package com.jagadish.concurrent;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyConcurrentCounterTest {
	
	MyConcurrentCounter counter;
	
	@Before
	public void setUp() {
		counter = new MyConcurrentCounter();
	}

	@Test
	public void test() {
		counter.increment();
		Assert.assertEquals(1, counter.getValue());
		counter.decrement();
		Assert.assertEquals(0, counter.getValue());	
	}

}
