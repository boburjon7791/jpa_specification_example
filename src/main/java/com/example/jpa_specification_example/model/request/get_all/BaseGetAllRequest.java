package com.example.jpa_specification_example.model.request.get_all;
import java.time.LocalDate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class BaseGetAllRequest {
    public int page;
    public int size=10;
    public String search;
    public Integer rateFrom;
    public Integer rateTo;
    public LocalDate from;
    public LocalDate to;
    public boolean all;
    public Pageable pageable(Sort sort){
        return PageRequest.of(page, size, sort);
    }

    public Pageable pageable(){
        return PageRequest.of(page, size);
    }
}
