// src/main/java/org/example/learn/controller/StatisticsController.java
package org.example.learn.controller;

import org.example.learn.service.DefectRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StatisticsController {

    @Autowired
    private DefectRecordService defectRecordService;

    @GetMapping("/statistics")
    public String showStatistics(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String dateRange,
            Model model) {

        // 获取统计数据 - 根据筛选条件
        List<org.example.learn.entity.DefectRecord> records;

        if (location != null && !location.trim().isEmpty()) {
            records = defectRecordService.getDefectRecordsByLocation(location);
        } else {
            records = defectRecordService.getAllDefectRecords();
        }

        // 过滤危险程度
        if (severity != null && !severity.trim().isEmpty()) {
            records = records.stream()
                    .filter(record -> severity.equals(record.getSeverityLevel()))
                    .collect(Collectors.toList());
        }

        // 计算统计指标
        int totalDefects = records.size();
        int highRiskCount = (int) records.stream()
                .filter(record -> "高".equals(record.getSeverityLevel()))
                .count();
        int processedCount = (int) records.stream()
                .filter(record -> "已处理".equals(record.getIsProcessed()))
                .count();
        int unprocessedCount = (int) records.stream()
                .filter(record -> "未处理".equals(record.getIsProcessed()))
                .count();

        // 添加到模型
        model.addAttribute("totalDefects", totalDefects);
        model.addAttribute("highRiskCount", highRiskCount);
        model.addAttribute("processedCount", processedCount);
        model.addAttribute("unprocessedCount", unprocessedCount);
        model.addAttribute("location", location);
        model.addAttribute("severity", severity);
        model.addAttribute("dateRange", dateRange);

        return "statistics";
    }

    @GetMapping("/statistics/chart-data")
    @ResponseBody
    public Map<String, Object> getChartData(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String severity) {

        List<org.example.learn.entity.DefectRecord> records;

        if (location != null && !location.trim().isEmpty()) {
            records = defectRecordService.getDefectRecordsByLocation(location);
        } else {
            records = defectRecordService.getAllDefectRecords();
        }

        // 过滤危险程度
        if (severity != null && !severity.trim().isEmpty()) {
            records = records.stream()
                    .filter(record -> severity.equals(record.getSeverityLevel()))
                    .collect(Collectors.toList());
        }

        Map<String, Object> result = new HashMap<>();

        // 缺陷类型分布数据
        Map<String, Long> defectTypeCount = records.stream()
                .collect(Collectors.groupingBy(
                        org.example.learn.entity.DefectRecord::getDefectType,
                        Collectors.counting()
                ));
        result.put("defectTypeDistribution", defectTypeCount);

        // 危险程度分布数据
        Map<String, Long> severityCount = records.stream()
                .collect(Collectors.groupingBy(
                        org.example.learn.entity.DefectRecord::getSeverityLevel,
                        Collectors.counting()
                ));
        result.put("severityDistribution", severityCount);

        // 处理状态统计
        Map<String, Long> processedStatus = records.stream()
                .collect(Collectors.groupingBy(
                        org.example.learn.entity.DefectRecord::getIsProcessed,
                        Collectors.counting()
                ));
        result.put("processedStatus", processedStatus);

        // 时间趋势数据 (按月统计)
        Map<String, Long> monthlyTrend = records.stream()
                .collect(Collectors.groupingBy(
                        record -> record.getDetectionDate().getMonthValue() + "月",
                        Collectors.counting()
                ));
        result.put("monthlyTrend", monthlyTrend);

        return result;
    }

}
