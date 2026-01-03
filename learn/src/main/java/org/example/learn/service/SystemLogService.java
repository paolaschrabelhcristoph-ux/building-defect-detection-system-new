// src/main/java/org/example/learn/service/SystemLogService.java
package org.example.learn.service;

import org.example.learn.entity.SystemLog;
import org.example.learn.repository.SystemLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemLogService {
    
    @Autowired
    private SystemLogRepository systemLogRepository;
    
    public List<SystemLog> getAllLogs() {
        return systemLogRepository.findAllOrderByDateDesc();
    }
    
    public List<SystemLog> getLogsByUserId(Long userId) {
        return systemLogRepository.findByUserId(userId);
    }
    
    public List<SystemLog> getLogsByOperationType(String operationType) {
        return systemLogRepository.findByOperationType(operationType);
    }
    
    public SystemLog saveLog(SystemLog log) {
        return systemLogRepository.save(log);
    }
    
    public void deleteLog(Long id) {
        systemLogRepository.deleteById(id);
    }
}
