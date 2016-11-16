package problem.statement;

public class JoinEx {
	public static void main(String[] args) {
		
		
		final Thread mainThread = Thread.currentThread();
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(100);
					System.out.println(mainThread.getState());
					Thread.currentThread().interrupt();
					System.out.println(mainThread.getState());
					StringBuilder sb = new StringBuilder();
					for(int i=0; i<10000000; i++) {
						int k=i*2;
						sb.append(i);
					}
					System.out.println("abc");
					Thread.currentThread().interrupt();
					for(int i=0; i<10000000; i++) {
						int k=i*2;
						sb.append(i);
					}
					System.out.println("Completed");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t.start();
		
		///Producer Thread
		try {
			System.out.println(mainThread.getState());
			//consumer
			t.join();
			System.out.println("join1 complete");
		} catch (InterruptedException e) {
			System.out.println("join1");
		}
		try {
			System.out.println(mainThread.getState());
			t.join();
			System.out.println("Something");
		} catch (InterruptedException e) {
			System.out.println("join2");
		}
	}
}
