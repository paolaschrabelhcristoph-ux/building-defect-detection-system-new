// src/main/java/org/example/learn/controller/DefectDetectionController.java
package org.example.learn.controller;

import org.example.learn.entity.DefectRecord;
import org.example.learn.service.DefectRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class DefectDetectionController {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private DefectRecordService defectRecordService;

    @GetMapping("/defect-detection")
    public String showDefectDetectionPage() {
        return "defect-detection";
    }

    // 模拟缺陷检测结果
    private List<String> simulateDefectDetection() {
        List<String> defects = new ArrayList<>();
        defects.add("墙体裂缝 - 位置: 左上角，长度: 15cm");
        defects.add("表面脱落 - 位置: 右侧中部，面积: 约0.5平方米");
        defects.add("渗水痕迹 - 位置: 底部，范围: 20x30cm");
        return defects;
    }

    @PostMapping("/upload-image")
    public String uploadImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam(value = "defectLocation", required = false) String defectLocation,
            @RequestParam(value = "defectDescription", required = false) String defectDescription,
            Model model) {
        if (image.isEmpty()) {
            model.addAttribute("error", "请选择要上传的图片");
            return "defect-detection";
        }

        try {
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

            // 保存原图
            Path originalFilePath = uploadPath.resolve(uniqueFilename);
            Files.write(originalFilePath, image.getBytes());

            // 生成处理后的图片（模拟）
            String processedFilename = "processed_" + uniqueFilename;
            Path processedFilePath = uploadPath.resolve(processedFilename);
            Files.write(processedFilePath, image.getBytes());

            // 模拟缺陷检测结果
            List<String> defects = simulateDefectDetection();

            // 创建缺陷记录并保存到数据库
            for (String defect : defects) {
                DefectRecord record = new DefectRecord();
                // 使用表单输入的缺陷地点，如果没有输入则使用默认值
                record.setLocation(defectLocation != null && !defectLocation.trim().isEmpty() ?
                        defectLocation : "建筑工地A区");
                record.setDefectType(defect.split(" - ")[0]);
                record.setSeverityLevel("中"); // 实际应用中应根据算法判断
                // 使用表单输入的详细描述，如果没有输入则使用缺陷信息
                record.setDescription(defectDescription != null && !defectDescription.trim().isEmpty() ?
                        defectDescription : defect);
                record.setDetectionDate(LocalDate.now());
                record.setImagePath("/uploads/" + uniqueFilename);
                // 新增检测结果
                record.setDetectionResult("AI检测结果为" + defect + "，建议立即修复");
                defectRecordService.saveDefectRecord(record);
            }

            // 使用相对路径或绝对路径确保图片能被访问
            model.addAttribute("originalImagePath", "/uploads/" + uniqueFilename);
            model.addAttribute("processedImagePath", "/uploads/" + processedFilename);
            model.addAttribute("defects", defects);
            model.addAttribute("defectCount", defects.size());

        } catch (IOException e) {
            model.addAttribute("error", "上传失败: " + e.getMessage());
            return "defect-detection";
        }

        return "defect-detection";
    }
}
