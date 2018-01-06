package com.javalearning;

public class FinalTest {

	public static void main(String args[]) {
		final1();
		
		compare1();
	}
	
	private static void compare1() {
		String s = null;
		
		if ((s != null) && (s.length() > 0)) System.out.println("1");
		if ((s != null) & (s.length() > 0)) System.out.println("2");
		if ((s == null) || (s.length() > 0)) System.out.println("3");
		if ((s == null) | (s.length() > 0)) System.out.println("4");
	}
	
	private static void final1() {
		AA a = new AA();
		final1_1(a);
		final1_1(a);
		//System.out.println(a.i);
	}
	
	private static void final1_1(final AA a) {
		a.i++;
	}
	
}

class AA {
	public int i;
}
