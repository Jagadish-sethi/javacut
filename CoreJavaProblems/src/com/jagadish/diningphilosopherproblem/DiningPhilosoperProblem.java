package com.jagadish.diningphilosopherproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiningPhilosoperProblem {
	
	static final int TOTAL =3;
	
	public static void main(String[] args) {
		
		System.out.println(-1%5);
		
		List<Fork> forks = new ArrayList<>();
		for (int i = 0; i < DiningPhilosoperProblem.TOTAL; i++) {
			forks.add(new Fork());
		}
		
		List<Philosopher> philosophers = new ArrayList<>();
		
		for (int i = 0; i < DiningPhilosoperProblem.TOTAL; i++) {
			philosophers.add(new Philosopher(i, forks.get(i), forks.get((i+DiningPhilosoperProblem.TOTAL-1)%DiningPhilosoperProblem.TOTAL)));
		}
		
		for (Philosopher philosopher : philosophers) {
			new Thread(philosopher).start();
		}
		
		Thread display =new Thread(new Display(philosophers,forks));
		
		display.start();
		
	}

}

class Display implements Runnable {
	List<Philosopher> philosophers;
	List<Fork> forks;
	public Display(List<Philosopher> philosophers,List<Fork> forks) {
		this.philosophers = philosophers;
		this.forks = forks;
	}
	@Override
	public void run() {
		while(true){
			for (int i = 0; i < philosophers.size(); i++) {
				System.out.print("  "+i);
			}
			System.out.println();
			for (int i = 0; i < philosophers.size(); i++) {
				System.out.print("  "+philosophers.get(i).eatCounter);
			}
			System.out.println();
			for (int i = 0; i < philosophers.size(); i++) {
				System.out.print("  "+philosophers.get(i).thinkCounter);
			}
			
			System.out.println("-----------------------------");
			for (int i = 0; i < forks.size(); i++) {
				System.out.print("  "+forks.get(i).isAvailable);
			}
			System.out.println("-----------------------------");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Thread t = new Thread();
			t.join();
			
		}
		
	}
	
	
}

class Fork {
	volatile boolean isAvailable = true;
}

class Philosopher implements Runnable {

	int id;
	int eatCounter;
	int thinkCounter;
	Random random;
	
	Fork right;
	Fork left;
	
	
	
	public Philosopher(int id, Fork right, Fork left) {
		this.id = id;
		this.random = new Random();
		this.right = right;
		this.left = left;
	}
	
	
	@Override
	public void run() {
		while(true){
		think();
		getFork();
		eat();
		dropFork();
		}
		
	}
	
	
	private void dropFork() {
		
		if(id%2==0){
			//take right
			put(right);
		}else{
			//take left
			put(left);
		}
		
		
		if(id%2==0){
			//take left
			put(left);
		}else{
			//take right
			put(right);
		}
		
	}


	private void put(Fork fork) {
		fork.isAvailable=true;
		
	}


	private void getFork() {
		
		//if(id%2==0){
			//take right
			get(right);
		/*}else{
			//take left
			get(left);
		}*/
		
		
		//if(id%2==0){
			//take left
			get(left);
		/*}else{
			//take right
			get(right);
		}*/
		
	}


	private void get(Fork fork) {
		// TODO Auto-generated method stub
		while(!fork.isAvailable){
		//	System.out.println(fork.isAvailable);
		}
		fork.isAvailable=false;
		
	}


	private void think() {
		
		int value = random.nextInt(1000);
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		thinkCounter++;
	}
	
	private void eat() {
		
		int value = random.nextInt(1000);
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		eatCounter++;
	}
	
}
