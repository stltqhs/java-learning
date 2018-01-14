package com.yuqing.dubbo.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class DubboProviderApp {

    private static final Logger logger = LoggerFactory.getLogger(DubboProviderApp.class);

    public static void main( String[] args ) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        applicationContext.start();
        applicationContext.refresh();

        System.in.read();
    }
}
