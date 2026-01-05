/*
Navicat MySQL Data Transfer

Source Server         : wd
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : login_db

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2026-01-05 09:11:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ai_analysis_result`
-- ----------------------------
DROP TABLE IF EXISTS `ai_analysis_result`;
CREATE TABLE `ai_analysis_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `defect_record_id` bigint(20) NOT NULL COMMENT '缺陷记录ID',
  `analysis_method` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分析方法',
  `confidence_score` double DEFAULT NULL,
  `defect_classification` json DEFAULT NULL COMMENT '缺陷分类结果',
  `severity_prediction` enum('高','中','低') COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '危险程度预测',
  `solution_suggestion` text COLLATE utf8mb4_unicode_ci COMMENT '处理方案建议',
  `processing_time` double DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `defect_record_id` (`defect_record_id`),
  CONSTRAINT `ai_analysis_result_ibfk_1` FOREIGN KEY (`defect_record_id`) REFERENCES `defect_record` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of ai_analysis_result
-- ----------------------------

-- ----------------------------
-- Table structure for `defect_record`
-- ----------------------------
DROP TABLE IF EXISTS `defect_record`;
CREATE TABLE `defect_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `location` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '缺陷地点',
  `defect_type` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '缺陷类型',
  `severity_level` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '危险程度',
  `description` text COLLATE utf8mb4_unicode_ci COMMENT '缺陷详细描述',
  `detection_date` date NOT NULL COMMENT '记录日期',
  `image_path` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '缺陷图片路径',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `detection_result` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_processed` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of defect_record
-- ----------------------------
INSERT INTO `defect_record` VALUES ('1', '辽宁工程技术大学葫芦岛校区', '墙体裂缝', '低', '校医院南面三楼到四楼第6个窗户之间', '2025-12-30', '/uploads/cadff689-792e-4b2a-a57d-b6b822f14ced.jpg', null, '2026-01-01 17:46:23', '裂缝长度15cm，深度6cm，危险系数较高', '未处理');
INSERT INTO `defect_record` VALUES ('2', '辽宁工程技术大学葫芦岛校区', '墙体裂缝', '高', '校医院北面体检室', '2025-12-30', '/uploads/3efa5093-db74-4cca-b4e9-bd1188a0dca7.jpg', null, '2026-01-01 17:46:25', '裂缝长度15cm，深度6cm，危险系数较高', '未处理');
INSERT INTO `defect_record` VALUES ('3', '建筑工地A区', '墙体裂缝', '中', '墙体裂缝 - 位置: 左上角，长度: 15cm', '2025-12-30', '/uploads/3c1f9632-b143-4df5-89e8-f3c1e0ef33a1.jpg', null, '2026-01-01 17:46:27', 'AI检测结果为墙体裂缝 - 位置: 左上角，长度: 15cm，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('4', '建筑工地A区', '表面脱落', '中', '表面脱落 - 位置: 右侧中部，面积: 约0.5平方米', '2025-12-30', '/uploads/3c1f9632-b143-4df5-89e8-f3c1e0ef33a1.jpg', null, '2026-01-01 17:46:29', 'AI检测结果为表面脱落 - 位置: 右侧中部，面积: 约0.5平方米，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('5', '建筑工地A区', '渗水痕迹', '中', '渗水痕迹 - 位置: 底部，范围: 20x30cm', '2025-12-30', '/uploads/3c1f9632-b143-4df5-89e8-f3c1e0ef33a1.jpg', null, '2026-01-01 17:46:30', 'AI检测结果为渗水痕迹 - 位置: 底部，范围: 20x30cm，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('6', '辽宁工程技术大学', '墙体裂缝', '中', '体育场', '2025-12-30', '/uploads/e570f955-3d5d-4cb1-8d9a-4e1d74d9f16b.jpg', null, '2026-01-01 17:46:31', 'AI检测结果为墙体裂缝 - 位置: 左上角，长度: 15cm，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('7', '辽宁工程技术大学', '表面脱落', '中', '体育场', '2025-12-30', '/uploads/e570f955-3d5d-4cb1-8d9a-4e1d74d9f16b.jpg', null, '2026-01-01 17:46:32', 'AI检测结果为表面脱落 - 位置: 右侧中部，面积: 约0.5平方米，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('8', '辽宁工程技术大学', '渗水痕迹', '中', '体育场', '2025-12-30', '/uploads/e570f955-3d5d-4cb1-8d9a-4e1d74d9f16b.jpg', null, '2026-01-01 17:46:34', 'AI检测结果为渗水痕迹 - 位置: 底部，范围: 20x30cm，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('9', '辽宁工程技术大学', '墙体裂缝', '中', '北门', '2025-12-30', '/uploads/fb3f9d58-0beb-46a6-8471-25ca9c5a15b1.jpg', null, '2026-01-01 17:46:37', 'AI检测结果为墙体裂缝 - 位置: 左上角，长度: 15cm，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('10', '辽宁工程技术大学', '表面脱落', '中', '北门', '2025-12-30', '/uploads/fb3f9d58-0beb-46a6-8471-25ca9c5a15b1.jpg', null, '2026-01-01 17:46:38', 'AI检测结果为表面脱落 - 位置: 右侧中部，面积: 约0.5平方米，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('11', '辽宁工程技术大学', '渗水痕迹', '中', '北门', '2025-12-30', '/uploads/fb3f9d58-0beb-46a6-8471-25ca9c5a15b1.jpg', null, '2026-01-01 17:46:40', 'AI检测结果为渗水痕迹 - 位置: 底部，范围: 20x30cm，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('12', '辽宁工程技术大学', '墙体裂缝', '中', '南门', '2025-12-31', '/uploads/ac024c21-f8fa-4374-8466-0b9818f66e00.jpg', null, '2026-01-01 17:46:41', 'AI检测结果为墙体裂缝 - 位置: 左上角，长度: 15cm，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('13', '辽宁工程技术大学', '表面脱落', '中', '南门', '2025-12-31', '/uploads/ac024c21-f8fa-4374-8466-0b9818f66e00.jpg', null, '2026-01-01 17:46:43', 'AI检测结果为表面脱落 - 位置: 右侧中部，面积: 约0.5平方米，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('14', '辽宁工程技术大学', '渗水痕迹', '中', '南门', '2025-12-31', '/uploads/ac024c21-f8fa-4374-8466-0b9818f66e00.jpg', null, '2026-01-01 17:46:46', 'AI检测结果为渗水痕迹 - 位置: 底部，范围: 20x30cm，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('15', '辽宁工程技术大学', '结构变形', '高', '北门向左十米处位置', '2025-12-31', '/uploads/1c9c1666-c659-409b-aea4-33df9e42a72b.jpg', null, '2026-01-01 17:46:46', '结构变形严重', '未处理');
INSERT INTO `defect_record` VALUES ('16', '辽宁工程技术大学', '墙体裂缝', '中', 'jjjj', '2025-12-31', '/uploads/cde076fb-45fa-4cb1-bc4d-5c8f838f588e.jpg', null, '2026-01-01 17:46:45', 'AI检测结果为墙体裂缝 - 位置: 左上角，长度: 15cm，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('17', '辽宁工程技术大学', '表面脱落', '中', 'jjjj', '2025-12-31', '/uploads/cde076fb-45fa-4cb1-bc4d-5c8f838f588e.jpg', null, '2026-01-01 17:46:48', 'AI检测结果为表面脱落 - 位置: 右侧中部，面积: 约0.5平方米，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('18', '辽宁工程技术大学', '渗水痕迹', '中', 'jjjj', '2025-12-31', '/uploads/cde076fb-45fa-4cb1-bc4d-5c8f838f588e.jpg', null, '2026-01-01 17:46:47', 'AI检测结果为渗水痕迹 - 位置: 底部，范围: 20x30cm，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('19', '辽宁工程技术大学', '墙体裂缝', '中', '南门广场距行人入口五十米处', '2026-01-01', '/uploads/a2a565cc-cc87-4af9-bcfc-fd692497f758.jpg', null, '2026-01-01 17:46:51', 'AI检测结果为墙体裂缝 - 位置: 左上角，长度: 15cm，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('20', '辽宁工程技术大学', '表面脱落', '中', '南门广场距行人入口五十米处', '2026-01-01', '/uploads/a2a565cc-cc87-4af9-bcfc-fd692497f758.jpg', null, null, 'AI检测结果为表面脱落 - 位置: 右侧中部，面积: 约0.5平方米，建议立即修复', '未处理');
INSERT INTO `defect_record` VALUES ('21', '辽宁工程技术大学', '渗水痕迹', '中', '南门广场距行人入口五十米处', '2026-01-01', '/uploads/a2a565cc-cc87-4af9-bcfc-fd692497f758.jpg', null, null, 'AI检测结果为渗水痕迹 - 位置: 底部，范围: 20x30cm，建议立即修复', '未处理');

-- ----------------------------
-- Table structure for `system_log`
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '操作用户ID',
  `operation_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `operation_content` text COLLATE utf8mb4_unicode_ci COMMENT '操作内容',
  `ip_address` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'IP地址',
  `user_agent` text COLLATE utf8mb4_unicode_ci COMMENT '用户代理',
  `status` enum('成功','失败') COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作状态',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `system_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES ('1', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 表面脱落', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('2', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 渗水痕迹', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('3', null, '查询', '查询所有缺陷记录', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('4', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 墙体裂缝', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('5', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 表面脱落', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('6', null, '查询', '查询所有缺陷记录', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('7', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 渗水痕迹', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('8', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 墙体裂缝', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('9', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 表面脱落', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('10', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 渗水痕迹', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('11', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 结构变形', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('12', null, '查询', '查询所有缺陷记录', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('13', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 墙体裂缝', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('14', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 表面脱落', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('15', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 渗水痕迹', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('16', null, '查询', '查询所有缺陷记录', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('17', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 墙体裂缝', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('18', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 表面脱落', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('19', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 渗水痕迹', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('20', null, '保存', '保存缺陷记录 - 地点: 辽宁工程技术大学, 类型: 墙体裂缝', null, null, '成功', null);
INSERT INTO `system_log` VALUES ('21', null, '查询', '查询所有缺陷记录', null, null, '成功', null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', 'wd016034');
INSERT INTO `user` VALUES ('2', 'wd', 'wd016034');
INSERT INTO `user` VALUES ('3', 'wyc', 'wd016034');
INSERT INTO `user` VALUES ('4', 'wj', 'wj016034');
INSERT INTO `user` VALUES ('5', 'lsh', '123456');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `role_description` text COLLATE utf8mb4_unicode_ci COMMENT '角色描述',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `user_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_user_role`;
CREATE TABLE `user_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `assigned_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_role` (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `warning_record`
-- ----------------------------
DROP TABLE IF EXISTS `warning_record`;
CREATE TABLE `warning_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `defect_record_id` bigint(20) DEFAULT NULL COMMENT '关联的缺陷记录ID',
  `rule_id` bigint(20) DEFAULT NULL COMMENT '触发的规则ID',
  `warning_level` enum('高','中','低') COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '预警等级',
  `warning_title` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '预警标题',
  `warning_content` text COLLATE utf8mb4_unicode_ci COMMENT '预警内容',
  `status` enum('待处理','已处理','已忽略') COLLATE utf8mb4_unicode_ci DEFAULT '待处理' COMMENT '处理状态',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `processed_at` timestamp NULL DEFAULT NULL COMMENT '处理时间',
  `processed_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '处理人',
  PRIMARY KEY (`id`),
  KEY `defect_record_id` (`defect_record_id`),
  KEY `rule_id` (`rule_id`),
  CONSTRAINT `warning_record_ibfk_1` FOREIGN KEY (`defect_record_id`) REFERENCES `defect_record` (`id`) ON DELETE SET NULL,
  CONSTRAINT `warning_record_ibfk_2` FOREIGN KEY (`rule_id`) REFERENCES `warning_rule` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of warning_record
-- ----------------------------

-- ----------------------------
-- Table structure for `warning_rule`
-- ----------------------------
DROP TABLE IF EXISTS `warning_rule`;
CREATE TABLE `warning_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '规则名称',
  `defect_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '缺陷类型',
  `severity_level` enum('高','中','低') COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '危险等级',
  `condition_expression` text COLLATE utf8mb4_unicode_ci COMMENT '条件表达式',
  `warning_message` text COLLATE utf8mb4_unicode_ci COMMENT '预警消息',
  `is_active` tinyint(1) DEFAULT '1' COMMENT '是否激活',
  `priority` int(11) DEFAULT '0' COMMENT '优先级',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of warning_rule
-- ----------------------------
INSERT INTO `warning_rule` VALUES ('1', '裂缝预警', '墙体裂缝', '高', '', '存在高危风险，禁止使用', '1', '0', null, null);
INSERT INTO `warning_rule` VALUES ('2', '外层材料脱落', '表面脱落', '低', 'defect.severityLevel==\'高\'', '外层材料脱落', '1', '1', null, null);
