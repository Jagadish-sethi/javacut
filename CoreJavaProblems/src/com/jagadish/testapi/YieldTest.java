package com.jagadish.testapi;

public class YieldTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		long old= System.currentTimeMillis();
		Thread t =new Thread(new MyTask());
		t.start();
		
		t.join();
		long newtime= System.currentTimeMillis();
		System.out.println(newtime-old);
	}

}


class MyTask extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for (long i = 0; i < 1000000; i++) {
			System.out.println("hell"+i);
			
			if(i%100000==0){
			//	this.yield();
			}
		}
		
	}
	
}