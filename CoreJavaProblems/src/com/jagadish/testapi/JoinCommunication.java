package com.jagadish.testapi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class JoinCommunication {

	public static void main(String[] args) {
		AtomicInteger ai;

		System.out.println("================================================");
		System.out.println("Producer consumer using join/interrupt\n\n");
		Queue<Integer> queue = new LinkedList();
		MyWNProducer myWNProducer = new MyWNProducer(queue, 5);
		MyWNConsumer myWNConsumer = new MyWNConsumer(queue, 5);

		myWNProducer.consumer = myWNConsumer;
		myWNConsumer.producer = myWNProducer;

		
		myWNConsumer.start();
		myWNProducer.start();

		try {
			Thread.sleep(1000);
			myWNProducer.stopProducing();
			myWNConsumer.stopConsuming();
			myWNConsumer.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
	}

}

class MyWNProducer extends Thread {

	Queue<Integer> queue;
	boolean isProducing = true;
	int maxsize;
	MyWNConsumer consumer;

	public MyWNProducer(Queue queue, int maxsize) {
		this.queue = queue;
		this.maxsize = maxsize;
	}

	@Override
	public void run() {
		while (isProducing) {
			try {
				if(queue.size() == maxsize) {
					consumer.join();
				}
				int value = (int) (Math.random() * 100);
				System.out.println("thread id : "
						+ Thread.currentThread().getId() + " WNProduced \t"
						+ value);
				queue.add(value);
				Thread.sleep(20);
				consumer.interrupt();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			

		}
	}

	public void stopProducing() {
		isProducing = false;
	}
}

class MyWNConsumer extends Thread {

	Queue<Integer> queue;
	boolean isConsuming = true;
	int maxsize;
	MyWNProducer producer;

	public MyWNConsumer(Queue queue, int maxsize) {
		this.queue = queue;
		this.maxsize = maxsize;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isConsuming || !queue.isEmpty()) {
			try {
				if(queue.isEmpty()) {
					producer.join();

				}
				int value = queue.poll();
				producer.interrupt();
				System.out.println("thread id : "
						+ Thread.currentThread().getId() + " WNConsumed \t\t\t"
						+ value);
				Thread.sleep(1);

			} catch (Exception e) {
				
			//	 e.printStackTrace();
			}
		}

	}

	public void stopConsuming() {
		isConsuming = false;
	}
}
