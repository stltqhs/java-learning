package com.javalearning;

import java.util.ArrayList;
import java.util.List;

public class ConcurentModificationExceptionTest {
	
	public static void main(String[] args) {
		List<String> arrList = new ArrayList();
		arrList.add("1");
		arrList.add("2");
		
		for (String item : arrList) {
			if ("1".equals(item))
				arrList.remove(item);
		}
		
		System.out.println(arrList);
	}
	
}
