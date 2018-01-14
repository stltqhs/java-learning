package com.yuqing.dubbo.provider.service.impl;

import com.yuqing.dubbo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author YutsingLee
 * @date 2018-01-14
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    public String hello() {
        logger.debug("invoke hello");
        return "dubbo-provider";
    }
}
