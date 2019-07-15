package com.zjw.service;

import com.zjw.entity.SysUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SysUsersPagingAndSortRepository extends PagingAndSortingRepository<SysUsers,Integer> {
    Page<SysUsers> findByAge(Integer age, Pageable pageable);
}
