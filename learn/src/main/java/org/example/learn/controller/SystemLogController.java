// src/main/java/org/example/learn/controller/SystemLogController.java
package org.example.learn.controller;

import org.example.learn.entity.SystemLog;
import org.example.learn.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/system-logs")
public class SystemLogController {
    
    @Autowired
    private SystemLogService systemLogService;
    
    @GetMapping
    public String showSystemLogs(Model model) {
        List<SystemLog> logs = systemLogService.getAllLogs();
        model.addAttribute("logs", logs);
        return "system-logs";
    }
}
