// src/main/java/org/example/learn/repository/UserRoleRepository.java
package org.example.learn.repository;

import org.example.learn.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRoleName(String roleName);
}
