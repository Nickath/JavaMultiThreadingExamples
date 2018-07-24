package org.nick.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) throws InterruptedException {
		//a semaphore is an object that maintains a count
		//we refer to the count as a number of available permits of the semaphore
		Semaphore semaphore = new Semaphore(1);
		System.out.println("Available permits: "+semaphore.availablePermits());
		semaphore.release();//semaphore function release increases the permits
		System.out.println("Available permits: "+semaphore.availablePermits());
		//semaphore acquire function decreases the permits and if no permits exist in the semaphore object will wait
		//as happens with lock
		semaphore.acquire();
		System.out.println("Available permits: "+semaphore.availablePermits());
		
		Connection.getInstance().connect();
	    ExecutorService executor = Executors.newCachedThreadPool();
	    //create a bunch of threads and call the connect method from all
	    for(int i = 0; i < 200; i++) {
	    	executor.submit(new Runnable() {
				public void run() {
					try {
						Connection.getInstance().connect();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	    		
	    	});

	    }
    	executor.shutdown();
    	executor.awaitTermination(1, TimeUnit.DAYS);

	}

}
