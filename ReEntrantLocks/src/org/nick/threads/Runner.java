package org.nick.threads;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private int count = 0; 
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	private void increment() {
		for(int i =0; i< 10000; i++) {
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException {
		    lock.lock();//if another thread tries to lock this, it will wait
		    System.out.println("Waiting ...");
		    cond.await();//same method with wait()
		    try {
			increment();
		    }
		    finally {
		    lock.unlock();	
		    }
			
	}
	
	public void secondThread() throws InterruptedException {
		Thread.sleep(500); //to make sure that the first thread will lock the lock
	    lock.lock();//if another thread tries to lock this, it will wait
	    
	    System.out.println("Press the return key");
	    new Scanner(System.in).nextLine();
	    System.out.println("Return key pressed");
	    
	    cond.signal();//signal is the corresponding notify() in a synchronized block, it will wake the firstThread method
	    try {
		increment();
	    }
	    finally {
	    lock.unlock();	
	    }
	}
	
	public void finished() {
		System.out.println("Count is: "+count);
	}
}	



/* Alternative way to achieve the above using the synchronized keyword 
 * 
 * package org.nick.threads;

public class Runner {

	private int count = 0; 
	private Object lock = new Object();
	
	private void increment() {
		for(int i =0; i< 10000; i++) {
			count++;
		}
	}
	
	public void firstThread() {
		synchronized(lock) {
			increment();
		}
		
	}
	
	public void secondThread() {
		synchronized(lock) {
			increment();
		}
	}
	
	public void finished() {
		System.out.println("Count is: "+count);
	}
}

 * 
 * 
 * 
 * 
 * */
