package com.autumn.component.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RocketMQMessageListener(topic = "TOPIC_TEST", consumerGroup = "your_consumer_group_name")
@Slf4j
public class RocketComsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        // 处理消息的逻辑
        log.info("Received message = {}",message);
    }

}
