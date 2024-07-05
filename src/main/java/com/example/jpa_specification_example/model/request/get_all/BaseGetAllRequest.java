package com.example.jpa_specification_example.model.request.get_all;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public class BaseGetAllRequest {
    public int page;
    public int size;
    public String name;
    public Integer rateFrom;
    public Integer rateTo;
    public LocalDate from;
    public LocalDate to;
    public boolean all;
    public List<SortRequest> sorts;
    public List<Order> getSorts(){
        return sorts.stream()
                    .map(SortRequest::toSort)
                    .toList();
    }
    public Pageable pageable(){
        return PageRequest.of(page, size, Sort.by(getSorts()));
    }
}
