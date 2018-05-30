package com.xiaodao.activemq.listener;

import com.xiaodao.activemq.QueuesName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by hujt49 on 2018/05/30.
 */
@Component
public class TestQueueListener {

    private Logger logger = LoggerFactory.getLogger(TestQueueListener.class);

    @JmsListener(destination = QueuesName.TEST_QUEUE)
    public void onMessage(String text) {
        logger.info("测试接收消息:{}", text);
    }
}
