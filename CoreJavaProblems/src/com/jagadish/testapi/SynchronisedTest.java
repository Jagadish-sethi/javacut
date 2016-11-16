package com.jagadish.testapi;

import junit.framework.Test;

public class SynchronisedTest {
	
	static synchronized void m1(){
		System.out.println("m1 called");
	}
	synchronized void m2(){
		System.out.println("m2 called");
	}
	static synchronized void m3(){
		System.out.println("m3 called");
	}
	synchronized void m4(){
		System.out.println("m4 called");
	}
	
	public static void main(String[] args) {
		final SynchronisedTest obj = new SynchronisedTest();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (Test.class) {
					m1();
					obj.m2();
					m3();
					obj.m4();

				}

			}
		}).start();
		
		
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				
				synchronized (obj) {
					
					m3();
					obj.m4();

				}

			}
		}).start();
		
		

		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (Test.class) {
					m1();
					obj.m2();

				}

			}
		}).start();
		
		

	}

}
