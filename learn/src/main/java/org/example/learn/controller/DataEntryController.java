// src/main/java/org/example/learn/controller/DataEntryController.java
package org.example.learn.controller;

import org.example.learn.entity.DefectRecord;
import org.example.learn.service.DefectRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/data-entry")
public class DataEntryController {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private DefectRecordService defectRecordService;

    @GetMapping("/entry")
    public String showDataEntryPage() {
        return "data-entry";
    }

    @GetMapping("/submit-success")
    public String showSubmitSuccess() {
        return "submit-success";
    }

    @PostMapping("/submit")
    public String submitDataEntry(
            @RequestParam("location") String location,
            @RequestParam("defectType") String defectType,
            @RequestParam("severityLevel") String severityLevel,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("detectionDate") LocalDate detectionDate,
            @RequestParam(value = "detectionResult", required = false) String detectionResult,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "isProcessed", required = false) String isProcessed,
            RedirectAttributes redirectAttributes) {

        try {
            // 创建缺陷记录对象
            DefectRecord record = new DefectRecord();
            record.setLocation(location);
            record.setDefectType(defectType);
            record.setSeverityLevel(severityLevel);
            record.setDescription(description);
            record.setDetectionDate(detectionDate);
            record.setDetectionResult(detectionResult); // 新增检测结果字段
            record.setIsProcessed(isProcessed != null ? isProcessed : "未处理");
            // 如果上传了图片，则保存图片
            if (image != null && !image.isEmpty()) {
                // 创建上传目录
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // 生成唯一文件名
                String originalFilename = image.getOriginalFilename();
                String extension = originalFilename != null ?
                        originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
                String uniqueFilename = UUID.randomUUID().toString() + extension;

                // 保存图片
                Path imagePath = uploadPath.resolve(uniqueFilename);
                Files.write(imagePath, image.getBytes());

                // 设置图片路径
                record.setImagePath("/uploads/" + uniqueFilename);
            }

            // 保存到数据库
            defectRecordService.saveDefectRecord(record);

            // 使用重定向属性传递成功消息
            redirectAttributes.addFlashAttribute("success", "缺陷数据录入成功！");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "图片上传失败: " + e.getMessage());
            return "redirect:/data-entry/entry";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "数据录入失败: " + e.getMessage());
            return "redirect:/data-entry/entry";
        }

        // 成功后重定向到提交成功页面
        return "redirect:/data-entry/submit-success";
    }
}
