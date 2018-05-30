package com.xiaodao.activemq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;


/**
 * Created by hujt49 on 2018/05/30.
 */
@Component
public class TestQueueProducer {

    private Logger logger = LoggerFactory.getLogger(TestQueueProducer.class);

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;


}
