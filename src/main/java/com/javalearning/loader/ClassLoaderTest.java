package com.javalearning.loader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {
	
	private String abc = "abc";
	
	public static void main(String args[]) throws InstantiationException, IllegalAccessException {
		MyClassLoader mc = new MyClassLoader();
		Class<?> clTest = mc.loadClass("com.javalearning.loader.ClassLoaderTest");
		Object obj = clTest.newInstance();
		try {
			Method printLoader = clTest.getMethod("printLoader", null);
			printLoader.invoke(obj, null);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printLoader() {
		System.out.println("String class Loader");
		System.out.println(abc.getClass());
	}
	
}

class MyClassLoader extends ClassLoader {
	public Class<?> loadClass(String name) {
		String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
		InputStream is = getClass().getResourceAsStream(fileName);
		if (is == null)
			try {
				return super.loadClass(name);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			byte data[] = new byte[is.available()];
			is.read(data);
			return defineClass(name, data, 0, data.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}