package com.example.jpa_specification_example.model.request;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.entity.Student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record StudentUpdate(
        @NotNull
        @PositiveOrZero
        Long id,

        @NotNull
        @PositiveOrZero
        Long groupId,

        @NotBlank
        String fullName,

        @NotNull
        @PositiveOrZero
        Integer rate
) {
    public Student toEntity(Group group){
        Student student = new Student(fullName.toLowerCase(), group, rate);
        student.setId(id);
        return student;
    }
}
