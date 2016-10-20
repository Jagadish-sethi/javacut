package com.jagadish.producerConsumer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author jseth3
 * Implement Producer Consumer problem using blocking queue & using wait and notify
 */
public class ProducerConsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// "Demonstrate producer consumer problem using blocking Queue"
		PCUsingBlockingQueue();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// "Demonstrate producer consumer problem using wait notify"
		PCUsingWaitNotify();

	}

	private static void PCUsingWaitNotify() {
		Queue<Integer> queue = new LinkedList();
		MyWNProducer myWNProducer = new MyWNProducer(queue, 5);
		MyWNConsumer myWNConsumer = new MyWNConsumer(queue, 5);

		Thread producer = new Thread(myWNProducer);
		Thread consumer = new Thread(myWNConsumer);

		consumer.start();
		producer.start();

		try {
			Thread.sleep(200);
			myWNProducer.stopProducing();
			myWNConsumer.stopConsuming();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void PCUsingBlockingQueue() {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue(5);
		MyBQProducer myBQProducer = new MyBQProducer(queue);
		MyBQConsumer myBQConsumer = new MyBQConsumer(queue);

		Thread producer = new Thread(myBQProducer);
		Thread consumer = new Thread(myBQConsumer);

		consumer.start();
		producer.start();

		try {
			Thread.sleep(200);
			myBQProducer.stopProducing();
			myBQConsumer.stopConsuming();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class MyWNProducer implements Runnable{
	
	Queue<Integer> queue;
	boolean isProducing = true;
	int maxsize;
	
	public MyWNProducer(Queue queue, int maxsize) {
		this.queue = queue;
		this.maxsize = maxsize;
	}

	@Override
	public void run() {
		while(isProducing){
			synchronized (queue) {
				try {
				while(queue.size()==maxsize){
					queue.wait();
				}
				int value = (int)(Math.random()*100);
				System.out.println(Thread.currentThread().getId()+" WNProduced \t"+value);
				queue.add(value);
				Thread.sleep(20);
				queue.notifyAll();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}
	
	public void stopProducing(){
		isProducing = false;
	}
}

class MyWNConsumer implements Runnable{
	
	Queue<Integer> queue;
	boolean isConsuming = true;
	int maxsize;
	
	public MyWNConsumer(Queue queue, int maxsize) {
		this.queue = queue;
		this.maxsize = maxsize;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isConsuming || !queue.isEmpty()){
			try {
				synchronized (queue) {
					while(queue.isEmpty()){
						queue.wait();
					}
				int value = queue.poll();
				System.out.println(Thread.currentThread().getId()+" WNConsumed \t\t\t"+value);
				Thread.sleep(40);
				queue.notifyAll();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void stopConsuming(){
		isConsuming = false;
	}
}

class MyBQProducer implements Runnable{
	
	BlockingQueue<Integer> queue;
	boolean isProducing = true;
	
	public MyBQProducer(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while(isProducing){
			int value = (int)(Math.random()*100);
			try {
				System.out.println(Thread.currentThread().getId()+" BQProduced \t"+value);
				queue.put(value);
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stopProducing(){
		isProducing = false;
	}
}

class MyBQConsumer implements Runnable{
	
	BlockingQueue<Integer> queue;
	boolean isConsuming = true;
	
	public MyBQConsumer(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isConsuming || !queue.isEmpty()){
			try {
				int value = queue.take();
				System.out.println(Thread.currentThread().getId()+" BQConsumed \t\t\t"+value);
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void stopConsuming(){
		isConsuming = false;
	}
}