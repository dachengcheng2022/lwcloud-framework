package com.autumn.controller.rocketmq;

import com.autumn.common.RetBiz;
import com.autumn.constant.RocketMqConstant;
import io.swagger.annotations.Api;
import lombok.Setter;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rocket")
@Api(tags = "ROCKET相关测试")
public class NormalProduceController {
    @Setter(onMethod_ = @Autowired)
    private RocketMQTemplate rocketmqTemplate;

    @GetMapping("/test")
    public RetBiz test() {
        Message<String> msg = MessageBuilder.withPayload("Hello,RocketMQ").build();
        rocketmqTemplate.send(RocketMqConstant.TOPIC_TEST, msg);
        return RetBiz.OK();
    }

}