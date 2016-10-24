package com.jagadish.producerConsumer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author jseth3
 * How do you implement producer consumer   problem where there is ten producers and ten consumers.
 */
public class NProducerConsumer {

	public static void main(String[] args) {

		BlockingQueue<Integer> queue = new ArrayBlockingQueue(5);
		List<MyBQProducer> myBQProducers = new ArrayList(10);
		List<MyBQConsumer> myBQConsumers = new ArrayList(10);
		
		for (int i = 0; i < 10; i++) {
			myBQProducers.add(new MyBQProducer(queue));
			myBQConsumers.add(new MyBQConsumer(queue));
		}
		
		for (int i = 0; i < 10; i++) {
			new Thread(myBQProducers.get(i)).start();
			new Thread(myBQConsumers.get(i)).start();
		}
		
		try {
			Thread.sleep(200);
			for (int i = 0; i < 10; i++) {
				myBQProducers.get(i).stopProducing();
				myBQConsumers.get(i).stopConsuming();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	
}
