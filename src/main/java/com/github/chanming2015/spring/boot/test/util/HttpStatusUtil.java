package com.github.chanming2015.spring.boot.test.util;

import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSONObject;

/**
 * Description:
 * Create Date:2018年5月17日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class HttpStatusUtil
{
    public static HttpStatus okMethodHttpStatus(JSONObject result)
    {
        return result.containsKey("errMsg") ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
    }

    public static HttpStatus createMethodHttpStatus(JSONObject result)
    {
        return result.containsKey("errMsg") ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
    }
}
