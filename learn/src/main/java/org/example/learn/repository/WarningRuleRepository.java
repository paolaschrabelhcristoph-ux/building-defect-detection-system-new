// src/main/java/org/example/learn/repository/WarningRuleRepository.java
package org.example.learn.repository;

import org.example.learn.entity.WarningRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarningRuleRepository extends JpaRepository<WarningRule, Long> {
    List<WarningRule> findByIsActiveTrue();
    List<WarningRule> findByDefectType(String defectType);
    List<WarningRule> findBySeverityLevel(WarningRule.DefectSeverityLevel severityLevel);
}
