package org.nick.threads;

public class App {

	private int count = 0;
	
	public static void main(String[] args) {
		App app = new App();
		app.doWork();

	}
	
	//every object in java has an intrinsic lock (or monitor lock)
	//if you call a synchronized method of an object, you acquire the intrinsic lock
	//only one thread can acquire the intrinsic lock at a time, if a second thread will try 
	//to use the function while it is already used by another thread (that already has the intrinsic lock)
	//it will have to wait until the first thread finishes and releases the intrinsic lock
	public synchronized void increment() {
		for(int i = 0; i < 10000; i++) {
			count++;
		}
	}
	
	public void doWork() {
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				increment();
				
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				increment();
				
			}
			
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join(); // to wait for the threads to finish use the thread.join method
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Count :" + count);
	}

}
