package com.jh.blog.controller;

import com.jh.blog.dao.PoemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/poem")
public class PoemController {

    @Autowired
    PoemDao poemDao;

    @ResponseBody
    @RequestMapping("/getPoem")
    public String[] getPoem(){
        return poemDao.getPoem();
    }
}
