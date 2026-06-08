package com.adi.activityservice.service;
import com.adi.activityservice.config.MyCustomException;
import com.adi.activityservice.dto.ActivityDTO;
import com.adi.activityservice.model.Activity;
import com.adi.activityservice.repository.ActivityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityRepository activityRepository;

    private final ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public ActivityDTO addActivity(ActivityDTO activityDTO) {
        //demonstration of Builder design pattern which is implemented like this in java.
        Activity activity=Activity.builder()
                .userId(activityDTO.getUserId())
                .activityType(activityDTO.getActivityType())
                .duration(activityDTO.getDuration())
                .caloriesBurned(activityDTO.getCaloriesBurned())
                .startTime(activityDTO.getStartTime())
                .build();
        Activity activity1 = activityRepository.save(activity);
        return objectMapper.convertValue(activity1,ActivityDTO.class);
    }

    @Override
    public List<ActivityDTO> getActivitiesOfUser(String userId) {
        List<Activity> activityList=activityRepository.findByUserId(userId);
        return activityList.stream().map(activity -> objectMapper.convertValue(activity,ActivityDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ActivityDTO getActivityById(Integer activityId) {
        Optional<Activity> optionalActivity=activityRepository.findById(activityId);
        Activity activity=optionalActivity.orElseThrow(()->new MyCustomException("activity.not.found"));
        return objectMapper.convertValue(activity,ActivityDTO.class);
    }
}
