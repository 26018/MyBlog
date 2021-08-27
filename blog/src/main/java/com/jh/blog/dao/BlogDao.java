package com.jh.blog.dao;

import com.jh.blog.domain.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogDao {

    @Select("select * from my_blog where uid = #{uid}")
    Blog[] getAllBlog(String uid);

    @Select("select * from my_blog where uid = #{uid} and concat(title,author,content) like concat('%',#{searchContent},'%')")
    Blog[] blogSearch(String searchContent,String uid);

    @Delete("delete from my_blog where id = #{id}")
    int deleteBlog(int id);

    @Insert("insert into my_blog(uid,title,author,content,bgimg,createDate,modifyDate,words,readtime) values(#{userId},#{title},#{author},#{content},#{bgImg},now(),now(),CHAR_LENGTH(#{content}),0)")
    boolean insertBlog(Blog blog);

    @Select("select LAST_INSERT_ID() as lastId")
    int getInsertBlogId();

    @Update("update my_blog set title = #{title},author = #{author},content = #{content},bgimg = #{bgImg},modifyDate = now(),words = CHAR_LENGTH(#{content}) where id = #{id}")
    boolean updateBlog(Blog blog);

    @Select("select * from my_blog where id = #{blogId}")
    Blog getBlogById(int blogId);
}
