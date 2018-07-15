package com.arch.demo004.controller;

import com.alibaba.fastjson.JSON;
import com.arch.demo004.component.User;
import com.arch.demo004.jpa.UserJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")

public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserJPA userJPA;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> getUserList(){
        logger.info("获取用户列表 getUserList");
        return userJPA.findAll();
    }


    @RequestMapping(value = "detail/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable("id")long id){
        List<User> users = userJPA.findUserById(id);
        if ((users.size()>0)){
            return users.get(0);
        }else return null;
    }

    @RequestMapping(value = "/pagelist",method = RequestMethod.GET)
    public Page<User> getPageUserList(Pageable pageable){
        logger.info("获取分页用户列表 getPageUserList");
        return userJPA.findAll(pageable);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult bindingResult){
        String result = "";
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErors =  bindingResult.getFieldErrors();
            for (FieldError fieldEror:fieldErors) {
                result += fieldEror.getField() + ":";
                result += fieldEror.getDefaultMessage();
                result +="/";
            }
        }else{
            logger.info("获取用户列表 getUserList");
            userJPA.save(user);
            result = JSON.toJSONString(user);
        }
        return result;
    }

    @RequestMapping(value = "/delete/{userid}",method = RequestMethod.POST)
    public List<User> deleteUser(@PathVariable(name = "userid")long userid){
        logger.info("删除用户"+ userid);
        userJPA.deleteUser(userid);
        return userJPA.findUserById(userid);
    }
}
