package com.jh.blog.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDao {

    @Insert("insert into tags(tagname) values(#{tagName})")
    boolean createNewTag(String tagName);

    @Select("select id from tags where tagname = #{tagName}")
    Integer getTagIdByName(String tagName);

    @Select("select LAST_INSERT_ID()")
    int getInsertTagId();

    @Select("select tagname from tags where id = #{id}")
    String getTagNameById(int id);

    @Select("select id from tags")
    Integer[] getAllTagId();

    @Select("select tagname from tags where concat(tagname) like concat('%',#{input},'%')")
    String[] getAllTagNameLikeInput(String input);
}
