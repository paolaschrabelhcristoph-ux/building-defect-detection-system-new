// src/main/java/org/example/learn/controller/WarningManagementController.java
package org.example.learn.controller;

import org.example.learn.entity.WarningRecord;
import org.example.learn.entity.WarningRule;
import org.example.learn.service.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/warning-management")
public class WarningManagementController {
    
    @Autowired
    private WarningService warningService;
    
    @GetMapping
    public String showWarningManagementPage(Model model) {
        List<WarningRecord> warningRecords = warningService.getAllWarningRecords();
        model.addAttribute("warningRecords", warningRecords);
        return "warning-management";
    }
    
    @PostMapping("/update-warning-status")
    @ResponseBody
    public String updateWarningStatus(@RequestParam Long id, 
                                      @RequestParam String status,
                                      @RequestParam String processedBy) {
        try {
            WarningRecord.WarningStatus warningStatus = WarningRecord.WarningStatus.valueOf(status);
            warningService.updateWarningRecordStatus(id, warningStatus, processedBy);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
    
    @GetMapping("/rules")
    public String showWarningRulesPage(Model model) {
        List<WarningRule> warningRules = warningService.getActiveWarningRules();
        model.addAttribute("warningRules", warningRules);
        return "warning-rules";
    }
}
