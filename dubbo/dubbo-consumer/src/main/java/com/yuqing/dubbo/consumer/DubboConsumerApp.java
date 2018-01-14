package com.yuqing.dubbo.consumer;

import com.yuqing.dubbo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class DubboConsumerApp {

    private static final Logger logger = LoggerFactory.getLogger(DubboConsumerApp.class);

    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        applicationContext.start();
        applicationContext.refresh();

        DemoService demoService = (DemoService) applicationContext.getBean("demoService");

        logger.debug("invoke hello() and return {}", demoService.hello());
    }
}
