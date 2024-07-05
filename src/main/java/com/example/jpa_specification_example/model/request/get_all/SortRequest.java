package com.example.jpa_specification_example.model.request.get_all;

import org.springframework.data.domain.Sort.Order;

public record SortRequest(
    String property,
    String sort
) {
    public Order toSort(){
        return sort.equalsIgnoreCase("ASC") ? Order.asc(property): Order.desc(property);
    }
}
