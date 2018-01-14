package com.yuqing.dubbo.consumer;

import com.yuqing.dubbo.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class DubboConsumerApp
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        applicationContext.start();
        applicationContext.refresh();

        DemoService demoService = (DemoService) applicationContext.getBean("demoService");

        System.out.println(demoService.hello());
    }
}
