package com.github.chanming2015.spring.boot.test.util.sql;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Description:
 * Create Date:2016年12月7日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class SpecRestrictions
{
    public static SpecCriterion isNull(final String attributeName)
    {
        return new SpecCriterion()
        {
            @Override
            public <T> Predicate getPredicate(Root<T> root, CriteriaBuilder cb)
            {
                return cb.isNull(root.get(attributeName));
            }

            @Override
            public String getName()
            {
                return attributeName;
            }
        };
    }
}
