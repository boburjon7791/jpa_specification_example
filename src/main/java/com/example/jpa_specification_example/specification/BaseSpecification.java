package com.example.jpa_specification_example.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.request.get_all.BaseGetAllRequest;

public class BaseSpecification<T> {
    private static <T> Specification<T> rateFrom(Integer rate){
        return (root, criteria, builder) -> {
            return builder.greaterThanOrEqualTo(root.get(Group._rate), rate);
        };
    }

    private static <T> Specification<T> rateTo(Integer rate){
        return (root, criteria, builder) -> {
            return builder.lessThanOrEqualTo(root.get(Group._rate), rate);
        };
    }
    private static <T> Specification<T> createdAtFrom(LocalDate createdAt){
        return (root, criteria, builder) -> {
            return builder.lessThanOrEqualTo(root.get(Group._createdAt), createdAt.atTime(0,0));
        };
    }
    private static <T> Specification<T> createdAtTo(LocalDate createdAt){
        return (root, criteria, builder) -> {
            return builder.greaterThanOrEqualTo(root.get(Group._createdAt), createdAt.atTime(23,59));
        };
    }
    public static <T> Specification<T> createBaseSpecification(BaseGetAllRequest request){
        Specification<T> specification = Specification.where(null);
        
        if(request.rateFrom!=null){
            specification=specification.or(BaseSpecification.rateFrom(request.rateFrom));
        }
        if(request.rateTo!=null){
            specification=specification.or(BaseSpecification.rateTo(request.rateTo));
        }
        if(request.from!=null){
            specification=specification.or(BaseSpecification.createdAtFrom(request.from));
        }
        if(request.to!=null){
            specification=specification.or(BaseSpecification.createdAtTo(request.to));
        }
        
        return specification;
    }
}
