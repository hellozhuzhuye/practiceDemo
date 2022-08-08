package com.example.practiceDemo.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

@Slf4j
public class SimpleReceiver {
    //开启10个子进程消费者 监听simple.hello
    @RabbitListener(queues = "simple.hello",concurrency = "10")
    public void receive(Channel channel, Message message) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info(" SimpleReceiver deliveryTag:{}", deliveryTag);
            log.info(" SimpleReceiver message:{}", message);
            log.info(" SimpleReceiver messageProperties:{}", message.getMessageProperties());
            log.info(" SimpleReceiver body:{}", message.getBody());
        }catch (Exception e){
            log.info("receive异常:",e);
            channel.basicReject(deliveryTag,true);
        }

    }

    //开启5个子进程消费者 监听simple.hello
    @RabbitListener(queues = "simple.hello",concurrency = "5")
    public void receive2(String in){
        log.info("receive2 in:{}",in);
    }

}
