package com.jh.blog.controller;

import com.alibaba.fastjson.JSON;
import com.jh.blog.domain.Blog;
import com.jh.blog.service.BlogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogController {

    final BlogService blogService;
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping("/save")
    private String saveBlog(@RequestParam("blog") String blogStr) {
        return blogService.insertBolg(blogStr) ? "success" : "failure";
    }

    @RequestMapping("/update")
    public String updateBlog(@RequestParam("blog") String blogStr) {
        return blogService.updateBlog(blogStr) ? "success" : "failure";
    }

    @RequestMapping("/getAllBlog")
    private String[] getAllBlog(String uid) {
        Blog[] allBlog = blogService.getAllBlog(uid);
        String[] res = new String[allBlog.length];
        for (int i = 0; i < allBlog.length; i++) {
            res[i] = JSON.toJSONString(allBlog[i]);
        }
        return res;
    }

    @RequestMapping("/delete")
    public String deleteBlog(@RequestParam("blogId") String id) {
        return blogService.deleteBlog(Integer.parseInt(id)) ? "success" : "failure";
    }

    @RequestMapping("/search")
    public Blog[] searchBlog(@RequestParam("searchContent") String searchContent,String uid) {
        return blogService.searchContent(searchContent,uid);
    }

    @RequestMapping("/getBlogsTag")
    String[] getBlogsTag(@RequestParam("blogId") int blogId) {
        return blogService.getBlogsTag(blogId);
    }

    @RequestMapping("/getAllBlogsTag")
    public String[] getAllBlogsTag() {
        return blogService.getAllBlogsTag();
    }

    @RequestMapping("/getBlogsByTagName")
    public Blog[] getBlogsByTagName(@RequestParam("tagName") String tagName) {
        return blogService.getBlogsByTagName(tagName);
    }

    @RequestMapping("/getAllTagNameLikeInput")
    public String[] getTagByInput(@RequestParam("blogTags") String input){
        return blogService.getAllTagNameLikeInput(input);
    }

}
