package com.arch.demo004.jpa;

import com.arch.demo004.component.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.io.Serializable;
import java.util.List;

public interface LogJPA extends JpaRepository<LoggerEntity,Long> ,JpaSpecificationExecutor<LoggerEntity> ,Serializable, QuerydslPredicateExecutor<LoggerEntity> {

    List<LoggerEntity> findUserById(long id);

    @Query("select  u from d_log  u where u.id=?1")
    List<LoggerEntity> getLog(long id);
}
