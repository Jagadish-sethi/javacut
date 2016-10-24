package com.jagadish.concurrent;

import com.jagadish.main.Main;

/**
 * @author jseth3
 * Design a concurrent Counter
 */
public class MyConcurrentCounter {

	private int counter;
	
	public MyConcurrentCounter() {
		counter = 0;
	}
	
	public synchronized void increment() {
		counter++;
	}
	
	public synchronized void decrement() {
		counter--;
	}
	
	public int getValue() {
		return counter;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\n=======Demo showing my concurrent counter=========");
		
		int n = Main.readInteger("Enter no. of threads you want to concurrently increment a counter :");
		Thread[] thread = new Thread[n];
		
		MyConcurrentCounter counter = new MyConcurrentCounter();
		for (int i = 0; i < thread.length; i++) {
			int count = Main.readInteger("Enter no. of times thread no. "+(i+1)+" to increment :");
			thread[i] = new Thread(new MyRunnableWithCounter(counter,count)); 
		}
		
		for (int i = 0; i < thread.length; i++) {
			thread[i].start();
		}
		for (int i = 0; i < thread.length; i++) {
			try {
				thread[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Value of counter after both the threads updated :"+counter.getValue());

	}

}

class MyRunnableWithCounter implements Runnable {
	
	MyConcurrentCounter counter;
	int n;
	
	public MyRunnableWithCounter(MyConcurrentCounter counter, int n) {
		this.counter = counter;
		this.n = n;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < n; i++) {
			System.out.println("Thread id :"+Thread.currentThread().getId()+"\t  "+(i+1)+" th time");
			counter.increment();
			//System.out.println(Thread.currentThread().getId()+"-----"+i);
		}
		
	}
	
}
