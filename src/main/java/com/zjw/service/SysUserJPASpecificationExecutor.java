package com.zjw.service;

import com.zjw.entity.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysUserJPASpecificationExecutor extends JpaSpecificationExecutor<SysUsers>, JpaRepository<SysUsers, Integer> {

}
