// src/main/java/org/example/learn/repository/WarningRecordRepository.java
package org.example.learn.repository;

import org.example.learn.entity.WarningRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarningRecordRepository extends JpaRepository<WarningRecord, Long> {
    List<WarningRecord> findByStatus(WarningRecord.WarningStatus status);
    
    @Query("SELECT w FROM WarningRecord w WHERE w.defectRecord.id = :defectRecordId")
    List<WarningRecord> findByDefectRecordId(@Param("defectRecordId") Long defectRecordId);
    
    List<WarningRecord> findByWarningLevel(WarningRecord.WarningStatus warningLevel);
    
    @Query("SELECT w FROM WarningRecord w ORDER BY w.createdAt DESC")
    List<WarningRecord> findAllOrderByDateDesc();
}
