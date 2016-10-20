package com.jagadish.concurrent;

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
		MyConcurrentCounter counter = new MyConcurrentCounter();
		new Thread(new MyRunnableWithCounter(counter)).start();
		new Thread(new MyRunnableWithCounter(counter)).start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(counter.getValue());

	}

}

class MyRunnableWithCounter implements Runnable {
	
	MyConcurrentCounter counter;
	
	public MyRunnableWithCounter(MyConcurrentCounter counter) {
		this.counter = counter;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 100; i++) {
			counter.increment();
			//System.out.println(Thread.currentThread().getId()+"-----"+i);
		}
		
	}
	
}
