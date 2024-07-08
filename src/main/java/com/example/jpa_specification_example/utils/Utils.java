package com.example.jpa_specification_example.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public interface Utils {
    String BASE_URL="/api";
    static String firstUpperCase(String title){
        // StringBuilder first=new StringBuilder(Character.toUpperCase(title.charAt(0)));
        // StringBuilder second = new StringBuilder(title.substring(1));
        return title;
    }
    static Sort sortById(){
        return Sort.by(Direction.ASC, "id");
    }
}
