package com.jh.blog.controller;

import com.jh.blog.Utils.RegisterUtils;
import com.jh.blog.domain.User;
import com.jh.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.*;
import javax.servlet.http.HttpServletRequest;
import java.security.GeneralSecurityException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/sendRegisterCode")
    public String sendRegisterCode(@RequestParam("registerMail") String registerMail) throws GeneralSecurityException, MessagingException {
        String randomCode = RegisterUtils.randomCode();
        RegisterUtils.SendEamil(registerMail, randomCode);
        return randomCode;
    }

    @ResponseBody
    @RequestMapping("/register")
    public boolean userRegister(User user){
        return userService.registerUser(user) != 1;
    }

    @ResponseBody
    @RequestMapping("/login")
    public User userLogin(HttpServletRequest request, User user) {
        System.out.println(user.toString());
        User loginUser = userService.userLogin(user);

        if (loginUser != null) {
            request.getSession().setAttribute("loginUser",loginUser.getMail());
        }
        return loginUser;
    }

}


