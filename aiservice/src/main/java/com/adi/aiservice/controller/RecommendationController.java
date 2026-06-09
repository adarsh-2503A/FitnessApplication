package com.adi.aiservice.controller;

import com.adi.aiservice.model.Recommendation;
import com.adi.aiservice.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendationsUsingUserId(@PathVariable String userId){
        return ResponseEntity.ok(recommendationService.getRecommendationsUsingUserId(userId));
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation> getRecommendationsUsingActivityId(@PathVariable Integer activityId){
        return ResponseEntity.ok(recommendationService.getRecommendationsUsingActivityId(activityId));
    }
}
