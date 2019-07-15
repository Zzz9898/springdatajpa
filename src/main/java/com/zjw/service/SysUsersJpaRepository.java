package com.zjw.service;

import com.zjw.entity.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SysUsersJpaRepository extends JpaRepository<SysUsers, Integer> {
    /**
     * Repository<T,ID>
     *
     * T:当前需要映射的实体。 ID:当前映射实体中ID的类型
     */
}
