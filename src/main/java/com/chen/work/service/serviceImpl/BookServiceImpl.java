package com.chen.work.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.work.mapper.BookMapper;
import com.chen.work.pojo.Book;
import com.chen.work.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl  extends ServiceImpl<BookMapper, Book> implements BookService {
}
