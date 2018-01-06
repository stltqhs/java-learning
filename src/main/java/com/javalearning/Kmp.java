package com.javalearning;

public class Kmp {
	
	private static boolean kmp(String src, String pattern) {
		return index(src, pattern) != -1;
	}
	
	private static int index(String src, String pattern) {
		int next_arr[] = next(pattern);
		int i = 0, j = 0;
		int p = -1;
		int srcLen = src.length(), patternLen = pattern.length();
		while (i < srcLen && j < patternLen) {
			if (j == -1 || src.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			} else {
				j = next_arr[j];
			}
		}
		if (j == patternLen)
			p = i - j;
		return p;
	}
	
	private static int[] next(String pattern) {
		int pos[] = new int[pattern.length()];
		int len = pattern.length();
		int j = 0, k = -1;
		pos[0] = -1;
		while (j < len - 1) {
			if (k == -1 || pattern.charAt(k) == pattern.charAt(j)) {
				j++;
				k++;
				if (pattern.charAt(k) != pattern.charAt(j)) {
					pos[j] = k;
				} else
					pos[j] = pos[k];
			} else {
				k = pos[k];
			}
		}
		return pos;
	}
	
	public static void main(String args[]) {
		String src = "123abcabd12";
		String pattern = "abcabd";
		System.out.println(kmp(src, pattern));
	}
	
}
