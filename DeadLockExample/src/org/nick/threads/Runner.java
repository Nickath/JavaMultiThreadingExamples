package org.nick.threads;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	private static Lock lock1 = new ReentrantLock();
	private static Lock lock2 = new ReentrantLock();
	
	private void acquireLocks(Lock firstlock, Lock secondlock) throws InterruptedException {
		while(true) {
			//Acquire locks
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			try {
				gotFirstLock = firstlock.tryLock();
				gotSecondLock = secondlock.tryLock();
			}
			finally {
				if(gotFirstLock && gotSecondLock) {
					return;
				}
				
				if(gotFirstLock) {
					firstlock.unlock();
				}
				if(gotSecondLock) {
					secondlock.unlock();
				}
			}
			
			//Locks not acquired
			Thread.sleep(1);
			
		}
	}
	
	
	public void firstThread() throws InterruptedException {
		Random random = new Random();
		for(int i = 0; i < 10000; i++) {
			
			acquireLocks(lock1,lock2);

			try {
			Account.transfer(acc1, acc2, random.nextInt(100));
			}
			finally{
			  lock1.unlock();
			  lock2.unlock();
			}
		}
	}
	
	public void secondThread() {
		Random random = new Random();
		for(int i = 0; i < 10000; i++) {
			
			lock1.lock();
			lock2.lock();
			
			try {
			Account.transfer(acc2, acc1, random.nextInt(100));
			}
			finally{
			  lock1.unlock();
			  lock2.unlock();
			}
	}
	
	}
	public void finished() {
		System.out.println("Account 1 balance: "+acc1.getBalance());
		System.out.println("Account 1 balance: "+acc2.getBalance());
		System.out.println("Total balance: "+(acc1.getBalance()+acc2.getBalance()));
	}
	
}
