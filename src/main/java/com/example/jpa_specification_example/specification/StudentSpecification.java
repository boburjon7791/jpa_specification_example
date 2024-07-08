package com.example.jpa_specification_example.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.jpa_specification_example.model.entity.BaseEntity;
import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.entity.Student;
import com.example.jpa_specification_example.model.request.get_all.StudentGet;

<<<<<<< HEAD
public class StudentSpecification{
    private static Specification<Student> groupId(Long groupId){
        return (root, query, builder) -> {
            return builder.equal(root.get(Group._group).get(BaseEntity._id), groupId);
=======
import jakarta.persistence.criteria.Join;

public class StudentSpecification{
<<<<<<< HEAD
    public static Specification<Student> createSpecification(StudentGet request){
        Specification<Student> specification=Specification.where(null);
        if(request.groupId!=null){
            specification=specification.and(groupId(request.groupId));
        }
        if(request.search!=null){
            specification=specification.and(fullName(request.search));
        }
        if(request.rateFrom!=null){
            specification=specification.and(fromDate(request.from));
        }
        if(request.rateTo!=null){
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
    private static Specification<Student> groupId(Long groupId){
        return (root, query, builder) -> {
            Join<Student,Group> groupJoin = root.join(Student._group);
            return builder.equal(groupJoin.get(Group._id), groupId);
<<<<<<< HEAD
        };
    }
    private static Specification<Student> fullName(String fullName){
        return (root, criteria, builder) -> {
            return builder.like(builder.lower(root.get(Student._fullName)), "%"+fullName.toLowerCase()+"%");
        };
    }
    private static Specification<Student> fromDate(LocalDate createdAt){
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get(Student._createdAt), createdAt);
        };
    }
    private static Specification<Student> toDate(LocalDate createdAt){
        return (root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get(Student._createdAt), createdAt);
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
=======
<<<<<<< HEAD
>>>>>>> 814b1a2d6b0b6fe2ab3088da96c3478b10d9a431
=======
>>>>>>> 814b1a2d6b0b6fe2ab3088da96c3478b10d9a431
>>>>>>> 56a589a41be0159aa4fc1ec363edac72f8f842cc
        };
    }
    private static Specification<Student> fullName(String fullName){
        return (root, criteria, builder) -> {
            return builder.like(builder.lower(root.get(Student._fullName)), "%"+fullName.toLowerCase()+"%");
        };
    }
    public static Specification<Student> concatWithBaseSpecification(Specification<Student> baseSpecification, StudentGet request){
        if(request.groupId!=null){
            baseSpecification=baseSpecification.and(StudentSpecification.groupId(request.groupId));
        }
        if(request.search!=null){
            baseSpecification=baseSpecification.and(StudentSpecification.fullName(request.search));
        }
        return baseSpecification;
    }
}
