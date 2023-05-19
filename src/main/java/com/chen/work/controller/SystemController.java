package com.chen.work.controller;

import com.chen.work.service.FileService;
import com.chen.work.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/system")
public class SystemController {
    @GetMapping("test")
    public String handlerTe(){
        return "yes!";
    }

    @PostMapping("/login")
    public Result handlerLogin(){
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        return Result.ok(map);
    }


    @GetMapping("/info")
    public Result handlerInfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "admin");
        map.put("introduction", "我是管理员");
        map.put("avatar","https://lxc-1312149144.cos.ap-nanjing.myqcloud.com/BookSystem/home.jpg");
        map.put("name", "管理员");
        return Result.ok(map);
    }


    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public Result handlerUpload(@RequestParam("file") MultipartFile file){
        String url = fileService.upload(file);
        return url==null? Result.fail(): Result.ok(url);
    }

}
