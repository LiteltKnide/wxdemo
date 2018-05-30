package com.xiaodao.controller;

import com.xiaodao.activemq.producer.TestQueueProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hujt49 on 2018/05/30.
 */
@RestController
@RequestMapping("/queue")
public class QueueTestController {

    private Logger logger = LoggerFactory.getLogger(QueueTestController.class);

    @Autowired
    TestQueueProducer testQueueProducer;

    @GetMapping("/test/{name}")
    public String sendQueue(@PathVariable("name") String name) {
        testQueueProducer.send(name);
        return name;
    }
}
