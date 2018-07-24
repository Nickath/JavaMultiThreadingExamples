package org.nick.threads;

import java.util.concurrent.Semaphore;

public class Connection {

	private static Connection instance = new Connection();
	private int connections = 0;
	Semaphore semaphore = new Semaphore(10, true);
	private Connection() {
		
	}
	
	public static Connection getInstance() {
		return instance;
	}
	
	
	public void connect() throws InterruptedException {
		try {
			doConnect();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			doConnect();
		}
		finally {
			semaphore.release();
		}
	}
	public void doConnect() throws InterruptedException {
		semaphore.acquire();
		synchronized(this) {
			connections++;
			System.out.println("Current connections: "+connections);
		}
		
		Thread.sleep(2000);
		semaphore.release();
		synchronized(this) {
			connections--;
			System.out.println("Current connections: "+connections);
		}
	}
}
