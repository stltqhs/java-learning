package com.javalearning;

public class InitOrder {

	{
		System.out.println("Init Order {}.");
	}
	
	int i = 1;
	
	Sym sym = new Sym();
	
	public static void main(String args[]) {
		InitOrder io = new InitOrder();
	}
}

class Sym {
	
	public Sym() {
		System.out.println("Sym init!");
	}
	
}
