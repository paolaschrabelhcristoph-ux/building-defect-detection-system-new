// src/main/java/org/example/learn/service/DefectRecordService.java
package org.example.learn.service;

import org.example.learn.entity.DefectRecord;
import org.example.learn.entity.SystemLog;
import org.example.learn.entity.WarningRecord;
import org.example.learn.repository.DefectRecordRepository;
import org.example.learn.repository.SystemLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DefectRecordService {

    @Autowired
    private DefectRecordRepository defectRecordRepository;

    @Autowired
    private SystemLogRepository systemLogRepository;

    @Autowired
    private WarningService warningService;

    public List<DefectRecord> getAllDefectRecords() {
        // 添加日志记录
        SystemLog log = new SystemLog("查询", "查询所有缺陷记录", null, null, SystemLog.OperationStatus.成功);
        systemLogRepository.save(log);

        return defectRecordRepository.findAllOrderByDateDesc();
    }

    public List<DefectRecord> getDefectRecordsByLocation(String location) {
        return defectRecordRepository.findByLocationContaining(location);
    }

    public List<DefectRecord> getDefectRecordsBySeverity(String severityLevel) {
        return defectRecordRepository.findBySeverityLevel(severityLevel);
    }

    public List<DefectRecord> getDefectRecordsByDateRange(LocalDate startDate, LocalDate endDate) {
        return defectRecordRepository.findByDateRange(startDate, endDate);
    }

    // 新增按检测结果查询的方法
    public List<DefectRecord> getDefectRecordsByDetectionResult(String detectionResult) {
        return defectRecordRepository.findByDetectionResultContaining(detectionResult);
    }

    public DefectRecord saveDefectRecord(DefectRecord defectRecord) {
        if (defectRecord.getDetectionDate() == null) {
            defectRecord.setDetectionDate(LocalDate.now());
        }

        DefectRecord savedRecord = defectRecordRepository.save(defectRecord);

        // 添加日志记录
        SystemLog log = new SystemLog("保存",
                "保存缺陷记录 - 地点: " + defectRecord.getLocation() +
                        ", 类型: " + defectRecord.getDefectType(),
                null, null, SystemLog.OperationStatus.成功);
        systemLogRepository.save(log);

        // 触发预警检查
        warningService.createWarningRecordForDefect(savedRecord);

        return savedRecord;
    }

    public void deleteDefectRecord(Long id) {
        DefectRecord record = defectRecordRepository.findById(id).orElse(null);
        if (record != null) {
            // 添加日志记录
            SystemLog log = new SystemLog("删除",
                    "删除缺陷记录 - ID: " + id +
                            ", 地点: " + record.getLocation(),
                    null, null, SystemLog.OperationStatus.成功);
            systemLogRepository.save(log);
        }

        defectRecordRepository.deleteById(id);
    }

    public DefectRecord getDefectRecordById(Long id) {
        return defectRecordRepository.findById(id).orElse(null);
    }

    public List<DefectRecord> getDefectRecordsByIsProcessed(String isProcessed) {
        return defectRecordRepository.findByIsProcessed(isProcessed);
    }

    // 新增按缺陷类型查询的方法
    public List<DefectRecord> getDefectRecordsByDefectType(String defectType) {
        return defectRecordRepository.findByDefectType(defectType);
    }
}
