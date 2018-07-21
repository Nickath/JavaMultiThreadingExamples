package org.nick.threads;

import java.util.Scanner;

public class Processor {

	public void produce() throws InterruptedException {
		//synchronizing on this uses the intrinsic lock of the Processor object
		synchronized(this) {
			System.out.println("Producer thread running ....");
			wait();//this thread will not resume until two things happen, 1)It must be possible for this thread to regain control of this lock to resume
			System.out.println("Resumed");
		}
	}
	
	//add thread to sleep so that this method will kick off first
	public void consume() throws InterruptedException {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		synchronized(this) {
			System.out.println("Waiting for return key");
			scanner.nextLine();
			notify();//notify can only be called within a synchronized block, this notify will notify the other thread that if it's waiting it can wake up
			System.out.println("Return key pressed");
			Thread.sleep(5000);//the notify() method may have been called to tell to the produce method that the lock 
			//has been released but the Thread.sleep keeps holding the lock so the Resumed method in the produce
			//function can run until the lock is relinquished by the previous thread
		}
	}
}
