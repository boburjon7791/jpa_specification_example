package com.example.jpa_specification_example.specification;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.example.jpa_specification_example.model.entity.BaseEntity;
import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.entity.Student;

import jakarta.persistence.criteria.Root;

public interface SpecificationBuilder<T> extends Specification<T>{
    static <T> Specification<T> builder(){
        return Specification.where(null);
    }
    static void check(Object object){
        if(object==null){
            throw new NullPointerException("Object cannot be null");
        }
    }
    static void checkType(Class<?> classType, Root<?> root){
        if(!root.getJavaType().equals(classType)){
            throw new IllegalArgumentException("Specification entity type %s need to match %s class type".formatted(root.getJavaType().getName(), classType.getName()));
        }
    }
    default Specification<T> fullName(String fullName){
        check(fullName);
        return this.and((root, query, builder) -> {
            return builder.like(builder.lower(root.get(Student._fullName)), "%"+fullName.toLowerCase()+"%");
        });
    }
    default Specification<T> rateFrom(Integer rate){
        check(rate);
        return this.and((root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get(BaseEntity._rate), rate);
        });
    }
    default Specification<T> rateTo(Integer rate){
        check(rate);
        return this.and((root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get(BaseEntity._rate), rate);
        });
    }
    default Specification<T> createdAtFrom(LocalDateTime createdAt){
        check(createdAt);
        return this.and((root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get(BaseEntity._createdAt), createdAt);
        });
    }
    default Specification<T> groupId(Long groupId){
        check(groupId);
        return this.and((root, query, builder) -> {
            checkType(Student.class, root);
            return builder.equal(root.get(Student._group).get(Group._id), groupId);
        });
    }
}
