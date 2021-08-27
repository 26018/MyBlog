package com.jh.blog;

import com.jh.blog.domain.User;
import com.jh.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class userTest {


    @Autowired
    UserService userService;

    @Test
    public void registerUser(){
        User user = new User("金辉","2033471349@qq.com","123465");
        Integer b = userService.registerUser(user);
        System.out.println(b);
    }

//    @Test
//    void existUser(){
//        User user = new User("金辉","2033471349@qq.com","123465");
//        Integer integer = userDao.existUser(user.getMail());
//        boolean res = (integer == 1) ? true:false;
//        System.out.println(res);
//    }

}
