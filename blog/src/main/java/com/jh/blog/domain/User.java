package com.jh.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    Integer uid;
    String nickName;
    String mail;
    String password;
    String photoUrl;
    String slogan;

    public User(String nickName, String mail, String password) {
        this.nickName = nickName;
        this.mail = mail;
        this.password = password;
    }
}
