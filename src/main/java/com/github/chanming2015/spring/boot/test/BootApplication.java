package com.github.chanming2015.spring.boot.test;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Description: Create Date:2016年12月7日
 *
 * @author XuMaoSen Version:1.0.0
 */
@SpringBootApplication
@EnableSwagger2
public class BootApplication
{
    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(BootApplication.class, args);
    }

    @Bean
    public Docket createApi()
    {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.github.chanming2015.spring.boot.test.web")).paths(paths()).build().apiInfo(apiInfo())
                .directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class).useDefaultResponseMessages(false)
                .enableUrlTemplating(true);
    }

    private Predicate<String> paths()
    {
        return Predicates.and(PathSelectors.regex("/.*"), Predicates.not(PathSelectors.regex("/error")));
    }

    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder().title("Document Api").description("Spring-boot-Springfox Example").license("Apache License Version 2.0")
                .contact("476076812@qq.com").version("2.0").build();
    }
}
