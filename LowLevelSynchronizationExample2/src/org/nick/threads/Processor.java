package org.nick.threads;

import java.util.LinkedList;
import java.util.Random;

public class Processor {
	
	//wait() causes the current thread to wait (blocks or suspends execution) until another thread
	//invokes the notify() method or the notifyAll() method for this object.

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 20;
	private Object lock = new Object();
	private static volatile int headOfList = 1;
	//in the produce thread, we will add items to the linkedlist
	public void produce() throws InterruptedException {
		int value = 0; 
		
		while(true) {
			
			synchronized(lock) {
				// if the list has come to the upper limit, wait and give the intrinsic lock to the consume method
				while(list.size() == LIMIT) {
					lock.wait();//
				}
				list.add(value++);
				System.out.println("List size: "+list.size());
				lock.notify();// when the this operation takes place, notify any other threads that are waiting
			}
			
		}
	}
	
	//in the consume thread we will remove items from the linkedlist
	public void consume() throws InterruptedException {
        
		while(true) {
			synchronized(lock) {
				//if the list has 0 elements,wait
				while(list.size() == 0) {
					lock.wait();
				}
				headOfList = list.removeFirst();
			    System.out.println("Head of list removed"+headOfList);//remove the first element from LinkedList (FIFO)
				lock.notify();//if any other thread was waiting for the above action, now will be notified
				//in our case, the thread running the produce method will be awakened up
			}
			
			Thread.sleep(500);
		}
	}
}
