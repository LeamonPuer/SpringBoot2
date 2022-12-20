package com.springboot2.controller;

import com.springboot2.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Lemon
 * @create 2022-12-09-15:50
 */
@Slf4j
@Controller
public class MyController {
    @ResponseBody
    // /person/John?age=26&hobby=1&hobby=2
    @GetMapping("/person/{name}")
    public Map<String, Object> test(@PathVariable("name") String name,
                                    @PathVariable Map<String, Object> map,
                                    @RequestParam(value = "age", required = false) Integer age,
                                    @RequestParam("hobby") List<String> hobby,
                                    @RequestParam Map<String, Object> map1,
                                    @RequestHeader(value = "User-Agent", required = false) String rh,
                                    @RequestHeader Map<String, Object> map2,
                                    Cookie cookie,
                                    @CookieValue(value = "_ga", required = false) String _ga) {
        System.out.println(cookie.getName() + cookie.getValue());
        System.out.println(_ga);
        return map;
    }

    @ResponseBody
    @DeleteMapping("/delete")
    public String test2() {
        return "delete";
    }

    @ResponseBody
    @PostMapping("/rb")
    public String test3(@RequestBody String rb) {
        System.out.println("======");
        return rb;
    }

    @ResponseBody
    @GetMapping("/emp/{emp1}/{emp2}")
    public Map<String, Object> test4(@MatrixVariable(value = "name", pathVar = "emp1") String emp1,
                                     @MatrixVariable(value = "name", pathVar = "emp2") String emp2) {
        Map<String, Object> map = new HashMap<>();
        map.put("emp1", emp1);
        map.put("emp2", emp2);
        return map;
    }

    @GetMapping("/hello")
    public String test5(Model model) {
        model.addAttribute("hello", "world");
        return "forward:/world";
    }

    @GetMapping("/world")
    public String test6(@RequestAttribute(value = "hello", required = false) String hello) {
        System.out.println(hello);
        return "hello";
    }

    @ResponseBody
    @GetMapping("/per")
    public Person test7() throws ParseException {
        Person person = new Person();
        person.setName("John");
        person.setAge(26);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sf.parse("1996-3-28");
        person.setBirth(parse);
        return person;
    }

    @PostMapping("/form")
    public String test8(@RequestParam("name") String name,
                        @RequestPart("img") MultipartFile img,
                        @RequestPart("photos") MultipartFile[] photos) throws IOException {
        log.info("接收的信息：name={},img={},photos={}", name, img.getSize(), photos.length);
        UUID uuid = UUID.randomUUID();
        if (!img.isEmpty()) {
            String fileName=img.getOriginalFilename();
            String hz = fileName.substring(fileName.lastIndexOf("."));
            img.transferTo(new File("H:\\"+uuid + hz));
            File file = new File("H:\\photos");
            if (!file.exists())
                file.mkdir();
            for (int i=0;i<photos.length;i++) {
                uuid = UUID.randomUUID();
                photos[i].transferTo(new File(file.getName() + "\\" + uuid + hz));
            }
        }
        return "hello";
    }
}
