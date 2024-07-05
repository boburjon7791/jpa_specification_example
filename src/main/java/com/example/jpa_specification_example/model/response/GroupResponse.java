package com.example.jpa_specification_example.model.response;

import com.example.jpa_specification_example.model.entity.Group;

import java.time.LocalDateTime;

public record GroupResponse(
        Long id,
        LocalDateTime createdAt,
        String name,
        Integer rate
) {
    public static GroupResponse fromEntity(Group entity) {
        return new GroupResponse(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getName(),
                entity.getRate()
        );
    }
}
