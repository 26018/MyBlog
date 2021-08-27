package com.jh.blog.dao;

import com.jh.blog.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Insert("insert into user(nickName,mail,password) values(#{nickName},#{mail},#{password})")
    boolean registerUser(User user);

    @Select("select count(*) from user where mail = #{mail}")
    Integer existUser(String mail);

    @Select("select * from user where mail = #{mail} and password = #{password}")
    User login(User user);

}
