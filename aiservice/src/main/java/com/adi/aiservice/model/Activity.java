package com.adi.aiservice.model;

import com.adi.aiservice.config.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Activity {
    private Integer id;
    private String userId;
    private ActivityType activityType;
    private Integer duration;
    private Integer caloriesBurned;
    private Map<String,Object> additionalMetrics;
    private LocalDateTime startTime;
}