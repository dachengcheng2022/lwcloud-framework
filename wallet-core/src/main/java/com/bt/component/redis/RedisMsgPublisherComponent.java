package com.bt.component.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisMsgPublisherComponent {
    
	private static Logger logger = LoggerFactory.getLogger(RedisMsgPublisherComponent.class);
    
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    
    @Async
    public void asyncConvertAndSend(String channel, Object message) {
        try {
            this.redisTemplate.convertAndSend(channel, message);
        } catch (Exception e) {
            logger.error("RedisMsgPublisherComponent convertAndSend error,channel:{},message:{},detail:{}",channel,message,e.getMessage());
        }
    }

}
