package com.yuqing.dubbo.provider.service.impl;

import com.yuqing.dubbo.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author YutsingLee
 * @date 2018-01-14
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {
    public String hello() {
        System.out.println("invoke hello");
        return "dubbo-provider";
    }
}
