package com.example.jpa_specification_example.specification;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.request.get_all.GroupGet;
import org.springframework.data.jpa.domain.Specification;

public class GroupSpecification extends BaseSpecification<Group>{
    public static Specification<Group> createSpecification(GroupGet request){
        Specification<Group> specification = build(request, Group.class);
        if(request.search!=null){
            specification=specification.and(name(request.search));
        }
        return specification;
    }
    private static Specification<Group> name(String name){
        return (root, criteria, builder) -> {
            return builder.like(builder.lower(root.get(Group._name)), "%"+name.toLowerCase()+"%");
        };
    }
}
