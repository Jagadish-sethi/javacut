package com.jagadish.concurrent;

import java.util.Iterator;

import com.jagadish.main.Main;

public class MyConcurrentDemo {

	public static void main(String[] args) {
		
		cyclicBarrierDemo();
		// Pause
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cowntDownLatchDemo();
		
	}
	
	/**
	 * Implement cowntDownLatch functionality using user defined cowntDownLatch
	 */
	public static void cowntDownLatchDemo() {
		// CountDownLatch demo
		int n = Main.readInteger("Enter the count down count :");
		MyCowntDownLatch latch = new MyCowntDownLatch(n);
		
		new Thread(new MyRunnableWithLatchForWait(latch)).start();
		Thread t2 =new Thread(new MyRunnableWithLatchForWait(latch));
		t2.start();
		// Pause
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new MyRunnableWithLatchForSteps(latch, n/2)).start();
		new Thread(new MyRunnableWithLatchForSteps(latch, n/2+1)).start();
		
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Implement cyclicBarrier functionality using user defined cyclicBarrier
	 */
	public static void cyclicBarrierDemo() {
		// CyclicBarrier Demo
		int n = Main.readInteger("Enter the barrier count :");
		MyCyclicBarrier barrier = new MyCyclicBarrier(n);
		
		for (int i = 0; i < n; i++) {
			new Thread(new MyRunnableWithBarrier(barrier)).start();
		}
	}

}



class MyCyclicBarrier {
	
	int barrierCount;
	int waitingThreadCount;
	public MyCyclicBarrier(int barrierCount) {
		this.barrierCount = barrierCount;
		this.waitingThreadCount = 0;
	}
	
	public void await() {
		synchronized (this) {
			waitingThreadCount++;
			if(waitingThreadCount<barrierCount){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				waitingThreadCount = 0;
				this.notifyAll();
			}
		}
	}
	
}

class MyCowntDownLatch {
	
	int latchCount;
	public MyCowntDownLatch(int latchCount) {
		this.latchCount = latchCount;
	}
	
	public void await() {
		synchronized (this) {
			if(latchCount!=0){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void countDown() {
		synchronized (this) {
			latchCount--;
			if (latchCount == 0) {
				this.notifyAll();
			}
		}
	}
	
}

class MyRunnableWithLatchForWait implements Runnable {

	MyCowntDownLatch latch;
	public MyRunnableWithLatchForWait(MyCowntDownLatch latch) {
		this.latch = latch;
	}
	
	public void run() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Before latch.await : "+Thread.currentThread().getId());
		latch.await();
		System.out.println("After latch.await : "+Thread.currentThread().getId());
	}
	
}

class MyRunnableWithLatchForSteps implements Runnable {

	MyCowntDownLatch latch;
	int stepCount;
	public MyRunnableWithLatchForSteps(MyCowntDownLatch latch, int stepCount) {
		this.latch = latch;
		this.stepCount = stepCount;
	}
	
	public void run() {
		for (int i = 0; i < stepCount; i++) {

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i+"th Before latch.countDown Thread id : " + Thread.currentThread().getId());
			latch.countDown();
			System.out.println(i+"th After latch.countDown Thread id : " + Thread.currentThread().getId());
		}
	}
	
}


class MyRunnableWithBarrier implements Runnable {

	MyCyclicBarrier barrier;
	public MyRunnableWithBarrier(MyCyclicBarrier barrier) {
		this.barrier = barrier;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Before barrier : "+Thread.currentThread().getId());
		barrier.await();
		System.out.println("After barrier : "+Thread.currentThread().getId());
	}
	
}
