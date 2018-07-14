package org.nick.threads;

import java.util.Scanner;

//In the example below, we initialize the running variable as true, in the main function, we create an instance
// of the Processor class which extends the Thread class and call the run method which is running a loop
//while the running variable is true
//the shutdown method actually in case we press any key should change the value of the running variable 
//which will have as a result the run method of the processor to stop
//The case is that in some systems or in some imperfect implementations of java, the thread running the
//code may decide to cache the running variable value.
//In fact, the thread of the main function intervenes to the value of the variable that the thread of the run method
//uses, which may be something that Java does not expect to happen
//using the volatile keyword, this code is guaranteed to work in all environments
//volatile is used to prevent threads caching variables when they are not changed from within that thread
//if we want to change a variable from another thread, we have to use volatile keyword
class Processor extends Thread {
	
	private volatile boolean running = true;
	
	public void run() {
		
		while(running) {
			System.out.println("Hello");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutDown() {
		running = false;
	}
}

public class App {

	public static void main(String[] args) {
		Processor proc1 = new Processor();
		proc1.start();
		
		System.out.println("Press return to stop");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		proc1.shutDown();
		

	}

}
