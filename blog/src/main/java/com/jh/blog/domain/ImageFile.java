package com.jh.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageFile {
    String name;
    String sha;
    String size;
    String type;
    String url;
}
