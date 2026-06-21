package com.adi.aiservice.service;

import com.adi.aiservice.dao.RecommendationRepository;
import com.adi.aiservice.model.Recommendation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RecommendationServiceImpl implements RecommendationService{

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Override
    public List<Recommendation> getRecommendationsUsingUserId(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    @Override
    @Cacheable(value = "recommendation", key = "#activityId") //use @CacheEvict on top of delete service, @CachePut on top of update service
    public Recommendation getRecommendationsUsingActivityId(Integer activityId) {
        return recommendationRepository.findByActivityId(activityId);
    }
}
