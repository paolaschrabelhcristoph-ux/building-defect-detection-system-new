// src/main/java/org/example/learn/entity/WarningRecord.java
package org.example.learn.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warning_record")
public class WarningRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "defect_record_id")
    private DefectRecord defectRecord;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private WarningRule rule;

    @Column(name = "warning_level")
    @Enumerated(EnumType.STRING)
    private WarningRule.DefectSeverityLevel warningLevel;

    @Column(name = "warning_title")
    private String warningTitle;

    @Column(name = "warning_content", columnDefinition = "TEXT")
    private String warningContent;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private WarningStatus status = WarningStatus.待处理;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @Column(name = "processed_by")
    private String processedBy;

    // 枚举类
    public enum WarningStatus {
        待处理, 已处理, 已忽略
    }

    // 构造函数
    public WarningRecord() {}

    public WarningRecord(DefectRecord defectRecord, WarningRule rule, 
                         WarningRule.DefectSeverityLevel warningLevel, 
                         String warningTitle, String warningContent) {
        this.defectRecord = defectRecord;
        this.rule = rule;
        this.warningLevel = warningLevel;
        this.warningTitle = warningTitle;
        this.warningContent = warningContent;
    }

    // Getter 和 Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DefectRecord getDefectRecord() { return defectRecord; }
    public void setDefectRecord(DefectRecord defectRecord) { this.defectRecord = defectRecord; }

    public WarningRule getRule() { return rule; }
    public void setRule(WarningRule rule) { this.rule = rule; }

    public WarningRule.DefectSeverityLevel getWarningLevel() { return warningLevel; }
    public void setWarningLevel(WarningRule.DefectSeverityLevel warningLevel) { this.warningLevel = warningLevel; }

    public String getWarningTitle() { return warningTitle; }
    public void setWarningTitle(String warningTitle) { this.warningTitle = warningTitle; }

    public String getWarningContent() { return warningContent; }
    public void setWarningContent(String warningContent) { this.warningContent = warningContent; }

    public WarningStatus getStatus() { return status; }
    public void setStatus(WarningStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getProcessedAt() { return processedAt; }
    public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }

    public String getProcessedBy() { return processedBy; }
    public void setProcessedBy(String processedBy) { this.processedBy = processedBy; }
}
