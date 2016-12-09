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
public interface SpecCriterion
{
    <T> Predicate getPredicate(Root<T> root, CriteriaBuilder cb);
}
