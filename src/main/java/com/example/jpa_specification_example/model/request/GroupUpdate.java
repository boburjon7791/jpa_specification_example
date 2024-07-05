package com.example.jpa_specification_example.model.request;

import com.example.jpa_specification_example.model.entity.Group;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record GroupUpdate(
        @NotNull
        Long id,

        @NotBlank
        String name,

        @NotNull
        @PositiveOrZero
        Integer rate
) {
    public Group toEntity(){
        Group group = new Group(name.toLowerCase(), rate);
        group.setId(id);
        return group;
    }
}
