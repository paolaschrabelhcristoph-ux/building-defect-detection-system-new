// src/main/java/org/example/learn/service/AIAnalysisResultService.java
package org.example.learn.service;

import org.example.learn.entity.AIAnalysisResult;
import org.example.learn.repository.AIAnalysisResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIAnalysisResultService {
    
    @Autowired
    private AIAnalysisResultRepository aiAnalysisResultRepository;
    
    public List<AIAnalysisResult> getResultsByDefectRecordId(Long defectRecordId) {
        return aiAnalysisResultRepository.findByDefectRecordId(defectRecordId);
    }
    
    public List<AIAnalysisResult> getResultsBySeverityPrediction(AIAnalysisResult.DefectSeverityLevel severityLevel) {
        return aiAnalysisResultRepository.findBySeverityPrediction(severityLevel);
    }
    
    public AIAnalysisResult saveResult(AIAnalysisResult result) {
        return aiAnalysisResultRepository.save(result);
    }
    
    public void deleteResult(Long id) {
        aiAnalysisResultRepository.deleteById(id);
    }
}
