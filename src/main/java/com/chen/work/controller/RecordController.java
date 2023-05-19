package com.chen.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.work.pojo.Book;
import com.chen.work.pojo.Record;
import com.chen.work.service.BookService;
import com.chen.work.service.RecordService;
import com.chen.work.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @GetMapping("findAll")
    public Result getAll(){
        return Result.ok(recordService.list());
    }

    // 根据id删除单个信息
    @DeleteMapping("/delete/{id}")
    public Result deleteForId(@PathVariable String id){
        return recordService.removeById(id)? Result.ok(): Result.fail();
    }

    // 根据id序列批量删除
    @DeleteMapping("batchRemove")
    public Result deleteBatch(@RequestBody List<Long> idList){
        return recordService.removeByIds(idList)? Result.ok(): Result.fail();
    }

    // 新增一
    @PostMapping("save")
    public Result save(@RequestBody Record record) {
        record.setBid(0);
        record.setRid(0);
        record.setReturnTime(new Date());
        return recordService.save(record)? Result.ok(): Result.fail();
    }

    // 根据id查询
    @GetMapping("get/{id}")
    public Result getById(@PathVariable Long id){
        Record record = recordService.getById(id);
        return Result.ok(record);
    }

    // 修改信息
    @PutMapping("update")
    public Result updateInfo(@RequestBody Record record){
        return recordService.updateById(record)? Result.ok(): Result.fail();
    }
    // @RequestBody(required = false) String name,
    //                             @RequestBody(required = false) String author, @RequestBody(required = false) String detail ,@RequestBody(required = false) Integer type
    // 分页查询
    @PostMapping("/findPage/{page}/{limit}")
    public Result searchPage(@PathVariable long page, @PathVariable long limit, @RequestBody(required = false) Record record){
        Page<Record> bookPage = new Page<>(page, limit);
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(record.getBname()))   wrapper.like("bname", record.getBname());
        if(!StringUtils.isEmpty(record.getRname()))  wrapper.like("rname", record.getRname());
        return Result.ok(recordService.page(bookPage, wrapper));
    }

}
