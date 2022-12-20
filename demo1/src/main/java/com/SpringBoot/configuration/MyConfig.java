package com.SpringBoot.configuration;

import com.SpringBoot.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lemon
 * @create 2022-12-07-18:36
 */
@Configuration
public class MyConfig {
    @Bean
    public Person person(){
        Person p=new Person("张三",23);
        return p;
    }
}
