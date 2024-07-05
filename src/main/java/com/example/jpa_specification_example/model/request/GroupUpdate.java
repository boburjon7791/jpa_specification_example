package com.example.jpa_specification_example.model.request;

import com.example.jpa_specification_example.model.entity.Group;
import jakarta.validation.constraints.NotNull;

public record GroupUpdate(
        @NotNull
        Long id,

        String name,

        Integer rate
) {
    public Group toEntity(){
        Group group = new Group(name, rate);
        group.setId(id);
        return group;
    }
}
