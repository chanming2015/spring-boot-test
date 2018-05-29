package com.github.chanming2015.spring.boot.test.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 * Create Date:2018年5月17日
 * @author XuMaoSen
 * Version:1.0.0
 */
@Component
public class RestTemplateUtil
{
    @Autowired
    private RestTemplate restTemplate;
    private static final Map<String, ?> EMPTY_URI_VARIABLES = new HashMap<String, String>();

    public <T> T post(String url, Class<T> cls, Map<String, String> heads, Map<String, ?> uriVariables, String body)
    {
        ResponseEntity<T> rss = request(url, HttpMethod.POST, cls, heads, uriVariables, body);
        return rss.getBody();
    }

    private <T> ResponseEntity<T> request(String url, HttpMethod method, Class<T> responseType, Map<String, String> heads, Map<String, ?> uriVariables, String body)
    {
        HttpHeaders requestHeaders = new HttpHeaders();
        if (heads != null)
        {
            requestHeaders.setAll(heads);
        }
        if (uriVariables == null)
        {
            uriVariables = EMPTY_URI_VARIABLES;
        }
        HttpEntity<String> requestEntity = new HttpEntity<String>(body, requestHeaders);
        ResponseEntity<T> rss = restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
        return rss;
    }
}
