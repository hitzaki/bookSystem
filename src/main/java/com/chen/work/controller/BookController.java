package com.chen.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.work.pojo.Book;
import com.chen.work.pojo.Reader;
import com.chen.work.pojo.Record;
import com.chen.work.service.BookService;
import com.chen.work.service.ReaderService;
import com.chen.work.service.RecordService;
import com.chen.work.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("findAll")
    public Result getAll(){
        return Result.ok(bookService.list());
    }

    // 根据id删除单个信息
    @DeleteMapping("/delete/{id}")
    public Result deleteForId(@PathVariable String id){
        return bookService.removeById(id)? Result.ok(): Result.fail();
    }

    // 根据id序列批量删除
    @DeleteMapping("batchRemove")
    public Result deleteBatch(@RequestBody List<Long> idList){
        return bookService.removeByIds(idList)? Result.ok(): Result.fail();
    }

    // 新增一个教师
    @PostMapping("save")
    public Result save(@RequestBody Book book) {
        return bookService.save(book)? Result.ok(): Result.fail();
    }

    // 根据id查询
    @GetMapping("get/{id}")
    public Result getById(@PathVariable Long id){
        Book book = bookService.getById(id);
        return Result.ok(book);
    }

    // 修改信息
    @PutMapping("update")
    public Result updateInfo(@RequestBody Book book){
        return bookService.updateById(book)? Result.ok(): Result.fail();
    }
    // @RequestBody(required = false) String name,
    //                             @RequestBody(required = false) String author, @RequestBody(required = false) String detail ,@RequestBody(required = false) Integer type
    // 分页查询
    @PostMapping("/findPage/{page}/{limit}")
    public Result searchPage(@PathVariable long page, @PathVariable long limit, @RequestBody(required = false) Book book){
        Page<Book> bookPage = new Page<>(page, limit);
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(book.getName()))   wrapper.like("name", book.getName());
        if(!StringUtils.isEmpty(book.getAuthor()))  wrapper.like("author", book.getAuthor());
        if(!StringUtils.isEmpty(book.getDetail()))  wrapper.like("detail", book.getDetail());
        if(!StringUtils.isEmpty(book.getType()))    wrapper.eq("type", book.getType());
        return Result.ok(bookService.page(bookPage, wrapper));
    }

    // -----------------------------------添加借书记录-----------------------------------------------
    @Autowired
    private ReaderService readerService;
    @Autowired
    private RecordService recordService;

    @PostMapping("/addRecord")
    public Result addRecord(@RequestBody Record record){
        record.setId(null);
        record.setBid(0);
        record.setRid(0);
        record.setReturnTime(new Date());
        return recordService.save(record)? Result.ok(): Result.fail();
    }
}
