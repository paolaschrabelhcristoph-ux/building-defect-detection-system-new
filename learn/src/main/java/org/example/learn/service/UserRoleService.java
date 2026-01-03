package org.example.learn.service;

import org.example.learn.entity.UserRole;
import org.example.learn.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    public List<UserRole> getAllRoles() {
        return userRoleRepository.findAll();
    }
    
    public UserRole getRoleById(Long id) {
        return userRoleRepository.findById(id).orElse(null);
    }
    
    public UserRole getRoleByName(String roleName) {
        return userRoleRepository.findByRoleName(roleName);
    }
    
    public UserRole saveRole(UserRole role) {
        return userRoleRepository.save(role);
    }
    
    public void deleteRole(Long id) {
        userRoleRepository.deleteById(id);
    }
}
