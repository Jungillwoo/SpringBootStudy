package com.example.jpa2_0225.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpa2_0225.repository.EmpRepository;
import com.example.jpa2_0225.store.Emp;

@Service
public class EmpService {

    @Autowired
    private EmpRepository empRepository;

    public Emp[] getAll() {
        Emp[] ar = null;

        List<Emp> list = empRepository.findAll();
        if (list != null && list.size() > 0) {
            ar = new Emp[list.size()];
            list.toArray(ar);
        }

        return ar;
    }

    public Emp[] searchEname(String ename) {
        Emp[] ar = null;

        List<Emp> list = empRepository.findByEname(ename);
        if (list != null && list.size() > 0) {
            ar = new Emp[list.size()];
            list.toArray(ar);
        }

        return ar;
    }

    public Emp[] searchEname2(String ename) {
        Emp[] ar = null;

        List<Emp> list = empRepository.findByEnameContaining(ename);
        if (list != null && list.size() > 0) {
            ar = new Emp[list.size()];
            list.toArray(ar);
        }

        return ar;
    }

    public Emp[] searchEnameLike(String ename) {
        Emp[] ar = null;

        List<Emp> list = empRepository.findByEnameLike("%"+ename+"%");
        if (list != null && list.size() > 0) {
            ar = new Emp[list.size()];
            list.toArray(ar);
        }

        return ar;
    }

    public Emp[] searchJobAndDeptno(String job, String deptno) {
        Emp[] ar = null;

        List<Emp> list = empRepository.findByJobContainsAndDeptno(job, deptno);
        if (list != null && list.size() > 0) {
            ar = new Emp[list.size()];
            list.toArray(ar);
        }

        return ar;
    }

    public Emp[] salLessThan(long sal) {
        Emp[] ar = null;

        List<Emp> list = empRepository.findBySalLessThan(sal);
        if (list != null && list.size() > 0) {
            ar = new Emp[list.size()];
            list.toArray(ar);
        }

        return ar;
    }

}
