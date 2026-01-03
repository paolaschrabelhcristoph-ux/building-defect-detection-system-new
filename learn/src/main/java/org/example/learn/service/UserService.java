package org.example.learn.service;

import org.example.learn.entity.User;
import org.example.learn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);

        System.out.println("查找的用户名: " + username);
        System.out.println("找到的用户: " + user);

        if (user == null) {
            System.out.println("用户不存在");
            return false;
        }

        System.out.println("数据库中的密码: " + user.getPassword());
        System.out.println("输入的密码: " + password);
        System.out.println("密码匹配结果: " + user.getPassword().equals(password));

        // 直接比较明文密码
        return user.getPassword().equals(password);
    }
}
