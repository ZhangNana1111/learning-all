package com.app.springbootjpa.vo;


import lombok.Data;

import javax.persistence.*;

/**
 * @Author ZhangNana
 * @DATE 2021/7/11 17:15
 * @Version 1.0
 */
@Entity
@Table(name = "user_info")
@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "address")
    private String address;
}
