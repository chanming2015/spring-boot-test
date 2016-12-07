package com.github.chanming2015.spring.boot.test.util.sql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * Description: Create Date:2016年12月7日
 *
 * @author XuMaoSen Version:1.0.0
 */
public class SpecUtil
{
    public static <T> Specification<T> spec(final SpecParam<T> specParam)
    {
        return new Specification<T>()
        {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb)
            {
                if ((specParam != null))
                {
                    Predicate result = null;
                    if (specParam.getCriterions().size() > 0)
                    {
                        List<Predicate> predicates = new ArrayList<Predicate>(specParam
                                .getCriterions().size());
                        for (SpecCriterion spec : specParam.getCriterions())
                        {
                            predicates.add(spec.getPredicate(root, cb));
                        }
                        result = cb.and(predicates.toArray(new Predicate[predicates.size()]));
                        return result;
                    }
                }
                return cb.conjunction();
            }
        };
    }
}
