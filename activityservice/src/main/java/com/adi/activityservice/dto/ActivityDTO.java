package com.adi.activityservice.dto;

import com.adi.activityservice.config.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityDTO {
    private Integer id;
    private String userId;
    private ActivityType activityType;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
}
