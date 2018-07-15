package com.arch.demo004.jpa;

import com.arch.demo004.component.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@CacheConfig(cacheNames = "user")
public interface UserJPA extends JpaRepository<User,Long>, JpaSpecificationExecutor<User>,Serializable {

    @Transactional
    @Modifying
    @Query(value = "UPDATE T_USER SET isenable= 1 WHERE id=:enable",nativeQuery = true)
    void isenableUser(@Param("enable") long id);

    @Transactional
    @Modifying
    @Query("update T_USER u set u.isenable = 0 where u.id = ?1")
    void deleteUser(Long id);

    @Cacheable
    List<User> findUserById(Long id);
}
