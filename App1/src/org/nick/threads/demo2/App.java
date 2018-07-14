package org.nick.threads.demo2;

//the second way is to implement the Runnable interface and pass it to the constructor of the Thread class
class Runner implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello " + i);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}



public class App  {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runner(), "Thread t1");
		Thread t2 = new Thread(new Runner(), "Thread t2");
		
		//these threads will run in parallel
		t1.start();
		t2.start();

	}

	

}
