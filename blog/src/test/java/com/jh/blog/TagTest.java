package com.jh.blog;

import com.jh.blog.dao.TagDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TagTest {

    @Autowired
    TagDao tagDao;

    @Test
    public void insertTag(){
        boolean json = tagDao.createNewTag("json");
        if (json) {
            System.out.println("插入成功！");
        }else
            System.out.println("插入失败！");
    }

    @Test
    public void getTagId(){
        Integer json = tagDao.getTagIdByName("jsona");
        System.out.println(json);
    }

    @Test
    void getAllTag(){


    }
}
