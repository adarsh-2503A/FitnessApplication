package com.adi.activityservice.controller;

import com.adi.activityservice.dto.ActivityDTO;
import com.adi.activityservice.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityDTO> addActivity(@RequestBody ActivityDTO activityDTO){
        return new ResponseEntity<>(activityService.addActivity(activityDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ActivityDTO>> getActivitiesOfUser(@RequestHeader("X-User-ID") String userId){
        return ResponseEntity.ok(activityService.getActivitiesOfUser(userId));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable Integer activityId){
        return ResponseEntity.ok(activityService.getActivityById(activityId));
    }
}
