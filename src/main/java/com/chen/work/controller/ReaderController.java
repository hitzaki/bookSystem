package com.chen.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.work.pojo.Book;
import com.chen.work.pojo.Reader;
import com.chen.work.service.BookService;
import com.chen.work.service.ReaderService;
import com.chen.work.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping("findAll")
    public Result getAll(){
        return Result.ok(readerService.list());
    }

    // 根据id删除单个信息
    @DeleteMapping("/delete/{id}")
    public Result deleteForId(@PathVariable String id){
        return readerService.removeById(id)? Result.ok(): Result.fail();
    }

    // 根据id序列批量删除
    @DeleteMapping("batchRemove")
    public Result deleteBatch(@RequestBody List<Long> idList){
        return readerService.removeByIds(idList)? Result.ok(): Result.fail();
    }

    // 新增一个教师
    @PostMapping("save")
    public Result save(@RequestBody Reader reader) {
        reader.setLastTime(new Date());
        return readerService.saveOrUpdate(reader)? Result.ok(): Result.fail();
    }

    // 根据id查询
    @GetMapping("get/{id}")
    public Result getById(@PathVariable Long id){
        Reader reader = readerService.getById(id);
        return Result.ok(reader);
    }

    // 修改信息
    @PutMapping("update")
    public Result updateInfo(@RequestBody Reader reader){
        return readerService.updateById(reader)? Result.ok(): Result.fail();
    }
    // @RequestBody(required = false) String name,
    //                             @RequestBody(required = false) String author, @RequestBody(required = false) String detail ,@RequestBody(required = false) Integer type
    // 分页查询
    @PostMapping("/findPage/{page}/{limit}")
    public Result searchPage(@PathVariable long page, @PathVariable long limit, @RequestBody(required = false) Reader reader){
        Page<Reader> bookPage = new Page<>(page, limit);
        QueryWrapper<Reader> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(reader.getName()))   wrapper.like("name", reader.getName());
        if(!StringUtils.isEmpty(reader.getAccount()))  wrapper.like("account", reader.getAccount());
        if(!StringUtils.isEmpty(reader.getPhone()))  wrapper.like("detail", reader.getPhone());
        return Result.ok(readerService.page(bookPage, wrapper));
    }
}
