package com.javalearning;

public class SymbolReference {
	
	private SubClass sc;
	
	public SuperClass get() {
		sc = null;
		return sc;
	}
	
	public static void main(String args[]) {
		SymbolReference sr = new SymbolReference();
		if (sr.get() == null) {
			System.out.println("null");
			System.out.println(A.a);
		}
	}
}

class A {
	public static int a = 3;
}
