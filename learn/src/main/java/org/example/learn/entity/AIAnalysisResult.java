// src/main/java/org/example/learn/entity/AIAnalysisResult.java
package org.example.learn.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_analysis_result")
public class AIAnalysisResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "defect_record_id", nullable = false)
    private Long defectRecordId;

    @Column(name = "analysis_method", length = 100)
    private String analysisMethod;

    @Column(name = "confidence_score") // 移除了 precision 和 scale
    private Double confidenceScore;

    @Column(name = "defect_classification", columnDefinition = "JSON")
    private String defectClassification; // JSON格式

    @Column(name = "severity_prediction", length = 10)
    @Enumerated(EnumType.STRING)
    private DefectSeverityLevel severityPrediction;

    @Column(name = "solution_suggestion", columnDefinition = "TEXT")
    private String solutionSuggestion;

    @Column(name = "processing_time") // 移除了 precision 和 scale
    private Double processingTime;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 枚举类
    public enum DefectSeverityLevel {
        高, 中, 低
    }

    // 构造函数
    public AIAnalysisResult() {}

    public AIAnalysisResult(Long defectRecordId, String analysisMethod, Double confidenceScore,
                            String defectClassification, DefectSeverityLevel severityPrediction,
                            String solutionSuggestion, Double processingTime) {
        this.defectRecordId = defectRecordId;
        this.analysisMethod = analysisMethod;
        this.confidenceScore = confidenceScore;
        this.defectClassification = defectClassification;
        this.severityPrediction = severityPrediction;
        this.solutionSuggestion = solutionSuggestion;
        this.processingTime = processingTime;
    }

    // Getter 和 Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDefectRecordId() { return defectRecordId; }
    public void setDefectRecordId(Long defectRecordId) { this.defectRecordId = defectRecordId; }

    public String getAnalysisMethod() { return analysisMethod; }
    public void setAnalysisMethod(String analysisMethod) { this.analysisMethod = analysisMethod; }

    public Double getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(Double confidenceScore) { this.confidenceScore = confidenceScore; }

    public String getDefectClassification() { return defectClassification; }
    public void setDefectClassification(String defectClassification) { this.defectClassification = defectClassification; }

    public DefectSeverityLevel getSeverityPrediction() { return severityPrediction; }
    public void setSeverityPrediction(DefectSeverityLevel severityPrediction) { this.severityPrediction = severityPrediction; }

    public String getSolutionSuggestion() { return solutionSuggestion; }
    public void setSolutionSuggestion(String solutionSuggestion) { this.solutionSuggestion = solutionSuggestion; }

    public Double getProcessingTime() { return processingTime; }
    public void setProcessingTime(Double processingTime) { this.processingTime = processingTime; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
