package com.SpringBoot;

import com.SpringBoot.pojo.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Lemon
 * @create 2022-12-07-9:54
 */
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MyApplication.class, args);
        Object person1 = run.getBean("person");
        Object person2 = run.getBean("person");
        System.out.println(person1==person2);
        System.out.println(run.getBeanDefinitionCount());
//        String[] dn = run.getBeanDefinitionNames();
//        for(String s:dn){
//            System.out.println(s);
//        }
    }
}
