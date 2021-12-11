package com.example.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@RestController
public class MessagePublisher {

    private final RabbitTemplate rabbitTemplate;

    public MessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestBody RabbitMessage message){
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(Date.from(Instant.now()));

        rabbitTemplate.convertAndSend(RabbitConfiguration.MESSAGE_EXCHANGE, RabbitConfiguration.ROUTING_KEY, message);

        return "Message was published";
    }
}
