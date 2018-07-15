package org.nick.threads;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

	//the blockingQueue is a data structure which can hold data items of the type you want (as arraylist)
	//it's a FIFO data structure (like classic queues)
	//thread-safe class, as any in the concurrent.* package so we do not have to worry about synchronization
	private static BlockingQueue queue = new ArrayBlockingQueue<Integer>(10);
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				consumer();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	private static void producer() throws InterruptedException {
		Random random = new Random();
		while(true) {
			try {
				queue.put(random.nextInt(100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(50);
		}
		
	}
	
	private static void consumer() {
		Random random = new Random();
		while(true) {
			try {
				Thread.sleep(50);
			//	if(random.nextInt(10) == 0) {
					Integer value = (Integer) queue.take();//retrieves and removes the head of the queue
					//if nothing exists inside the queue, take will wait until the queue will be filled with
					//any value when queue.take takes place, then queue.put patiently waits the take process
					//to finish
					System.out.println("Taken value: "+value+"; Queue size is: "+queue.size());
			//	}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
