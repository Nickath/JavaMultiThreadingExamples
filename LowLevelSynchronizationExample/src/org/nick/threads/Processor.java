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
				// if the list has come to the upper limit, wait
				while(list.size() == LIMIT) {
					System.out.println("Reached upper limit ("+ LIMIT +"), waiting ...\n"
							+ "Give the intrinsic lock to consume method\"");
					Thread.sleep(3000);
					lock.wait();
				}
				list.add(value++);
				System.out.println("List size: "+list.size());
				lock.notify();// when the above operation takes place, notify any other threads that are waiting
			}
			
		}
	}
	
	//in the consume thread we will remove items from the linkedlist
	public void consume() throws InterruptedException {
        
		while(true) {
			synchronized(lock) {
				//if the list has 0 elements,wait
				while(list.size() == 0) {
					System.out.println("Reached zero limit, waiting ...\n"
							+ "\"Give the intrinsic lock to produce thread\""+" Head of the list is "+headOfList);
					Thread.sleep(3000);
					lock.wait();
				}
				System.out.println("List size: "+list.size());
				headOfList = list.removeFirst();
			    //	System.out.println("Value is "+value);//remove the first element from LinkedList (FIFO)
				lock.notify();//if any other thread was waiting for the above action, now will be notified
			}
		}
	}
}
