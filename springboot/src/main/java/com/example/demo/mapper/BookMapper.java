package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Book;

public interface BookMapper extends BaseMapper<Book> {//BookMapper继承BaseMapper，BaseMapper自带增删改查,(方括号内是实体类)

}
