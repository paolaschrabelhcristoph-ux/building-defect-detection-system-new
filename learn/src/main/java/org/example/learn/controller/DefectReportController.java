// src/main/java/org/example/learn/controller/DefectReportController.java
package org.example.learn.controller;

import org.example.learn.entity.DefectRecord;
import org.example.learn.service.DefectRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/report")
public class DefectReportController {

    @Autowired
    private DefectRecordService defectRecordService;

    @GetMapping("/generate")
    public String showReportPage(Model model) {
        List<DefectRecord> defectRecords = defectRecordService.getAllDefectRecords();
        model.addAttribute("defectRecords", defectRecords);
        return "defect-report"; // 确保此文件名与模板文件名一致
    }
    @PostMapping("/search")
    public String searchDefects(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String detectionResult,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String isProcessed, // 新增参数
            Model model) {

        List<DefectRecord> defectRecords;

        if (location != null && !location.trim().isEmpty()) {
            defectRecords = defectRecordService.getDefectRecordsByLocation(location);
        } else if (severity != null && !severity.trim().isEmpty()) {
            defectRecords = defectRecordService.getDefectRecordsBySeverity(severity);
        } else if (detectionResult != null && !detectionResult.trim().isEmpty()) {
            defectRecords = defectRecordService.getDefectRecordsByDetectionResult(detectionResult);
        } else if (startDate != null && !startDate.trim().isEmpty() &&
                endDate != null && !endDate.trim().isEmpty()) {
            defectRecords = defectRecordService.getDefectRecordsByDateRange(
                    LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else if (isProcessed != null && !isProcessed.trim().isEmpty()) {
            defectRecords = defectRecordService.getDefectRecordsByIsProcessed(isProcessed);
        } else {
            defectRecords = defectRecordService.getAllDefectRecords();
        }

        model.addAttribute("defectRecords", defectRecords);
        return "defect-report";
    }


    @PostMapping("/add")
    @ResponseBody
    public String addDefectRecord(@RequestBody DefectRecord defectRecord) {
        try {
            defectRecordService.saveDefectRecord(defectRecord);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
}
