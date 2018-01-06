package com.javalearning;

public class InitializeCar extends InitializeCarPrt {
	
	{
		System.out.print("1");
	}
	
	public InitializeCar() {
		System.out.print("2");
	}
	
	static {
		System.out.print("3");
		System.out.print("4");
	}
	
	public static void main(String args[]) {
		new InitializeCar();
	}

}

class InitializeCarPrt {
	
	public InitializeCarPrt() {
		System.out.print("5");
	}
	
	static {
		System.out.print("6");
	}
}
