package com.adi.activityservice.service;

import com.adi.activityservice.config.RabbitMQConfig;
import com.adi.activityservice.model.Activity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActivityProducer {
    @Autowired
    private RabbitTemplate template;
    public void sendMessage(Activity activity){
        try {
            template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, activity);
            log.info("Message sent to rabbitMQ successfully");
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
