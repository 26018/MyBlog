package com.jh.blog;

import com.alibaba.fastjson.JSONObject;
import com.jh.blog.dao.BlogAndTag;
import com.jh.blog.dao.BlogDao;
import com.jh.blog.dao.TagDao;
import com.jh.blog.domain.Blog;
import com.jh.blog.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogTest {

    @Autowired
    BlogDao blogDao;

    @Autowired
    BlogAndTag blogAndTag;

    @Autowired
    TagDao tagDao;

    @Autowired
    BlogService blogService;

    @Test
    public void insertBlog(){
        boolean b = blogDao.insertBlog(new Blog(1, "title", "author", "content", "bgImg"));
        System.out.println(b);
    }

    @Test
    public void test(){
        String str = "{'title':'jinhui','tagName':'thisIsTag,thatIsTag'}";
        Object tagName = JSONObject.parseObject(str).get("tagName");
        System.out.println(tagName);
        Blog blog = JSONObject.parseObject(str, Blog.class);
        System.out.println(blog.toString());
    }

    @Test
    public void getInsertId(){
        int insertBlogId = blogDao.getInsertBlogId();
        System.out.println(insertBlogId);
    }

    @Test
    public void fortest(){

    }

    @Test
    public void strd() {
        Integer[] allBlogTagId = blogAndTag.getAllBlogTagId();
        for (Integer integer : allBlogTagId) {
            System.out.println(integer);

        }
    }



}
