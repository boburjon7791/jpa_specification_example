package com.example.jpa_specification_example.model.response;

import com.example.jpa_specification_example.model.entity.Student;

import java.time.LocalDateTime;

public record StudentResponse(
        Long id,
        LocalDateTime createdAt,
        String fullName,
        GroupResponse group,
        Integer rate
) {
    public static StudentResponse fromEntity(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getCreatedAt(),
                student.getFullName(),
                GroupResponse.fromEntity(student.getGroup()),
                student.getRate()
        );
    }
}
