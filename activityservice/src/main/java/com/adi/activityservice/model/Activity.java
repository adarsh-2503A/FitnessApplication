package com.adi.activityservice.model;

import com.adi.activityservice.config.ActivityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name="activities")
@Data
@Builder//implementing builder design pattern
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String userId;
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;
    private Integer duration;
    private Integer caloriesBurned;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name="additional_metrics",columnDefinition = "json")
    private Map<String,Object> additionalMetrics;
    private LocalDateTime startTime;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
