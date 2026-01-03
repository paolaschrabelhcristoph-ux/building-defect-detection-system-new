package org.example.learn.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")  // 修改表名为 user
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // 去掉 email 字段

    // 构造函数
    public User() {}

    public User(String username, String password) {  // 修改构造函数，去掉 email 参数
        this.username = username;
        this.password = password;
    }

    // Getter 和 Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // 去掉 email 的 getter 和 setter
}
