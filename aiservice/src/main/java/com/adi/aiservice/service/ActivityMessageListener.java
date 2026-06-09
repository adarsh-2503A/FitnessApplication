package com.adi.aiservice.service;

import com.adi.aiservice.model.Activity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActivityMessageListener {

    @RabbitListener(queues = "activityQueue")
    public void processActivity(Activity activity){
        log.info("Received Message for processing "+activity.getId());
    }
}
