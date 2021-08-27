package com.jh.blog.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.java2d.loops.Blit;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    String tagName;
    List<Blog> connectedBlog;

}
