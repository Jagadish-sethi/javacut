package com.jagadish.threadPool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPoolExecutor {

	BlockingQueue<Runnable> myqueue;
	int coreSize;
	List<MyThread> myThreads;

	boolean isShutdown = false;

	public MyThreadPoolExecutor(int coreSize, BlockingQueue<Runnable> myqueue) {
		this.coreSize = coreSize;
		this.myqueue = myqueue;
		myThreads = new ArrayList<MyThread>(coreSize);
	}

	synchronized public void execute(Runnable command) {
		if (!isShutdown) {
			try {
				myqueue.put(command);
				startThread();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("Task was not submitted further since shutdown was invoked");
		}
	}

	private void startThread() {
		if (!myqueue.isEmpty() && myThreads.size() < coreSize) {
			MyThread myThread = new MyThread();
			myThread.start();
			myThreads.add(myThread);
		}
	}

	public void shutdown() {
		isShutdown = true;
		for (Iterator iterator = myThreads.iterator(); iterator.hasNext();) {
			MyThread myThread = (MyThread) iterator.next();
			myThread.stopThread();
		}
	}

	class MyThread extends Thread {
		private boolean isStopped = false;

		public void run() {
			while (!isStopped) {
				try {
					Runnable runnable = myqueue.take();
					runnable.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void stopThread() {
			this.isStopped = true;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockingQueue<Runnable> myqueue = new ArrayBlockingQueue<Runnable>(5);
		MyThreadPoolExecutor executor = new MyThreadPoolExecutor(3, myqueue);

		executor.execute(new MyRunnable(1));
		System.out.println("Submitted Task no. "+1);
		executor.execute(new MyRunnable(2));
		System.out.println("Submitted Task no. "+2);
		executor.execute(new MyRunnable(3));
		System.out.println("Submitted Task no. "+3);
		executor.execute(new MyRunnable(4));
		System.out.println("Submitted Task no. "+4);
		executor.execute(new MyRunnable(5));
		System.out.println("Submitted Task no. "+5);
		executor.execute(new MyRunnable(6));
		System.out.println("Submitted Task no. "+6);
		executor.execute(new MyRunnable(7));
		System.out.println("Submitted Task no. "+7);
		executor.execute(new MyRunnable(8));
		System.out.println("Submitted Task no. "+8);
		executor.execute(new MyRunnable(9));
		System.out.println("Submitted Task no. "+9);
		executor.execute(new MyRunnable(10));
		System.out.println("Submitted Task no. "+10);
		executor.shutdown();
		executor.execute(new MyRunnable(11));
		System.out.println("Submitted Task no. "+11);
		
		
	}

}


class MyRunnable implements Runnable{
	int id;
	public MyRunnable(int id) {
		this.id = id;
	}
	public void run() {
		
		for (int i = 0; i < 20; i++) {
			System.out.println(Thread.currentThread().getId()+"\t-----\t"+id+"\t-----\t"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
