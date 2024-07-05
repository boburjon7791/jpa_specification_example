package com.example.jpa_specification_example.model.request;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.entity.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record StudentCreate(
        @NotBlank
        String fullName,

        @NotNull
        @Positive
        Long groupId,

        @NotNull
        @PositiveOrZero
        Integer rate
) {
    public Student toEntity(Group group){
        return new Student(fullName.toLowerCase(), group, rate);
    }
}
