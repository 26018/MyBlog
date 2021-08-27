package com.jh.blog.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    int id;
    int userId;
    String title;
    String author;
    String[] tags;
    String content;
    String bgImg;
    Date createDate;
    Date modifyDate;
    int words;
    int readTime;

    public Blog(int userId, String title,String author ,String content, String bgImg) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.bgImg = bgImg;
    }

}
