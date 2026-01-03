package org.example.learn.controller;

import org.example.learn.entity.DefectRecord;
import org.example.learn.service.DefectRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/smart-warning")
public class SmartWarningController {

    @Autowired
    private DefectRecordService defectRecordService;

    @GetMapping
    public String showSmartWarningPage() {
        return "smart-warning";
    }

    @PostMapping("/generate-solution")
    @ResponseBody
    public Map<String, Object> generateSolution(@RequestBody Map<String, String> requestData) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 获取请求参数
            String defectType = requestData.get("defectType");
            String severityLevel = requestData.get("severityLevel");
            String detectionResult = requestData.get("detectionResult");
            String description = requestData.get("description");

            // 调用AI生成处理方案
            String solution = generateAISolution(defectType, severityLevel, detectionResult, description);

            response.put("solution", solution);
            response.put("status", "success");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }

        return response;
    }

    private String generateAISolution(String defectType, String severityLevel, String detectionResult, String description) {
        // 构建AI输入内容
        StringBuilder inputContent = new StringBuilder();
        inputContent.append("缺陷类型: ").append(defectType).append("\n");
        inputContent.append("危险程度: ").append(severityLevel).append("\n");
        if (detectionResult != null && !detectionResult.isEmpty()) {
            inputContent.append("检测结果: ").append(detectionResult).append("\n");
        }
        if (description != null && !description.isEmpty()) {
            inputContent.append("详细描述: ").append(description).append("\n");
        }

        // 这里可以集成真实的AI模型，当前为模拟实现
        String baseSolution = "针对" + defectType + "问题，";

        switch (severityLevel) {
            case "高":
                baseSolution += "由于危险程度为高，建议立即停止使用并进行全面修复，采用高强度材料进行加固处理。";
                break;
            case "中":
                baseSolution += "由于危险程度为中，建议尽快安排修复工作，采用标准修复工艺进行处理。";
                break;
            case "低":
                baseSolution += "由于危险程度为低，建议在下次维护时进行修复，采用常规修复工艺即可。";
                break;
            default:
                baseSolution += "建议根据具体情况制定相应的修复方案。";
        }

        // 根据检测结果和描述添加更具体的建议
        if (detectionResult != null && !detectionResult.isEmpty()) {
            baseSolution += "根据检测结果'" + detectionResult + "'，";
        }

        if (description != null && !description.isEmpty()) {
            baseSolution += "结合详细描述'" + description + "'，";
        }

        baseSolution += "建议采取以下措施：1. 现场安全评估 2. 专业修复 3. 后续监测。";

        return baseSolution;
    }
}
