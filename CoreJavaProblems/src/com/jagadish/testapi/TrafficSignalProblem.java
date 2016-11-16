package com.jagadish.testapi;

public class TrafficSignalProblem {
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Traffic(0));
		Thread t2 = new Thread(new Traffic(1));
		Thread t3 = new Thread(new Traffic(2));
		
		t1.start();
		t2.start();
		t3.start();
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	Traffic.stop = true;
		
		
	}

}



class Traffic implements Runnable {

	static int counter = 0;
	static volatile boolean stop = false; 
	int id;
	
	public Traffic(int id) {
		this.id =id;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!stop){
			synchronized (Traffic.class) {
				if(counter%3!=id){
					try {
						Traffic.class.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				counter++;
				System.out.println(id+"---"+counter);
				
			}
			
			
		}
		
	}
	
}
