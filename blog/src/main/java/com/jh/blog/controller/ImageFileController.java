package com.jh.blog.controller;

import com.jh.blog.Utils.GithubUtils;
import com.jh.blog.domain.ImageFile;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Controller
public class ImageFileController {

    @RequestMapping("/getAllImage")
    @ResponseBody
    private ImageFile[] getAllImages() throws UnirestException {
        List<ImageFile> fileList = GithubUtils.getFileList();
        for (ImageFile imageFile : fileList) {
            imageFile.setUrl(GithubUtils.getFileLink(imageFile.getName()));
        }
        return fileList.toArray(new ImageFile[fileList.size()]);
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    private String uploadFile(@RequestParam("file") MultipartFile file) throws IOException, UnirestException {
        System.out.println("上传图片开始...");
        byte[] bytes = file.getBytes();
        String s = GithubUtils.encryptToBase64(bytes);
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        boolean b = GithubUtils.uploadFile(s, fileName);
        if (b) {
            return GithubUtils.getFileLink(fileName);
        } else
            return "上传失败，请检查网络.";
    }
}
