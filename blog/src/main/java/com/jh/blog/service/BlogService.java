package com.jh.blog.service;

import com.alibaba.fastjson.JSONObject;
import com.jh.blog.dao.BlogAndTag;
import com.jh.blog.dao.BlogDao;
import com.jh.blog.dao.TagDao;
import com.jh.blog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableTransactionManagement
public class BlogService {

    @Autowired
    BlogDao blogDao;
    @Autowired
    TagDao tagDao;
    @Autowired
    BlogAndTag blogAndTag;

    @Transactional
    public boolean insertBolg(String blogStr){
        String[] tagName = JSONObject.parseObject(blogStr).get("blogTags").toString().split(",");
        blogDao.insertBlog(JSONObject.parseObject(blogStr, Blog.class));
        int insertBlogId = blogDao.getInsertBlogId();
        for (String tag : tagName) {
            Integer tagIdByName = tagDao.getTagIdByName(tag);
            if (tagIdByName == null) { //新的tag值
                tagDao.createNewTag(tag);
                tagIdByName = tagDao.getTagIdByName(tag);
            }
            boolean insertBlogAndTag = blogAndTag.insertBlogAndTag(insertBlogId, tagIdByName);
            if (!insertBlogAndTag){
                return false;
            }
        }
        return true;
    }

    @Transactional
    public Blog[] getAllBlog(String uid){
        Blog[] allBlog = blogDao.getAllBlog(uid);
        for (Blog blog : allBlog) {
            Integer[] blogsTag = blogAndTag.getBlogsTag(blog.getId());
            String[] strings = new String[blogsTag.length];
            for (int i1 = 0; i1 < blogsTag.length; i1++) {
                strings[i1] = tagDao.getTagNameById(blogsTag[i1]);
            }
            blog.setTags(strings);
        }
        return allBlog;
    }

    @Transactional
    public boolean updateBlog(String blogStr){
        Blog blog = JSONObject.parseObject(blogStr, Blog.class);
        String tagNames = JSONObject.parseObject(blogStr).get("tags").toString();
        String[] tagName = tagNames.split(",");
        blogDao.updateBlog(blog);
        blogAndTag.deleteBlogAndTagByBlogId(blog.getId());
        for (String tag : tagName) {
            Integer tagIdByName = tagDao.getTagIdByName(tag);
            if (tagIdByName == null) {
                tagDao.createNewTag(tag);
                tagIdByName = tagDao.getInsertTagId();
            }
            boolean insertBlogAndTag = blogAndTag.insertBlogAndTag(blog.getId(), tagIdByName);
            if (!insertBlogAndTag) {
                return false;
            }
        }
        return true;
    }

    public Blog[] searchContent(String searchContent,String uid){
        return blogDao.blogSearch(searchContent,uid);
    }

    @Transactional
    public boolean deleteBlog(int blogId) {
        //外键约束，需要先删除blogandtag，在删除blog
        boolean b = blogAndTag.deleteBlogAndTagByBlogId(blogId);
        int i = blogDao.deleteBlog(blogId);
        return i == 1 && b;
    }

    public String[] getBlogsTag(int blogId){
        Integer[] blogsTag = blogAndTag.getBlogsTag(blogId);
        String[] allBlogsTag = new String[blogsTag.length];
        int index = 0;
        for (int s : blogsTag) {
            allBlogsTag[index++] = tagDao.getTagNameById(s);
        }
        return allBlogsTag;
    }

    //所有被blog引用过的tag
    public String[] getAllBlogsTag(){

        Integer[] allBlogTagId = blogAndTag.getAllBlogTagId();
        String[] allTagName = new String[allBlogTagId.length];
        int index = 0;
        for (Integer integer : allBlogTagId) {
            allTagName[index++] = tagDao.getTagNameById(integer);
        }
        return allTagName;
    }

    public Blog[] getBlogsByTagName(String tagName){
        Integer tagId = tagDao.getTagIdByName(tagName);
        Integer[] blogsId = blogAndTag.getBlogsIdByTagId(tagId);
        Blog[] blogs = new Blog[blogsId.length];
        int index = 0;
        for (Integer integer : blogsId) {
            blogs[index++] = blogDao.getBlogById(integer);
        }
        return blogs;
    }

    public String[] getAllTagNameLikeInput(String input){
        return tagDao.getAllTagNameLikeInput(input);
    }

}
