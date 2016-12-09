package com.github.chanming2015.spring.boot.test.util.sql;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
                if (specParam != null)
                {
                    Predicate result = null;
                    Collection<SpecCriterion> specs = specParam.getCriterions();
                    if (specs.size() > 0)
                    {
                        Set<Predicate> predicates = new HashSet<Predicate>(4);
                        for (SpecCriterion spec : specs)
                        {
                            predicates.add(spec.getPredicate(root, cb));
                        }
                        result = cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
                    
                    Set<Collection<SpecCriterion>> orSpecSet = specParam.getOrSpecCriterions();
                    if ((orSpecSet != null) && (orSpecSet.size() > 0))
                    {
                        for (Collection<SpecCriterion> orSpecs : orSpecSet)
                        {
                            Set<Predicate> predicates = new HashSet<Predicate>(4);
                            for (SpecCriterion spec : orSpecs)
                            {
                                predicates.add(spec.getPredicate(root, cb));
                            }
                            Predicate orResult = cb.and(predicates.toArray(new Predicate[predicates.size()]));
                            result = cb.or(result, orResult);
                        }
                    }
                    return result;
                }
                return cb.conjunction();
            }
        };
    }
}
