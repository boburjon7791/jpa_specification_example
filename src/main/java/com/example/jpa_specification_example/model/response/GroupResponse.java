package com.example.jpa_specification_example.model.response;

import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.utils.Utils;

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
                Utils.firstUpperCase(entity.getName()),
                entity.getRate()
        );
    }
}
