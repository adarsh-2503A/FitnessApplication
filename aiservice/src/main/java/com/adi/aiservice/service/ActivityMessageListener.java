package com.adi.aiservice.service;

import com.adi.aiservice.dao.RecommendationRepository;
import com.adi.aiservice.model.Activity;
import com.adi.aiservice.model.Recommendation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class ActivityMessageListener {

    @Autowired
    private ActivityAiService activityAiService;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @RabbitListener(queues = "activityQueue")
    public void processActivity(Activity activity) throws IOException {
        log.info("Received Message for processing "+activity.getId());
        Recommendation recommendation= activityAiService.generateAiRecommendation(activity);
        recommendationRepository.save(recommendation);
    }
}
