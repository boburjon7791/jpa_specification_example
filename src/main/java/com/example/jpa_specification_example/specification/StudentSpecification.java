package com.example.jpa_specification_example.specification;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.entity.Student;

import jakarta.persistence.criteria.Join;

public class StudentSpecification {
    public static Specification<Student> nameEquals(String name){
        return (root, query, builder) -> builder.like(root.get(Student._fullName), name.toLowerCase());
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
    public static Specification<Student> groupEquals(Long groupId){
        return (root, query, builder) -> {
            Join<Student,Group> groupJoin = root.join("group");
            return builder.equal(groupJoin.get("id"), groupId);
        };
    }
}
