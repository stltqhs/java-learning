package com.javalearning;

public class Poly {

	public static void main(String args[]) {
		ClassA a = new ClassB();
		a.f();
	}
}

class ClassA {
	protected int i = 1;
	protected int j = 2;
	public void f() {
		System.out.println(i);
		System.out.println(j);
	}
}

class ClassB extends ClassA {
	/*
	protected int i = 3;
	protected int j = 4;
	*/// output 1 2
	
	/*
	public ClassB() {
		i = 3;
		j = 4;
	}
	*/// output 3 4
}
