package com.javalearning;

public class Util {
	
	public static void printArray(int arr[]) {
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			if (i > 0) System.out.print(",");
			System.out.print(arr[i]);
		}
	}
	
}
