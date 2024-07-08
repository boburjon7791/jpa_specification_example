package com.example.jpa_specification_example.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.entity.Student;
import com.example.jpa_specification_example.model.request.get_all.StudentGet;

import jakarta.persistence.criteria.Join;

public class StudentSpecification{
    public static Specification<Student> createSpecification(StudentGet request){
        Specification<Student> specification=Specification.where(null);
        if(request.groupId!=null){
            specification=specification.and(groupId(request.groupId));
        }
        if(request.search!=null){
            specification=specification.and(fullName(request.search));
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
    private static Specification<Student> groupId(Long groupId){
        return (root, query, builder) -> {
            Join<Student,Group> groupJoin = root.join(Student._group);
            return builder.equal(groupJoin.get(Group._id), groupId);
        };
    }
    private static Specification<Student> fullName(String fullName){
        return (root, criteria, builder) -> {
            return builder.like(builder.lower(root.get(Student._fullName)), "%"+fullName.toLowerCase()+"%");
        };
    }
    private static Specification<Student> fromDate(LocalDate createdAt){
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get(Student._createdAt), createdAt.atTime(0, 0));
        };
    }
    private static Specification<Student> toDate(LocalDate createdAt){
        return (root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get(Student._createdAt), createdAt.atTime(23, 59));
        };
    }
    private static Specification<Student> fromRate(Integer rate){
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get(Student._rate), rate);
        };
    }
    private static Specification<Student> toRate(Integer rate){
        return (root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get(Student._rate), rate);
        };
    }
}
