package com.adi.activityservice.repository;

import com.adi.activityservice.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Integer> {
    List<Activity> findByUserId(String userId);
}
