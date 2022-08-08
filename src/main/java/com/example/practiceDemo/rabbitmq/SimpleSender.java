package com.example.practiceDemo.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SimpleSender {

    @Autowired
    private RabbitTemplate template;

    private static final String queueName="simple.hello";

    public void send(String message) {
        this.template.convertAndSend(queueName, message);
        log.info(" SimpleSender send:{}", message);
    }
}
