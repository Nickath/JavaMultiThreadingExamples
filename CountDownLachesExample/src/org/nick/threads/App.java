package org.nick.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class Processor implements Runnable{
    private CountDownLatch latch;
    private int i;
    
    public Processor(CountDownLatch latch, int i) {
    	this.latch = latch;
    	this.i = i;
    }
	public void run() {
		System.out.println("Started processor " +this.i);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		latch.countDown();
	}
	
}

public class App {

	
	public static void main(String[] args) {
		
		//a countdownlatch lets you count down from a number that you specify (f.e 3 until 0)
		//and it lets one or more threads wait until the latch reaches zero
		CountDownLatch latch = new CountDownLatch(20);
		ExecutorService executor = Executors.newFixedThreadPool(20); // create 3 workers (threads)
		
		//each thread is going to get one processor 
		for(int i = 0; i < 20; i++) {
			executor.submit(new Processor(latch, i));
		}
		
		try {
			latch.await();//await function waits until the countdown latch has counted down to zero
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed");
		executor.shutdown();//shutdown the executor - stop accepting any other tasks
	}
}
