package com.javalearning;

public class VolatileTest {

	public static volatile int race = 0;
	
	public synchronized static void increase() {
		race++;
	}
	
	public synchronized static int getRace() {
		return race;
	}
	
	private static final int THREADS_COUNT = 20;
	
	public static void main(String args[]) {
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < THREADS_COUNT; i++) {
			threads[i] = new Thread(new Runnable() {
				public void run() {
					for (int i = 0; i < 10000; i++)
					increase();
				}
			});
			threads[i].start();
		}
		
		int n = 0;
		while (n < THREADS_COUNT) {
			n = 0;
			for (int i = 0; i < THREADS_COUNT; i++) {
				if (!threads[i].isAlive())
					n++;
			}
		}
		System.out.println(getRace());
	}
	
}
