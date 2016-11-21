package com.jagadish.testapi;

public class TrafficSignalProblem {
	
	public static void main(String[] args) {
		
		Object obj = new Object();
		Thread t1 = new Thread(new Traffic(0,obj));
		Thread t2 = new Thread(new Traffic(1,obj));
		Thread t3 = new Thread(new Traffic(2,obj));
		
		t1.start();
		t2.start();
		t3.start();
		
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Traffic.stop = true;
		
		
	}

}



class Traffic implements Runnable {

	static int counter = 0;
	static int loop = 0;
	static volatile boolean stop = false; 
	int id;
	Object obj;
	
	public Traffic(int id, Object obj) {
		this.id =id;
		this.obj = obj;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!stop){
			synchronized (obj) {
				if(counter%3!=id){
					try {
						
						while(counter%3!=id){
							obj.wait();
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(counter%3==0){
					loop++;
				}
				counter++;
				//System.out.println(id+"---"+counter);
				
				
				if(loop%3==id){
					System.out.println(id+"---"+counter+"---"+loop+"----green");
				}else{
					System.out.println(id+"---"+counter+"---"+loop+"---red");
				}
				obj.notifyAll();
			}
			
			
		}
		
	}
	
}
