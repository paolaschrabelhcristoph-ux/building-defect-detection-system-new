// src/main/java/org/example/learn/entity/WarningRule.java
package org.example.learn.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warning_rule")
public class WarningRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_name", nullable = false)
    private String ruleName;

    @Column(name = "defect_type")
    private String defectType;

    @Column(name = "severity_level")
    @Enumerated(EnumType.STRING)
    private DefectSeverityLevel severityLevel;

    @Column(name = "condition_expression", columnDefinition = "TEXT")
    private String conditionExpression;

    @Column(name = "warning_message", columnDefinition = "TEXT")
    private String warningMessage;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "priority")
    private Integer priority = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 枚举类
    public enum DefectSeverityLevel {
        高, 中, 低
    }

    // 构造函数
    public WarningRule() {}

    public WarningRule(String ruleName, String defectType, DefectSeverityLevel severityLevel,
                       String conditionExpression, String warningMessage) {
        this.ruleName = ruleName;
        this.defectType = defectType;
        this.severityLevel = severityLevel;
        this.conditionExpression = conditionExpression;
        this.warningMessage = warningMessage;
    }

    // Getter 和 Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public String getDefectType() { return defectType; }
    public void setDefectType(String defectType) { this.defectType = defectType; }

    public DefectSeverityLevel getSeverityLevel() { return severityLevel; }
    public void setSeverityLevel(DefectSeverityLevel severityLevel) { this.severityLevel = severityLevel; }

    public String getConditionExpression() { return conditionExpression; }
    public void setConditionExpression(String conditionExpression) { this.conditionExpression = conditionExpression; }

    public String getWarningMessage() { return warningMessage; }
    public void setWarningMessage(String warningMessage) { this.warningMessage = warningMessage; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
