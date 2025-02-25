package com.example.jpa2_0225.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa2_0225.service.EmpService;
import com.example.jpa2_0225.store.Emp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("all")
    public String getAll() {
        StringBuffer sb = new StringBuffer();

        Emp[] ar = empService.getAll();
        for (Emp emp : ar) {
            sb.append(emp.getEmpno());
            sb.append("/");
            sb.append(emp.getEname());
            sb.append("<br/>");
        }
        return sb.toString();
    }

    @PostMapping("searchName")
    public String searchName(@RequestParam String ename) {
        StringBuffer sb = new StringBuffer();

        Emp[] ar = empService.searchEname(ename);
        if (ar != null) {
            for (Emp emp : ar) {
                sb.append(emp.getEmpno());
                sb.append("/");
                sb.append(emp.getEname());
                sb.append("<br/>");
            }
        }
        
        return sb.toString();
    }

    @PostMapping("searchName2")
    public String searchName2(@RequestParam String ename) {
        StringBuffer sb = new StringBuffer();

        Emp[] ar = empService.searchEname2(ename);
        if (ar != null) {
            for (Emp emp : ar) {
                sb.append(emp.getEmpno());
                sb.append("/");
                sb.append(emp.getEname());
                sb.append("<br/>");
            }
        }
        
        return sb.toString();
    }

    @PostMapping("searchNameLike")
    public String searchNameLike(@RequestParam String ename) {
        StringBuffer sb = new StringBuffer();

        Emp[] ar = empService.searchEnameLike(ename);
        if (ar != null) {
            for (Emp emp : ar) {
                sb.append(emp.getEmpno());
                sb.append("/");
                sb.append(emp.getEname());
                sb.append("<br/>");
            }
        }
        
        return sb.toString();
    }

    @GetMapping("searchJobAndDeptno")
    public String searchJobAndDeptno(@RequestParam String job, @RequestParam String deptno) {
        StringBuffer sb = new StringBuffer();

        Emp[] ar = empService.searchJobAndDeptno(job, deptno);
        if (ar != null) {
            for (Emp emp : ar) {
                sb.append(emp.getEmpno());
                sb.append("/");
                sb.append(emp.getEname());
                sb.append("/");
                sb.append(emp.getJob());
                sb.append("/");
                sb.append(emp.getDeptno());
                sb.append("<br/>");
            }
        }
        
        return sb.toString();
    }
    
    @GetMapping("salLessThan")
    public String salLessThan(@RequestParam long sal) {
        StringBuffer sb = new StringBuffer();

        Emp[] ar = empService.salLessThan(sal);
        if (ar != null) {
            for (Emp emp : ar) {
                sb.append(emp.getEmpno());
                sb.append("/");
                sb.append(emp.getEname());
                sb.append("/");
                sb.append(emp.getSal());
                sb.append("<br/>");
            }
        }
        
        return sb.toString();
    }
}
