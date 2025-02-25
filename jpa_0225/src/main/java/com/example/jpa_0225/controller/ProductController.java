package com.example.jpa_0225.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa_0225.repository.CategoryRepository;
import com.example.jpa_0225.repository.ProductRepository;
import com.example.jpa_0225.store.Category1JPO;
import com.example.jpa_0225.store.ProductJPO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("test")
    public String test() {
        ProductJPO p1 = ProductJPO.builder()
            .pName("빈센트 아몬드")
            .pCompany("Art Box").build();

        System.out.println(p1);
        productRepository.save(p1);
        return "test";    
    }

    @GetMapping("list")
    public String getList() {

        List<ProductJPO> list = productRepository.findAll(Sort.by(Sort.Direction.DESC,"pNum"));
        StringBuffer sb = new StringBuffer();

        sb.append("<h1>상품별 카테고리 목록</h1>");
        for(ProductJPO p : list) {
            sb.append(p.getPNum());
            sb.append("/");
            sb.append(p.getPName());
            sb.append("/");
            sb.append(p.getCvo1().getCName());
            sb.append("/");
            sb.append(p.getCvo1().getDesc());
            sb.append("<br>");
        }
        return sb.toString();
    }

    @GetMapping("cList")
    public String getClist() {

        List<Category1JPO> categories = categoryRepository.findAll();
        StringBuffer sb = new StringBuffer();

        sb.append("<h1>카테고리별 상품 목록</h1>");
        for (Category1JPO c : categories) {
            sb.append(c.getIdx());
            sb.append("/");
            sb.append(c.getCName());
            sb.append("/");
            sb.append(c.getCName());
            sb.append("/");
            sb.append(c.getDesc());
            sb.append("</br>");
            for (ProductJPO p : c.getList()) {
                sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
                sb.append(p.getPNum());
                sb.append("/");
                sb.append(p.getPName());
                sb.append("</br>");
            }
        }
        return sb.toString();
    }
    
    
}
