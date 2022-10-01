package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("try")
public class Try {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String sex;
}


