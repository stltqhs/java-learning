package com.javalearning;

public class NonSharedVar {
	
	public static int i = 0;
	
	public static void main(String args[]) {
		Thread[] threads = new Thread[2];
		threads[0] = new Thread(new Runnable() {
			public void run() {
				for (int j = 0; j < 1000; j++) {
					for (int k = 0; k < 20; k++) {
						i++;
						System.out.print("(" + i + ")");
					}
				}
			}
		});
		threads[1] = new Thread(new Runnable() {
			public void run() {
				for (int j = 0; j < 20000; j++) {
					System.out.print("[" + i + "]");
				}
			}
		});
		threads[0].start();
		threads[1].start();
	}
	
}
