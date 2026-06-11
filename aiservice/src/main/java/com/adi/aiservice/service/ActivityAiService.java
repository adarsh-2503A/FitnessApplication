package com.adi.aiservice.service;

import com.adi.aiservice.model.Activity;
import com.adi.aiservice.model.Recommendation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@Slf4j
public class ActivityAiService {
    private ChatClient chatClient;

    @Value("classpath:ActivitySystemPrompt.st")
    private Resource systemPrompt;

    @Value("classpath:outputFormat.txt")
    private Resource outputFormat;

    public ActivityAiService(ChatClient.Builder chatClientBuilder){
        this.chatClient=chatClientBuilder.build();
    }

    public Recommendation generateAiRecommendation(Activity activity) throws IOException {
        String opFormat=outputFormat.getContentAsString(StandardCharsets.UTF_8);
        PromptTemplate promptTemplate=new PromptTemplate(systemPrompt);
        Map<String,Object> activityMap=Map.of("activityType",activity.getActivityType(),"duration",activity.getDuration(),"caloriesBurned",activity.getCaloriesBurned(),"additionalMetrics",activity.getAdditionalMetrics(),"outputFormat",opFormat);
        Recommendation recommendation=chatClient.prompt(promptTemplate.create(activityMap)).call().entity(Recommendation.class);
        Recommendation finalRecommendation=new Recommendation();
        finalRecommendation.setActivityId(activity.getId());
        finalRecommendation.setActivityType(activity.getActivityType());
        finalRecommendation.setUserId(activity.getUserId());
        finalRecommendation.setAnalysis(recommendation.getAnalysis());
        finalRecommendation.setImprovements(recommendation.getImprovements());
        finalRecommendation.setSafety(recommendation.getSafety());
        finalRecommendation.setSuggestions(recommendation.getSuggestions());
        return finalRecommendation;
    }
}
