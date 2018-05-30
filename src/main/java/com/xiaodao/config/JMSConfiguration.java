package com.xiaodao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;

/**
 * Created by hujt49 on 2018/05/30.
 */
@Configuration
public class JMSConfiguration {

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate() {
        return new JmsMessagingTemplate();
    }
}
