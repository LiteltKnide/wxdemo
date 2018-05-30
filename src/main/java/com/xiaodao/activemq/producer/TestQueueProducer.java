package com.xiaodao.activemq.producer;

import com.xiaodao.activemq.QueuesName;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang3.time.DateUtils;
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

    public void send(String message){
        logger.info("发送MQ消息:{}-{}", message, System.currentTimeMillis());
        ActiveMQQueue queue = new ActiveMQQueue(QueuesName.TEST_QUEUE);
        jmsMessagingTemplate.convertAndSend(queue, message);
    }
}
