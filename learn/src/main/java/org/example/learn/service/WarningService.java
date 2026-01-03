// src/main/java/org/example/learn/service/WarningService.java
package org.example.learn.service;

import org.example.learn.entity.WarningRecord;
import org.example.learn.entity.WarningRule;
import org.example.learn.entity.DefectRecord;
import org.example.learn.repository.WarningRecordRepository;
import org.example.learn.repository.WarningRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WarningService {

    @Autowired
    private WarningRuleRepository warningRuleRepository;

    @Autowired
    private WarningRecordRepository warningRecordRepository;

    public List<WarningRule> getActiveWarningRules() {
        return warningRuleRepository.findByIsActiveTrue();
    }

    public List<WarningRule> getWarningRulesByDefectType(String defectType) {
        return warningRuleRepository.findByDefectType(defectType);
    }

    public List<WarningRecord> getWarningRecordsByStatus(WarningRecord.WarningStatus status) {
        return warningRecordRepository.findByStatus(status);
    }

    public WarningRecord createWarningRecord(WarningRecord warningRecord) {
        return warningRecordRepository.save(warningRecord);
    }

    public void updateWarningRecordStatus(Long id, WarningRecord.WarningStatus status, String processedBy) {
        WarningRecord record = warningRecordRepository.findById(id).orElse(null);
        if (record != null) {
            record.setStatus(status);
            record.setProcessedAt(LocalDateTime.now());
            record.setProcessedBy(processedBy);
            warningRecordRepository.save(record);
        }
    }

    public List<WarningRecord> getAllWarningRecords() {
        return warningRecordRepository.findAllOrderByDateDesc();
    }

    // 新增方法
    public WarningRule saveWarningRule(WarningRule warningRule) {
        return warningRuleRepository.save(warningRule);
    }

    public void deleteWarningRule(Long id) {
        warningRuleRepository.deleteById(id);
    }

    public void toggleWarningRuleActive(Long id) {
        WarningRule rule = warningRuleRepository.findById(id).orElse(null);
        if (rule != null) {
            rule.setIsActive(!rule.getIsActive());
            warningRuleRepository.save(rule);
        }
    }

    public WarningRule getWarningRuleById(Long id) {
        return warningRuleRepository.findById(id).orElse(null);
    }

    public List<WarningRule> getAllWarningRules() {
        return warningRuleRepository.findAll();
    }

    // 根据缺陷记录创建预警记录
    public void createWarningRecordForDefect(DefectRecord defectRecord) {
        List<WarningRule> rules = getActiveWarningRules();

        for (WarningRule rule : rules) {
            // 检查规则是否适用于当前缺陷
            if (shouldTriggerWarning(defectRecord, rule)) {
                WarningRecord warningRecord = new WarningRecord();
                warningRecord.setDefectRecord(defectRecord);
                warningRecord.setRule(rule);
                warningRecord.setWarningLevel(defectRecord.getSeverityLevel() != null ?
                        WarningRule.DefectSeverityLevel.valueOf(defectRecord.getSeverityLevel()) :
                        WarningRule.DefectSeverityLevel.中);
                warningRecord.setWarningTitle("缺陷预警: " + defectRecord.getDefectType());
                warningRecord.setWarningContent(rule.getWarningMessage() +
                        " - 缺陷地点: " + defectRecord.getLocation() +
                        " - 检测日期: " + defectRecord.getDetectionDate());

                createWarningRecord(warningRecord);
            }
        }
    }

    private boolean shouldTriggerWarning(DefectRecord defectRecord, WarningRule rule) {
        // 根据规则条件判断是否触发预警
        if (rule.getDefectType() != null && !rule.getDefectType().isEmpty()) {
            if (!defectRecord.getDefectType().equals(rule.getDefectType())) {
                return false;
            }
        }

        if (rule.getSeverityLevel() != null) {
            if (!defectRecord.getSeverityLevel().equals(rule.getSeverityLevel().name())) {
                return false;
            }
        }

        return true;
    }
}
