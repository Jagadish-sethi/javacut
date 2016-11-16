package com.jagadish.testapi;

public class VloatilTest {
	
	
	public static void main(String[] args) {
		
		Value val = new Value();
		new Thread(new Runner(0,val)).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new Runner(1,val)).start();
		
		
	}

}

class Value{
	/*volatile*/ boolean stop = false;
	
}

class Runner implements Runnable{
	
	Value val;
	
	int i = 0;
	public Runner(int i,Value val) {
		// TODO Auto-generated constructor stub
		this.i=i;
		this.val=val;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(i==0){
		running();
		}else{
			stopping();
		}
	}

	private void stopping() {
		// TODO Auto-generated method stub
		System.out.println("stop");
		val.stop=true;
		System.out.println("stop");
	}

	private void running() {
		// TODO Auto-generated method stub
		while(!val.stop){
			System.out.println("running"+(++i));
		}
		
	}
	
}