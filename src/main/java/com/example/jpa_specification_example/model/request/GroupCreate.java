package com.example.jpa_specification_example.model.request;

import com.example.jpa_specification_example.model.entity.Group;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record GroupCreate(
        @NotBlank
        String name,

        @NotNull
        @PositiveOrZero
        Integer rate
) {
    public Group toEntity(){
        return new Group(name, rate);
    }
}
