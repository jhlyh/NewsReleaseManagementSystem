package com.example.newsreleasemanagementsystem.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC,force = true)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    //姓名
    private String username;
    //邮箱
    private String email;
    //密码
    private String password;
    //电话
    private String phone;
    //自我简介
    private String bio;
    //头像
    private String headshotsUrl;
    //用户状态
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
    //收藏新闻
    @OneToMany
    @JoinColumn(name = "bookmark")
    @JsonIgnoreProperties({"comments"})
    private Set<New> news;
    //角色权限
    @ManyToMany
    @JoinColumn(name = "role")
    private Set<Role> roles;
    //偶像
    @ManyToMany
    @JoinTable(
            name = "idol_fan",
            joinColumns = @JoinColumn(name = "fan"),
            inverseJoinColumns = @JoinColumn(name = "idol")
    )
    @JsonIgnoreProperties({"fans,idols,roles,news"})
    private Set<User> idols;
    //粉丝
    @ManyToMany(mappedBy = "idols")
    @JsonIgnoreProperties({"fans,idols,roles,news"})
    private Set<User> fans;

    public User(String username, String email, String encode) {
        this();
        this.username = username;
        this.email = email;
        this.password = encode;
    }
}
