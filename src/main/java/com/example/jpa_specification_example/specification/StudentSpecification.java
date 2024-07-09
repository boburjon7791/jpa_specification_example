package com.example.jpa_specification_example.specification;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.entity.Student;
import com.example.jpa_specification_example.model.request.get_all.StudentGet;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification extends BaseSpecification<Student>{
    public static Specification<Student> createSpecification(StudentGet request){
        Specification<Student> specification=build(request, Student.class);
        if(request.groupId!=null){
            specification=specification.and(groupId(request.groupId));
        }
        if(request.search!=null){
            specification=specification.and(fullName(request.search));
        }
        return specification;
    }
    private static Specification<Student> groupId(Long groupId){
        return (root, query, builder) -> {
            return builder.equal(root.get(Group._group).get(Group._id), groupId);
        };
    }
    private static Specification<Student> fullName(String fullName){
        return (root, criteria, builder) -> {
            return builder.like(builder.lower(root.get(Student._fullName)), "%"+fullName.toLowerCase()+"%");
        };
    }
}
