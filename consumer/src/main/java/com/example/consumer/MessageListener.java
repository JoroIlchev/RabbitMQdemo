package com.example.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = RabbitConfiguration.QUEUE_NAME)
    public void listener(RabbitMessage message){
        System.out.println(message);
    }
}
