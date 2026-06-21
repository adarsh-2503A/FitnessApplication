package com.adi.aiservice.model;

import com.adi.aiservice.config.ActivityType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recommendations")
@Data
public class Recommendation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private Integer activityId;
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "analysis",columnDefinition = "json")
    private AnalysisRecord analysis;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "improvements",columnDefinition = "json")
    private List<ImprovementRecord> improvements;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "suggestions",columnDefinition = "json")
    private List<SuggestionRecord> suggestions;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "safety",columnDefinition = "json")
    private List<String> safety;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
