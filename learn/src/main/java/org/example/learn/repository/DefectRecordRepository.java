// src/main/java/org/example/learn/repository/DefectRecordRepository.java
package org.example.learn.repository;

import org.example.learn.entity.DefectRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefectRecordRepository extends JpaRepository<DefectRecord, Long> {
    List<DefectRecord> findByLocationContaining(String location);

    @Query("SELECT d FROM DefectRecord d WHERE d.severityLevel = :severityLevel")
    List<DefectRecord> findBySeverityLevel(@Param("severityLevel") String severityLevel);

    @Query("SELECT d FROM DefectRecord d WHERE d.detectionDate BETWEEN :startDate AND :endDate")
    List<DefectRecord> findByDateRange(@Param("startDate") java.time.LocalDate startDate,
                                       @Param("endDate") java.time.LocalDate endDate);

    @Query("SELECT d FROM DefectRecord d ORDER BY d.detectionDate DESC")
    List<DefectRecord> findAllOrderByDateDesc();

    // 新增按检测结果查询的方法
    @Query("SELECT d FROM DefectRecord d WHERE d.detectionResult LIKE %:detectionResult%")
    List<DefectRecord> findByDetectionResultContaining(@Param("detectionResult") String detectionResult);

    @Query("SELECT d FROM DefectRecord d WHERE d.isProcessed = :isProcessed")
    List<DefectRecord> findByIsProcessed(@Param("isProcessed") String isProcessed);

    // 新增按缺陷类型查询的方法
    @Query("SELECT d FROM DefectRecord d WHERE d.defectType = :defectType")
    List<DefectRecord> findByDefectType(@Param("defectType") String defectType);
}
