package org.nick.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//A thread pool is like having a number of workers in a factory, if we have a bunch of tasks, we distribute them
//to our workers (threads) to do them in parallel

class Processor implements Runnable{

	private int id; 
	
	public Processor(int id) {
		this.id = id;
	}
	
	public void run() {
		System.out.println("Starting: "+id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed: "+id);
	}
	
}

public class App {

	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(2); //create two threads
		//the executor service will run its own managerial thread that will handle parcelling out the tasks we give
		//to it
		
		//f.e we give 5 tasks to the executor service having two threads to complete them
		for(int i = 0; i < 5; i++) {
			executor.submit(new Processor(i));

		}
		System.out.println("All tasks submitted");
		executor.shutdown();//declare to the executor stop accepting new tasks
        
        try {
			executor.awaitTermination(1, TimeUnit.DAYS);//wait for one day if tasks have not finished
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//
        
        System.out.println("All tasks completed");
	}

}
