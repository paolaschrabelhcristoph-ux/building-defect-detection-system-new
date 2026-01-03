// src/main/java/org/example/learn/repository/SystemLogRepository.java
package org.example.learn.repository;

import org.example.learn.entity.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemLogRepository extends JpaRepository<SystemLog, Long> {
    List<SystemLog> findByUserId(Long userId);
    
    @Query("SELECT s FROM SystemLog s WHERE s.operationType = :operationType")
    List<SystemLog> findByOperationType(@Param("operationType") String operationType);
    
    @Query("SELECT s FROM SystemLog s ORDER BY s.createdAt DESC")
    List<SystemLog> findAllOrderByDateDesc();
}
