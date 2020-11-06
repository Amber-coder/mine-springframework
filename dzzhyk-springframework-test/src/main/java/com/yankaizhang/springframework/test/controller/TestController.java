package com.yankaizhang.springframework.test.controller;


import com.yankaizhang.springframework.beans.factory.annotation.Autowired;
import com.yankaizhang.springframework.context.annotation.Controller;
import com.yankaizhang.springframework.test.service.TestService;
import com.yankaizhang.springframework.webmvc.annotation.RequestMapping;
import com.yankaizhang.springframework.webmvc.annotation.RequestParam;
import com.yankaizhang.springframework.webmvc.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("username", "haha");
        request.setAttribute("msg", testService.sayHello("haha"));
        return "index";
    }

    public void hi(){
        System.out.println("执行了hi方法...");
    }

    @RequestMapping("/upload")
    public String index(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file){
        //获取文件的真实文件名
        String trueName = file.getOriginalFilename();
        if (null == trueName){
            request.setAttribute("msg", "上传失败");
            return "index";
        }
        String DIR = "/Users/dzzhyk/Desktop/openSource/springframework/dzzhyk-springframework-test/upload/";
        String saveName = UUID.randomUUID().toString();

        //创建要保存的文件
        File dir = new File(DIR);
        File newFile = new File(DIR + saveName + trueName.substring(trueName.lastIndexOf(".")));

        if (!dir.exists()){
            boolean mkdirs = dir.mkdirs();
            if (!mkdirs){
                request.setAttribute("msg", "上传失败");
                return "index";
            }
        }

        //把临时文件file转储到newFile上
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("msg", "上传失败");
            return "index";
        }
        request.setAttribute("msg", "上传成功");
        return "index";
    }

}
