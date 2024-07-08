package com.example.jpa_specification_example.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.request.get_all.GroupGet;

public class GroupSpecification{
    public static Specification<Group> createSpecification(GroupGet request){
        Specification<Group> specification = Specification.where(null);
        if(request.search!=null){
            specification=specification.and(name(request.search));
        }
        if(request.from!=null){
            specification=specification.and(fromDate(request.from));
        }
        if(request.to!=null){
            specification=specification.and(toDate(request.to));
        }
        if(request.rateFrom!=null){
            specification=specification.and(fromRate(request.rateFrom));
        }
        if(request.rateTo!=null){
            specification=specification.and(toRate(request.rateTo));
        }
        return specification;
    }
    private static Specification<Group> name(String name){
        return (root, criteria, builder) -> {
            return builder.like(builder.lower(root.get(Group._name)), "%"+name.toLowerCase()+"%");
        };
    }
    private static Specification<Group> fromDate(LocalDate createdAt){
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get(Group._createdAt), createdAt);
        };
    }
    private static Specification<Group> toDate(LocalDate createdAt){
        return (root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get(Group._createdAt), createdAt);
        };
    }
    private static Specification<Group> fromRate(Integer rate){
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get(Group._rate), rate);
        };
    }
    private static Specification<Group> toRate(Integer rate){
        return (root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get(Group._rate), rate);
        };
    }
}
