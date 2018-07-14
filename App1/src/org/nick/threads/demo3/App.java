package org.nick.threads.demo3;


//sometimes we need to run one method in its thread and we dont want to create a separate class 
//this is a good and fast way to do this
public class App {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {

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
			
		});
	}
}
