package com.jh.blog.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogAndTag {

    //为blog添加Tag
    @Insert("insert into blogandtag(blogid,tagid) values(#{blogId},#{tagId})")
    boolean insertBlogAndTag(int blogId,int tagId);

    //删除某条blog后，删除与这条blog相关的Tag
    @Delete("delete from blogandtag where blogid = #{blogId}")
    boolean deleteBlogAndTagByBlogId(int bogId);

    //获取某个blog引用的tag的id
    @Select("select tagid from blogandtag where blogid = #{blogId}")
    Integer[] getBlogsTag(int blogId);

    //获取所有blog引用的tag的id
    @Select("select distinct tagid from blogandtag")
    Integer[] getAllBlogTagId();

    //获取与某个tag有关的blog的id
    @Select("select blogid from blogandtag where tagid = #{tagId}")
    Integer[] getBlogsIdByTagId(int tagId);
}
