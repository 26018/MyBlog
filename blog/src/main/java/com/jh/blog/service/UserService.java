package com.jh.blog.service;

import com.jh.blog.dao.UserDao;
import com.jh.blog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    /**
     * @param user
     * @return 1表示重复注册  0表示注册成功。
     */
    public Integer registerUser(User user) {
        Integer integer = userDao.existUser(user.getMail());
        System.out.println(integer);
        if (integer == 1) {
            return integer;
        }else
            userDao.registerUser(user);
        return 0;
    }

    public User userLogin(User user){
        return userDao.login(user);
    }


}
