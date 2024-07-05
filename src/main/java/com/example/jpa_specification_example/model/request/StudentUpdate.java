package com.example.jpa_specification_example.model.request;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.entity.Student;

public record StudentUpdate(
        Long id,
        Long groupId,
        String fullName,
        Integer rate
) {
    public Student toEntity(Group group){
        Student student = new Student(fullName.toLowerCase(), group, rate);
        student.setId(id);
        return student;
    }
}
