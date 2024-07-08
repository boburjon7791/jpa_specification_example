package com.example.jpa_specification_example.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.request.get_all.GroupGet;

public class GroupSpecification{
    private static Specification<Group> name(String name){
        return (root, criteria, builder) -> {
            return builder.like(builder.lower(root.get(Group._name)), "%"+name.toLowerCase()+"%");
        };
    }
    public static Specification<Group> concatWithBaseSpecification(Specification<Group> baseSpecification, GroupGet request){
        if(request.search!=null){
            return baseSpecification.and(name(request.search));
        }
        return baseSpecification;
    }
}
