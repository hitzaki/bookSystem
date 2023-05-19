package com.chen.work.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.work.mapper.ReaderMapper;
import com.chen.work.pojo.Reader;
import com.chen.work.service.ReaderService;
import org.springframework.stereotype.Service;

@Service
public class ReaderServiceImpl extends ServiceImpl<ReaderMapper, Reader> implements ReaderService {
}
