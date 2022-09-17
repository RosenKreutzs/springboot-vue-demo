package com.example.demo.controller;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.News;
import com.example.demo.mapper.NewsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;

//@RestController的意思是表示自己为返回json的控制器
@RestController
@RequestMapping("/news")//@RequestMapping是usercontroller统一的路由
public class NewsController {
    @Resource//引入Mapper的userMapper
    NewsMapper newsMapper;

    @PostMapping(value="/save")//定义post的接口
    public Result<?> save(@RequestBody News news){//RequestBody 可以将前台传来的json转化为User对象
        //Result<?>的问号表示所以类型的数据都可以接受
        newsMapper.insert(news);
        return Result.success();//放回信息
    }

    @PutMapping(value="/updata")//定义post的接口
    public Result<?> updata(@RequestBody News news){//RequestBody 可以将前台传来的json转化为User对象
        //Result<?>的问号表示所以类型的数据都可以接受
        newsMapper.updateById(news);
        return Result.success();//放回信息
    }
    @DeleteMapping(value="/delNews/{id}")///delUser/{id}/{aa}可以同时传入两个数据
    public Result<?> delUser(@PathVariable Long id){//@PathVariable花括号中的字符串变为对象,搭配@DeleteMapping
        //Result<?>的问号表示所以类型的数据都可以接受
        newsMapper.deleteById(id);
        return Result.success();//放回信息
    }
    @GetMapping(value = "/findPage")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam(defaultValue = "") String search){
        //pageNum是当前页;pageSize是一页有多少条数据;search是查询的关键字(defaultValue是设置默认值)
        LambdaQueryWrapper<News> wrapper=Wrappers.<News>lambdaQuery();//sql条件的执行对象
        if (StrUtil.isNotBlank(search)){//StrUtil.isNotBlank(search)是hutool依赖用来帮助判断参数是否为空的
            wrapper.like(News::getTitle,search);
        }
        Page<News> newsPage= newsMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        //selectPage需要Page对象和sql执行对象
        //new Page<>(pageNum,pageSize)//分页对象
        //Wrappers.<User>lambdaQuery().like(User::getNickName,search)//sql执行对象
        // User::getNickName是User.getNickName的变形
        return Result.success(newsPage);//Result.success(变量名)//用返回变量
    }
}
