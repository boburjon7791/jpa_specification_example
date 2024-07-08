package com.example.jpa_specification_example.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.request.get_all.GroupGet;

public class GroupSpecification{
<<<<<<< HEAD
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
=======
>>>>>>> 56a589a41be0159aa4fc1ec363edac72f8f842cc
    private static Specification<Group> name(String name){
        return (root, criteria, builder) -> {
            return builder.like(builder.lower(root.get(Group._name)), "%"+name.toLowerCase()+"%");
        };
    }
<<<<<<< HEAD
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
=======
    public static Specification<Group> concatWithBaseSpecification(Specification<Group> baseSpecification, GroupGet request){
        if(request.search!=null){
            return baseSpecification.and(name(request.search));
        }
        return baseSpecification;
>>>>>>> 56a589a41be0159aa4fc1ec363edac72f8f842cc
    }
}
