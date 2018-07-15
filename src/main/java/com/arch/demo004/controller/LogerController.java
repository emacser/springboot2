package com.arch.demo004.controller;


import com.arch.demo004.common.Inquirer;
import com.arch.demo004.component.LoggerEntity;
import com.arch.demo004.component.QLoggerEntity;
import com.arch.demo004.jpa.LogJPA;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping(value = "/log")
@RestController
public class LogerController {

    @Autowired
    private LogJPA logJPA;

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/list")
    private List<LoggerEntity> getAllLog(){
        return logJPA.findAll();
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public List<LoggerEntity> deleteUser(@PathVariable(name = "id")long id){
        return logJPA.getLog(id);
    }

    @RequestMapping(value = "/query/{id}")
    public List<LoggerEntity> getLog(@PathVariable(name = "id")long id) throws ParseException {
        QLoggerEntity qlog = QLoggerEntity.loggerEntity;
        JPAQuery<LoggerEntity> query = new JPAQuery<>(entityManager);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2018-07-07");
        return query.select(qlog).from(qlog).where(qlog.id.eq(id) , qlog.addtime.before(date)).fetch();
    }

    @RequestMapping(value = "/dateBefore/{dateStr}")
    public List<LoggerEntity> getLogByTime(@PathVariable(name = "dateStr")String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateStr);

        QLoggerEntity qlog = QLoggerEntity.loggerEntity;
        Inquirer inquirer = new Inquirer();
        inquirer.putExpression(qlog.addtime.after(date));

        return inquirer.iteratorToList(logJPA.findAll(inquirer.buidleQuery()));
    }
}
