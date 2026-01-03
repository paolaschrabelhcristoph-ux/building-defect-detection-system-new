// src/main/java/org/example/learn/controller/WarningRuleController.java
// 修正此控制器以正确处理请求
package org.example.learn.controller;

import org.example.learn.entity.WarningRule;
import org.example.learn.service.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/warning-rules")
public class WarningRuleController {

    @Autowired
    private WarningService warningService;

    @GetMapping
    public String showWarningRulesPage(Model model) {
        List<WarningRule> warningRules = warningService.getAllWarningRules();
        model.addAttribute("warningRules", warningRules);
        return "warning-rules";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public WarningRule getWarningRule(@PathVariable Long id) {
        return warningService.getWarningRuleById(id);
    }

    @PostMapping("/add")
    @ResponseBody
    public String addWarningRule(@RequestBody WarningRule warningRule) {
        try {
            warningService.saveWarningRule(warningRule);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateWarningRule(@RequestBody WarningRule warningRule) {
        try {
            warningService.saveWarningRule(warningRule);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public String deleteWarningRule(@PathVariable Long id) {
        try {
            warningService.deleteWarningRule(id);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/toggle-active/{id}")
    @ResponseBody
    public String toggleWarningRuleActive(@PathVariable Long id) {
        try {
            warningService.toggleWarningRuleActive(id);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
}
