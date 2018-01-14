package com.yuqing.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class DubboProviderApp
{
    public static void main( String[] args ) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        applicationContext.start();
        applicationContext.refresh();

        System.in.read();
    }
}
