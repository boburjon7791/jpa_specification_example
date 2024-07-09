package com.example.jpa_specification_example.specification;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.request.get_all.BaseGetAllRequest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class BaseSpecification<T> {
    protected static <T> Specification<T> build(BaseGetAllRequest request, Class<T> returnType){
        Specification<T> specification= Specification.where(null);
        if (request.from!=null) {
            specification=specification.and(fromDate(request.from));
        }
        if (request.to!=null) {
            specification=specification.and(toDate(request.to));
        }
        if (request.rateFrom!=null) {
            specification=specification.and(fromRate(request.rateFrom));
        }
        if (request.rateTo!=null) {
            specification=specification.and(toRate(request.rateTo));
        }
        return specification;
    }
    protected static <T> Specification<T> fromDate(LocalDate createdAt){
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get(Group._createdAt), createdAt.atTime(0,0));
        };
    }
    protected static <T> Specification<T> toDate(LocalDate createdAt){
        return (root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get(Group._createdAt), createdAt.atTime(23,59));
        };
    }
    protected static <T> Specification<T> fromRate(Integer rate){
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get(Group._rate), rate);
        };
    }
    protected static <T> Specification<T> toRate(Integer rate){
        return (root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get(Group._rate), rate);
        };
    }
}
