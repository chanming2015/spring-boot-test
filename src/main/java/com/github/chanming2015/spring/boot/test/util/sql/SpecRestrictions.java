package com.github.chanming2015.spring.boot.test.util.sql;

import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
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
        };
    }

    public static SpecCriterion isNotNull(final String attributeName)
    {
        return new SpecCriterion()
        {
            @Override
            public <T> Predicate getPredicate(Root<T> root, CriteriaBuilder cb)
            {
                return cb.isNotNull(root.get(attributeName));
            }
        };
    }

    public static SpecCriterion eq(final String attributeName, final Object value)
    {
        return new SpecCriterion()
        {
            @Override
            public <T> Predicate getPredicate(Root<T> root, CriteriaBuilder cb)
            {
                return cb.equal(root.get(attributeName), value);
            }
        };
    }

    public static SpecCriterion ne(final String attributeName, final Object value)
    {
        return new SpecCriterion()
        {
            @Override
            public <T> Predicate getPredicate(Root<T> root, CriteriaBuilder cb)
            {
                return cb.notEqual(root.get(attributeName), value);
            }
        };
    }

    public static SpecCriterion like(final String attributeName, final String value)
    {
        return new SpecCriterion()
        {
            @Override
            public <T> Predicate getPredicate(Root<T> root, CriteriaBuilder cb)
            {
                Path<String> path = root.get(attributeName);
                return cb.like(path, value);
            }
        };
    }

    public static SpecCriterion notLike(final String attributeName, final String value)
    {
        return new SpecCriterion()
        {
            @Override
            public <T> Predicate getPredicate(Root<T> root, CriteriaBuilder cb)
            {
                Path<String> path = root.get(attributeName);
                return cb.notLike(path, value);
            }
        };
    }

    public static SpecCriterion in(final String attributeName, final Collection<?> value)
    {
        return new SpecCriterion()
        {
            @Override
            public <T> Predicate getPredicate(Root<T> root, CriteriaBuilder cb)
            {
                return root.get(attributeName).in(value);
            }
        };
    }

    public static SpecCriterion notIn(final String attributeName, final Collection<?> value)
    {
        return new SpecCriterion()
        {
            @Override
            public <T> Predicate getPredicate(Root<T> root, CriteriaBuilder cb)
            {
                return cb.not(root.get(attributeName).in(value));
            }
        };
    }

    @SuppressWarnings("rawtypes")
    public static SpecCriterion le(final String attributeName, final Comparable value)
    {
        return new SpecCriterion()
        {
            @SuppressWarnings("unchecked")
            @Override
            public <T> Predicate getPredicate(Root<T> root, CriteriaBuilder cb)
            {
                Path<? extends Comparable> path = root.get(attributeName);
                return cb.lessThanOrEqualTo(path, value);
            }
        };
    }

    @SuppressWarnings("rawtypes")
    public static SpecCriterion ge(final String attributeName, final Comparable<?> value)
    {
        return new SpecCriterion()
        {
            @SuppressWarnings("unchecked")
            @Override
            public <T> Predicate getPredicate(Root<T> root, CriteriaBuilder cb)
            {
                Path<? extends Comparable> path = root.get(attributeName);
                return cb.greaterThanOrEqualTo(path, value);
            }
        };
    }

    @SuppressWarnings("rawtypes")
    public static SpecCriterion between(final String attributeName, final Comparable lessValue, final Comparable greaterValue)
    {
        return new SpecCriterion()
        {
            @SuppressWarnings("unchecked")
            @Override
            public <T> Predicate getPredicate(Root<T> root, CriteriaBuilder cb)
            {
                Path<? extends Comparable> path = root.get(attributeName);
                return cb.between(path, lessValue, greaterValue);
            }
        };
    }

}
