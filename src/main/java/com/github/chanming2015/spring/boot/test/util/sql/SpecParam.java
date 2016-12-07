package com.github.chanming2015.spring.boot.test.util.sql;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * Description:
 * Create Date:2016年12月7日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class SpecParam<T>
{
    private final Map<String, SpecCriterion> specCriterions = new HashMap<String, SpecCriterion>();

    public SpecParam<T> isNull(String fieldName)
    {
        if (!StringUtils.isEmpty(fieldName))
        {
            specCriterions.put(fieldName, SpecRestrictions.isNull(fieldName));
        }
        return this;
    }

    public Collection<SpecCriterion> getCriterions()
    {
        return specCriterions.values();
    }
}
