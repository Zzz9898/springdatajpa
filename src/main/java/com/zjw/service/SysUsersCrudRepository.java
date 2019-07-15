package com.zjw.service;


import com.zjw.entity.SysUsers;
import org.springframework.data.repository.CrudRepository;

public interface SysUsersCrudRepository extends CrudRepository<SysUsers, Integer> {
    /**
     * 先不需要写接口
     */
}
