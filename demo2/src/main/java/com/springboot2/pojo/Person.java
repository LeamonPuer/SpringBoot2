package com.springboot2.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author Lemon
 * @create 2022-12-11-10:34
 */
@Data
@ToString
public class Person {
    private String name;
    private Integer age;
    private Date birth;
}
