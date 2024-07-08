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
    private static Specification<Student> groupId(Long groupId){
        return (root, query, builder) -> {
            Join<Student,Group> groupJoin = root.join(Student._group);
            return builder.equal(groupJoin.get(Group._id), groupId);
>>>>>>> 814b1a2d6b0b6fe2ab3088da96c3478b10d9a431
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
