package com.javalearning;

public class Constructor {

	private int a;
	
	private int b;
	
	public Constructor() {
		a = 1;
		b = 2;
	}
	
	public Constructor(int a) {
		this.a = a;
		b = 2;
	}
	
	public Constructor(int a, int b) {
		this.a = a;
		this.b = b;
	}
}
