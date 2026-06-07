package com.adi.activityservice.controller;

import com.adi.activityservice.dto.ActivityDTO;
import com.adi.activityservice.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityDTO> addActivity(@RequestBody ActivityDTO activityDTO){
        return new ResponseEntity<>(activityService.addActivity(activityDTO), HttpStatus.CREATED);
    }
}
