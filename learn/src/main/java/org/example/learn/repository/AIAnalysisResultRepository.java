// src/main/java/org/example/learn/repository/AIAnalysisResultRepository.java
package org.example.learn.repository;

import org.example.learn.entity.AIAnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AIAnalysisResultRepository extends JpaRepository<AIAnalysisResult, Long> {
    List<AIAnalysisResult> findByDefectRecordId(Long defectRecordId);
    
    @Query("SELECT a FROM AIAnalysisResult a WHERE a.severityPrediction = :severityLevel")
    List<AIAnalysisResult> findBySeverityPrediction(@Param("severityLevel") AIAnalysisResult.DefectSeverityLevel severityLevel);
}
