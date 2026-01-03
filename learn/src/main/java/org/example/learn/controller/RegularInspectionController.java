// src/main/java/org/example/learn/controller/RegularInspectionController.java
package org.example.learn.controller;

import org.example.learn.entity.DefectRecord;
import org.example.learn.service.DefectRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/regular-inspection")  // 基础路径
public class RegularInspectionController {

    @Autowired
    private DefectRecordService defectRecordService;

    @GetMapping  // 这将映射到 /regular-inspection
    public String showRegularInspectionPage(Model model) {
        // 获取所有未处理的缺陷记录
        List<DefectRecord> unprocessedRecords = defectRecordService.getDefectRecordsByIsProcessed("未处理");
        model.addAttribute("defectRecords", unprocessedRecords);
        return "regular-inspection";
    }

    @PostMapping("/update-status")
    @ResponseBody
    public String updateStatus(@RequestParam Long id, @RequestParam String status) {
        try {
            DefectRecord record = defectRecordService.getDefectRecordById(id);
            if (record != null) {
                record.setIsProcessed(status);
                defectRecordService.saveDefectRecord(record);
                return "success";
            }
            return "error: 记录不存在";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/search")
    public String searchDefects(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String defectType,
            Model model) {

        List<DefectRecord> defectRecords;

        // 构建查询条件
        if (location != null && !location.trim().isEmpty()) {
            defectRecords = defectRecordService.getDefectRecordsByLocation(location);
        } else if (severity != null && !severity.trim().isEmpty()) {
            defectRecords = defectRecordService.getDefectRecordsBySeverity(severity);
        } else if (defectType != null && !defectType.trim().isEmpty()) {
            defectRecords = defectRecordService.getDefectRecordsByDefectType(defectType);
        } else {
            // 如果没有筛选条件，获取所有未处理的记录
            defectRecords = defectRecordService.getDefectRecordsByIsProcessed("未处理");
        }

        // 进一步筛选未处理的记录
        defectRecords = defectRecords.stream()
                .filter(record -> "未处理".equals(record.getIsProcessed()))
                .collect(Collectors.toList());

        model.addAttribute("defectRecords", defectRecords);
        model.addAttribute("location", location);
        model.addAttribute("severity", severity);
        model.addAttribute("defectType", defectType);

        return "regular-inspection";
    }
}
