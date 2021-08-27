package com.jh.blog.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface PoemDao {

    @Select("select poem from poems limit 0,40")
    String[] getPoem();

}
