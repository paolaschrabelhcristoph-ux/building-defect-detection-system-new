// src/main/java/org/example/learn/entity/DefectRecord.java
package org.example.learn.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "defect_record")
public class DefectRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String location; // 缺陷地点

    @Column(nullable = false)
    private String defectType; // 缺陷类型

    @Column(nullable = false)
    private String severityLevel; // 危险程度

    @Column(length = 1000)
    private String description; // 缺陷详细描述

    @Column(nullable = false)
    private LocalDate detectionDate; // 记录日期

    private String imagePath; // 缺陷图片路径

    // 新增检测结果字段
    @Column(length = 2000)
    private String detectionResult; // 检测结果

    @Column(nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    private java.time.LocalDateTime updatedAt;

    // 构造函数
    public DefectRecord() {}

    public DefectRecord(String location, String defectType, String severityLevel,
                        String description, LocalDate detectionDate, String imagePath) {
        this.location = location;
        this.defectType = defectType;
        this.severityLevel = severityLevel;
        this.description = description;
        this.detectionDate = detectionDate;
        this.imagePath = imagePath;
    }

    // 新增是否已处理字段，默认值为"未处理"
    @Column(nullable = false)
    private String isProcessed = "未处理"; // 默认值

    // Getter 和 Setter
    public String getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(String isProcessed) {
        this.isProcessed = isProcessed;
    }


    // Getter 和 Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDefectType() { return defectType; }
    public void setDefectType(String defectType) { this.defectType = defectType; }

    public String getSeverityLevel() { return severityLevel; }
    public void setSeverityLevel(String severityLevel) { this.severityLevel = severityLevel; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDetectionDate() { return detectionDate; }
    public void setDetectionDate(LocalDate detectionDate) { this.detectionDate = detectionDate; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    // 新增检测结果的getter和setter
    public String getDetectionResult() { return detectionResult; }
    public void setDetectionResult(String detectionResult) { this.detectionResult = detectionResult; }

    public java.time.LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.time.LocalDateTime createdAt) { this.createdAt = createdAt; }

    public java.time.LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(java.time.LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
