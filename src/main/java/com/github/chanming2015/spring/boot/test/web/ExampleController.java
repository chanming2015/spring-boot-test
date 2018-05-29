package com.github.chanming2015.spring.boot.test.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.chanming2015.spring.boot.test.entity.Test;
import com.github.chanming2015.spring.boot.test.repository.TestRepository;
import com.github.chanming2015.utils.sql.SpecParam;
import com.github.chanming2015.utils.sql.SpecProperty;
import com.github.chanming2015.utils.sql.SpecUtil;
import com.github.chanming2015.utils.sql.TupleRepository;

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
    @Autowired
    private TupleRepository tupleRepository;

    @ApiOperation(value = "测试接口")
    @ApiResponse(code = 200, message = "OK")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    ResponseEntity<String> home()
    {

        SpecParam<Test> specParam = new SpecParam<Test>();
        specParam.between("version", Integer.valueOf(2), Double.valueOf("6.6"));

        SpecParam<Test> specParam2 = new SpecParam<Test>();
        specParam2.eq("username", "Bob");
        specParam2.eq("deleted", Boolean.FALSE);

        SpecParam<Test> specParam3 = new SpecParam<Test>();
        specParam3.like("password", "he%");
        specParam3.isNotNull("id");

        specParam.or(specParam2).or(specParam3);

        List<Test> list = repository.findAll(SpecUtil.spec(specParam));

        SpecProperty p1 = SpecProperty.forName("username");
        SpecProperty p2 = SpecProperty.forName("password");

        List<Tuple> l2 = tupleRepository.findAll(Test.class, SpecUtil.spec(specParam), new Sort(Direction.DESC, "id"), null, p1, p2);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
