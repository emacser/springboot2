package com.arch.demo004.controller;

import com.arch.demo004.reporitory.RedisReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping(value = "/redis")
public class RedisContorller {

    @Autowired
    private RedisReporitory redisReporitory;

    @RequestMapping("/set")
    public String set(String key,String value){
        redisReporitory.setKey(key, value);
        return "success";
    }
    @RequestMapping("/get")
    public String get(String key){
        return redisReporitory.getValue(key);
    }
}
