package com.adi.activityservice.repository;

import com.adi.activityservice.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity,Integer> {

}
