package com.jagadish.concurrent;

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
		MyCowntDownLatch latch = new MyCowntDownLatch(3);

		new Thread(new MyRunnableWithLatchForWait(latch)).start();
		new Thread(new MyRunnableWithLatchForWait(latch)).start();
		// Pause
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new MyRunnableWithLatchForSteps(latch, 2)).start();
		new Thread(new MyRunnableWithLatchForSteps(latch, 1)).start();
	}
	
	/**
	 * Implement cyclicBarrier functionality using user defined cyclicBarrier
	 */
	public static void cyclicBarrierDemo() {
		// CyclicBarrier Demo
		MyCyclicBarrier barrier = new MyCyclicBarrier(3);
		new Thread(new MyRunnableWithBarrier(barrier)).start();
		new Thread(new MyRunnableWithBarrier(barrier)).start();
		new Thread(new MyRunnableWithBarrier(barrier)).start();
		// Pause
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new MyRunnableWithBarrier(barrier)).start();
		new Thread(new MyRunnableWithBarrier(barrier)).start();
		new Thread(new MyRunnableWithBarrier(barrier)).start();
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
			System.out.println("Before latch.countDown : " + Thread.currentThread().getId());
			latch.countDown();
			System.out.println("After latch.countDown : " + Thread.currentThread().getId());
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
