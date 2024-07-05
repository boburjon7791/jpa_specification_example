package com.example.jpa_specification_example.model.request;

import com.example.jpa_specification_example.model.entity.Group;
import jakarta.validation.constraints.NotBlank;

public record GroupCreate(
        @NotBlank
        String name,

        @NotBlank
        Integer rate
) {
    public Group toEntity(){
        return new Group(name,rate);
    }
}
