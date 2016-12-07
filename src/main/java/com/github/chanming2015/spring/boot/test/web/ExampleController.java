package com.github.chanming2015.spring.boot.test.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.chanming2015.spring.boot.test.entity.Test;
import com.github.chanming2015.spring.boot.test.repository.TestRepository;
import com.github.chanming2015.spring.boot.test.util.sql.SpecParam;
import com.github.chanming2015.spring.boot.test.util.sql.SpecUtil;

/**
 * Description:
 * Create Date:2016年12月7日
 * @author XuMaoSen
 * Version:1.0.0
 */
@RestController
public class ExampleController
{
    @Autowired
    private TestRepository repository;

    @RequestMapping("/")
    String home()
    {
        SpecParam<Test> specParam = new SpecParam<Test>();
        specParam.isNull("username");
        List<Test> list = repository.findAll(SpecUtil.spec(specParam));
        return list.size() + "";
    }
}
