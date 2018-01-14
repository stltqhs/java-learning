package com.javalearning;

public class InsertionSort {
	
	/**
	 * straight insertion sort
	 * @param src
	 */
	public static void sis(int src[]) {
		int temp = 0;
		int len = src.length;
		int j= 0;
		for (int i = 1; i < len; i++) {
			temp = src[i];
			for (j = i - 1; j >= 0 && src[j] > temp; j--)
				src[j + 1] = src[j];
			src[j + 1] = temp;
		}
	}
	
	public static void sisg(int src[]) {
		int len = src.length;
		
		for (int i = 1; i < len; i++) {
			
		}
	}
	
	public static void main(String args[]) {
		int arr[] = new int[]{7, 2, 18, 63, 1, 5,37};
		System.out.println("sis:");
		sisg(arr);
		Util.printArray(arr);
	}
	
}
