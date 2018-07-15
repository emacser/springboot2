package com.arch.demo004.reporitory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisReporitory {
    @Autowired
    private StringRedisTemplate template;

    public  void setKey(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value);
    }

    public String getValue(String key){
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }
}