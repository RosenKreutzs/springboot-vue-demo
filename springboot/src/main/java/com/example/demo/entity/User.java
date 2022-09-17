package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")//用mybatisplus创建对象要与数据库的表名相对应
@Data//lombok中的帮助生成get和set方法并隐藏
public class User {
    @TableId(value = "id",type = IdType.AUTO)//确认id为主键且自动生成
    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private Integer age;
    private String sex;
    private String address;
    private Integer role;
}
