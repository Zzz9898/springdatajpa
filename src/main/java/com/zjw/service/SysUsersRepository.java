package com.zjw.service;

import com.zjw.entity.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysUsersRepository extends JpaRepository<SysUsers, Integer> {

    /**
     * JpaRepository<T,ID>
     *
     * T:当前需要映射的实体。 ID:当前映射实体中ID的类型
     */
    // 单条件
    List<SysUsers> findByName(String name);

    // 多条件（and）
    List<SysUsers> findByNameAndAge(String name, int age);

    // 多条件（or）
    List<SysUsers> findByNameOrAge(String name, int age);

    // 单条件（like）
    List<SysUsers> findByNameLike(String name);

    // 多条件（and）
    List<SysUsers> findByNameAndAgeAndAddr(String name, int age, String addr);

    @Query("from SysUsers where name = ?1")
    List<SysUsers> QueryByNameHQL(String name);
}