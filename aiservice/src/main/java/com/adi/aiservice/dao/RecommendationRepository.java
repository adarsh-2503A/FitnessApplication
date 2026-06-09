package com.adi.aiservice.dao;

import com.adi.aiservice.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation,Integer> {
    List<Recommendation> findByUserId(String userId);

    Recommendation findByActivityId(Integer activityId);
}
