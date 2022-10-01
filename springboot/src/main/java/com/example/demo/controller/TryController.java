package com.example.demo.controller;
import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.List;


//@RestController的意思是表示自己为返回json的控制器
@RestController
@RequestMapping("/try_mongodb")//@RequestMapping是usercontroller统一的路由
public class TryController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/shows")//定义post的接口
    public Result<?> findTry() {
        List<Try> tryList = mongoTemplate.findAll(Try.class);

        return Result.success(tryList);
    }
   }
