package com.zjw;

import com.zjw.entity.SysUsers;
import com.zjw.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RestController
public class controller {
    @Autowired
    private SysUsersRepository sysUsersRepository;
    @Autowired
    private SysUsersCrudRepository sysUsersCrudRepository;
    @Autowired
    private SysUsersPagingAndSortRepository sysUsersPagingAndSortRepository;
    @Autowired
    private SysUsersJpaRepository sysUsersJpaRepository;
    @Autowired
    private SysUserJPASpecificationExecutor sysUserJPASpecificationExecutor;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public void save() {
        SysUsers users = new SysUsers();
        users.setAddr("USA");
        users.setAge(12);
        users.setName("lucy");
        sysUsersRepository.save(users);
    }

    @RequestMapping(value="/findone",method = RequestMethod.GET)
    public List<SysUsers> findByName(String name){
        List<SysUsers> byName = sysUsersRepository.findByName(name);
        return byName;
    }

    @RequestMapping(value="/findmore",method = RequestMethod.GET)
    public List<SysUsers> findByNameAndAge(String name,int age){
        List<SysUsers> byName = sysUsersRepository.findByNameAndAge(name,age);
        return byName;
    }

    @RequestMapping(value="/findmoreplus",method = RequestMethod.GET)
    public List<SysUsers> findByNameAndAge(String name,int age,String addr){
        List<SysUsers> byName = sysUsersRepository.findByNameAndAgeAndAddr(name,age,addr);
        return byName;
    }

    @RequestMapping(value="/query",method = RequestMethod.GET)
    public List<SysUsers> query(String name){
        List<SysUsers> byName = sysUsersRepository.QueryByNameHQL(name);
        return byName;
    }

    @RequestMapping(value = "/curd/save",method = RequestMethod.GET)
    public void saveC() {
        SysUsers users = new SysUsers();
        users.setAddr("FRE");
        users.setAge(14);
        users.setName("nilda");
        sysUsersCrudRepository.save(users);
    }

    @RequestMapping(value = "/curd/delete/{id}",method = RequestMethod.GET)
    public void deleteC(@PathVariable("id")int id) {
        sysUsersCrudRepository.deleteById(id);
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public Iterable<SysUsers> sort(){
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "age"); // 定义排序规则
        Sort sort = new Sort(order); // 封装了排序的规则
        Iterable<SysUsers> all = sysUsersPagingAndSortRepository.findAll(sort);
        return all;
    }

    @RequestMapping(value = "/sort/paging", method = RequestMethod.GET)
    public Iterable<SysUsers> sortPaging(){
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "age"); // 定义排序规则
        Sort sort = new Sort(order); // 封装了排序的规则
        Iterable<SysUsers> all = sysUsersPagingAndSortRepository.findAll(new PageRequest(1,2,sort));
        return all;
    }

    @RequestMapping(value = "/sort/age", method = RequestMethod.GET)
    public Iterable<SysUsers> sortAge(int age){
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "age"); // 定义排序规则
        Sort sort = new Sort(order); // 封装了排序的规则
        Iterable<SysUsers> all = sysUsersPagingAndSortRepository.findByAge(age,new PageRequest(1,2,sort));
        return all;
    }

    @RequestMapping(value = "/jpa/sort", method = RequestMethod.GET)
    public Page<SysUsers> jpasort(){
        Page<SysUsers> all = sysUsersJpaRepository.findAll(new PageRequest(0, 2, Sort.Direction.ASC, "age"));
        return all;
    }

    @RequestMapping(value = "/all/list",method = RequestMethod.GET)
    public Page<SysUsers> executor(){
//        Pageable pageable = new PageRequest(0, 3, Sort.Direction.DESC,"age");
        Pageable pageable = PageRequest.of(0,3,Sort.Direction.DESC,"age");
        List<Predicate> list = new ArrayList<Predicate>();
        Specification<SysUsers> sysUsersSpecification = new Specification<SysUsers>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                list.add(criteriaBuilder.equal(root.get("name"),"lucy"));
                list.add(criteriaBuilder.equal(root.get("addr"),"USA"));
                Predicate p[] = new Predicate[list.size()];
                return criteriaBuilder.or(list.toArray(p));
            }
        };
        Page<SysUsers> all = sysUserJPASpecificationExecutor.findAll(sysUsersSpecification, pageable);
        System.out.println(all.getTotalElements());
        return all;
    }
}
