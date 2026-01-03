package org.example.learn.entity;

// src/main/java/org/example/learn/entity/SystemLog.java

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "system_log")
public class SystemLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "operation_type", length = 50)
    private String operationType;

    @Column(name = "operation_content", columnDefinition = "TEXT")
    private String operationContent;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "user_agent", columnDefinition = "TEXT")
    private String userAgent;

    @Column(name = "status", length = 10)
    @Enumerated(EnumType.STRING)
    private OperationStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum OperationStatus {
        成功, 失败
    }

    // 构造函数
    public SystemLog() {}

    public SystemLog(String operationType, String operationContent, String ipAddress, String userAgent, OperationStatus status) {
        this.operationType = operationType;
        this.operationContent = operationContent;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.status = status;
    }

    // Getter 和 Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getOperationType() { return operationType; }
    public void setOperationType(String operationType) { this.operationType = operationType; }

    public String getOperationContent() { return operationContent; }
    public void setOperationContent(String operationContent) { this.operationContent = operationContent; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }

    public OperationStatus getStatus() { return status; }
    public void setStatus(OperationStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
