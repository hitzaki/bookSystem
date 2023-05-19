package com.chen.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.work.pojo.Admin;
import com.chen.work.pojo.Book;
import com.chen.work.service.AdminService;
import com.chen.work.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/findPage/{page}/{limit}")
    public Result searchPage(@PathVariable long page, @PathVariable long limit, @RequestBody(required = false) Admin admin){
        Page<Admin> bookPage = new Page<>(page, limit);
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(admin.getAccount()))   wrapper.like("account", admin.getAccount());
        if(!StringUtils.isEmpty(admin.getName()))  wrapper.like("name", admin.getName());
        if(!StringUtils.isEmpty(admin.getPhone()))  wrapper.like("phone", admin.getPhone());
        return Result.ok(adminService.page(bookPage, wrapper));
    }
}
