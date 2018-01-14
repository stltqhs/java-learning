package com.javalearning;

public class EffectivelyFinalTest {
	
	public int x = 0;
	
	class LevelFirst {
		
		public int x = 1;
		
		public LevelFirst() {
			int y = 3;
			
			class LevelSecong {
				public void f() {
					//y = 4; // error
					System.out.println(y);
				}
			};
		}
	}
	
	public static void main(String args[]) {
		EffectivelyFinalTest inst1 = new EffectivelyFinalTest();
		EffectivelyFinalTest.LevelFirst inst2 = inst1.new LevelFirst();
		
		System.out.println(inst1.x);
	}
	
}
