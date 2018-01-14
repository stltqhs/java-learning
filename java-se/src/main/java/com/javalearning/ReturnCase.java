package com.javalearning;

public class ReturnCase {
	
	public static void main(String args[]) {
		System.out.println(fd());
	}
	
	private static int fd() {
		int i = 1;
		try {
			i++;
			return i;
		} finally {
			i++;
			return i;
		}
	}
	
}
