package org.example.learn.controller;

import org.example.learn.entity.User;
import org.example.learn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           Model model) {

        // 检查密码是否一致
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "两次输入的密码不一致");
            return "register";
        }

        // 检查用户名是否已存在
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        }

        // 创建新用户，不加密密码
        User newUser = new User(username, password);
        userRepository.save(newUser);

        model.addAttribute("success", "注册成功，请登录");
        return "register";
    }
}
