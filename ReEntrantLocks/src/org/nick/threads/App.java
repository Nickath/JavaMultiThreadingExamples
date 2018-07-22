package org.nick.threads;

/** A re-entrant lock is an alternative way of using the synchronized keyword
 * 
 * 
 *
 */
public class App {

	public static void main(String[] args) throws InterruptedException {
		Runner runner = new Runner();
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					runner.firstThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					runner.secondThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		t1.start();
		t2.start();
		
		//the join() method which allows one thread to wait until another thread completes its execution.
		//If t is a Thread object whose thread is currently executing, then t.join(); it
		//causes the current thread to pause its execution until thread it join completes its execution.
		t1.join();
		t2.join();
		
		runner.finished();
	}
}
