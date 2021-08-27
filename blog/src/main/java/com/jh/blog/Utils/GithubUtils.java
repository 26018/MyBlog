package com.jh.blog.Utils;

import com.alibaba.fastjson.JSONObject;
import com.jh.blog.domain.ImageFile;
import com.jh.blog.domain.Storage;
import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.*;

public class GithubUtils {

    static String url = "https://v2.kkpp.cc"; //https://api.github.com

//    static String url = "https://api.github.com"; //https://api.github.com

    static String username = "jinhui-ss";

    static String access_token = "7ffc7fd85f54b69ceba2e5a2a01dd816c0095cc1";

    static String storageName;

    static String fullName;

    static {
        try {
            System.out.println("init method start..");
            long l = System.currentTimeMillis();
            List<Storage> allStorageList = getAllStorageList();
            for (Storage o : allStorageList) {
                if (Integer.parseInt(o.getSize()) < 1024 * 1024 * 800) { //800mb
                    storageName = o.getName();
                    fullName = o.getFullName();
                    break;
                }
            }
            long l1 = System.currentTimeMillis();
            System.out.println("fullName:"+fullName);
            System.out.println("storageName:" + storageName);
            System.out.println("init method over..");
            System.out.println("init time:" + (l1 - l) +"ms");
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static boolean uploadFile(String fileBase64Code, String fileName) throws UnirestException {
        long l = System.currentTimeMillis();
        HttpResponse<String> response =
                Unirest.put(url + "/repos/" + fullName + "/contents/" + fileName)
                        .header("content-type", "application/vnd.github.v3+json")
                        .basicAuth("access_token", access_token)
                        .body("{\n  \"message\": \"commit from banana\",\n  \"content\": \"" + fileBase64Code + "\"\n}")
                        .asString();
        long l1 = System.currentTimeMillis();
        if (response.getStatus() == 201) {
            System.out.println("上传成功!共用时:" + (l1 - l) + "ms");
            return true;
        } else {
            System.out.println("上传失败!");
            return false;
        }
    }

    //https://api.github.com/repos/26018/img/contents/image/11.jpg
    public static boolean updateFile(String newFileBase64Code, String fileName, String oldFileSHA) throws UnirestException {
        long l = System.currentTimeMillis();
        HttpResponse<String> response = Unirest.put(url + "/repos/" + fullName + "/contents/" + "/" + fileName)
                .header("content-type", "application/vnd.github.v3+json")
                .basicAuth("access_token", access_token)
                .body("{\n\t\"message\": \"update from banana\",\n  \"content\": \"" + newFileBase64Code + "\",\n  \"sha\": \"" + oldFileSHA + "\"\n}")
                .asString();
        long l1 = System.currentTimeMillis();
        if (response.getStatus() == 200) {
            System.out.println("更新成功!共用时：" + (l1 - l) + "ms");
            return true;
        } else {
            System.out.println("更新失败！");
            return false;
        }
    }

    //https://api.github.com/repos/26018/img/contents/image/11.jpg
    public static boolean deleteFile(String fileName, String fileSHA) throws UnirestException {
        long l = System.currentTimeMillis();
        HttpResponse<String> response = Unirest.delete(url + "/repos/" + fullName + "/contents/" + "/" + fileName)
                .header("content-type", "application/vnd.github.v3+json")
                .basicAuth("access_token", access_token)
                .body("{\n  \"message\": \"delete a file\",\n  \"sha\": \"" + fileSHA + "\"\n}")
                .asString();
        long l1 = System.currentTimeMillis();
        if (response.getStatus() == 200) {
            System.out.println("删除" + fileName + "成功！共用时：" + (l1 - l) + "ms");
            return true;
        } else {
            System.out.println("删除失败！");
            return false;
        }

    }

    public static String encryptToBase64(byte[] b) throws IOException {
        if (b == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(b);
    }

    public static String getFileLink(String fileName) {
        return "https://cdn.jsdelivr.net/gh/" + fullName + "/" + "/" + fileName;
    }

    //https://api.github.com/repos/26018/img/contents/image/03.png
    public static ImageFile getFIleInfo(String fileName) throws UnirestException {
        HttpResponse<String> response =
                Unirest.get(url + "/repos/" + fullName + "/contents/" + fileName)
                        .header("content-type", "application/vnd.github.v3+json")
                        .basicAuth("access_token", access_token)
                        .asString();
        if (response.getStatus() == 404) {
            System.out.println("文件不存在");
            return null;
        }
        return JSONObject.parseObject(response.getBody(), ImageFile.class);
    }

    //https://api.github.com/repos/26018/img/contents
    public static List<ImageFile> getFileList() throws UnirestException {
        HttpResponse<String> response =
                Unirest.get(url + "/repos/" + fullName + "/contents")
                        .header("content-type", "application/vnd.github.v3+json")
                        .basicAuth("access_token", access_token)
                        .asString();
        Headers headers = response.getHeaders();
        String s = headers.get("X-RateLimit-Remaining").get(0);
        System.out.println("剩余访问次数：" + s);
        return JSONObject.parseArray(response.getBody(), ImageFile.class);
    }

    public static boolean createStorage(String storageName) throws UnirestException {
        String password = "8576677a65efe9cbe6a9a6b45c0355927d968ca6";
        HttpResponse<String> response = Unirest.post(url + "/user/repos?Accept=application%2Fvnd.github.v3%2Bjson&access_token=5a502ddfd453399412e1bdc7ff6eb8db89197e23&user=" + username + "&password=" + password)
                .header("content-type", "application/vnd.github.v3+json")
//                .basicAuth("access_token",access_token)
                .body("{\n  \"name\":\"" + storageName + "\"\n}")
                .asString();
        if (response.getStatus() == 201) {
            System.out.println("创建仓库 " + storageName + " 成功！");
            return true;
        } else {
            System.out.println("创建仓库失败！");
            return false;
        }
    }

    public static List<Storage> getAllStorageList() throws UnirestException {
        HttpResponse<String> response = Unirest.get(url+"/users/" + username + "/repos").header("content-type", "application/vnd.github.v3+json")
                .basicAuth("access_token", access_token).asString();
        return JSONObject.parseArray(response.getBody(), Storage.class);
    }
}
