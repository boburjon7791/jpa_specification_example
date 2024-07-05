package com.example.jpa_specification_example.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.entity.Student;

import jakarta.persistence.criteria.Join;

public class StudentSpecification {
    public static Specification<Student> fullNameContains(String name){
        return (root, query, builder) -> builder.like(builder.lower(root.get(Student._fullName)), "%"+name.toLowerCase()+"%");
    }
    public static Specification<Student> rateFrom(Integer rate){
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(Student._rate), rate);
    }
    public static Specification<Student> rateTo(Integer rate){
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get(Student._rate), rate);
    }
    public static Specification<Student> createdAtFrom(LocalDate createdAt){
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get(Student._createdAt), createdAt.atTime(0, 0));
    }
    public static Specification<Student> createdAtTo(LocalDate createdAt){
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(Student._createdAt), createdAt.atTime(23,59));
    }
    public static Specification<Student> groupId(Long groupId){
        return (root, query, builder) -> {
            Join<Student,Group> groupJoin = root.join("group");
            return builder.equal(groupJoin.get("id"), groupId);
        };
    }
}
