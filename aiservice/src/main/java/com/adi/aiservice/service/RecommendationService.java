package com.adi.aiservice.service;

import com.adi.aiservice.model.Recommendation;

import java.util.List;

public interface RecommendationService {

    List<Recommendation> getRecommendationsUsingUserId(String userId);

    Recommendation getRecommendationsUsingActivityId(Integer activityId);
}
