package com.adi.activityservice.service;

import com.adi.activityservice.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {
    ActivityDTO addActivity(ActivityDTO activityDTO);

    List<ActivityDTO> getActivitiesOfUser(String userId);

    ActivityDTO getActivityById(Integer activityId);
}
