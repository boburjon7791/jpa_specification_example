package com.example.jpa_specification_example.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationData {
    private int page;
    private int numberOfElements;
    private int totalPages;
    private long totalElements;
    public static PaginationData of(Page<?> pagination){
        return PaginationData.builder()
                .page(pagination.getNumber())
                .totalElements(pagination.getTotalElements())
                .totalPages(pagination.getTotalPages())
                .numberOfElements(pagination.getNumberOfElements())
                .build();
    }
}
