package com.example.jpa_specification_example.specification;

import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.example.jpa_specification_example.model.entity.Group;

public class GroupSpecification {
    public static Specification<Group> nameContains(String name){
        return (root, criteria, builder) -> {
            return builder.like(root.get(Group._name), name.toLowerCase());
        };
    }

    public static Specification<Group> rateFrom(Integer rate){
        return (root, criteria, builder) -> {
            return builder.greaterThanOrEqualTo(root.get(Group._rate), rate);
        };
    }

    public static Specification<Group> rateTo(Integer rate){
        return (root, criteria, builder) -> {
            return builder.lessThanOrEqualTo(root.get(Group._rate), rate);
        };
    }
    public static Specification<Group> createdAtFrom(LocalDate createdAt){
        return (root, criteria, builder) -> {
            return builder.lessThanOrEqualTo(root.get(Group._createdAt), createdAt.atTime(0,0));
        };
    }
    public static Specification<Group> createdAtTo(LocalDate createdAt){
        return (root, criteria, builder) -> {
            return builder.greaterThanOrEqualTo(root.get(Group._createdAt), createdAt.atTime(23,59));
        };
    }
}
