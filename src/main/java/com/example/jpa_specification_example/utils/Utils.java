package com.example.jpa_specification_example.utils;

public interface Utils {
    String BASE_URL="/api";
    static String firstUpperCase(String title){
        StringBuilder first=new StringBuilder(Character.toUpperCase(title.charAt(0)));
        StringBuilder second = new StringBuilder(title.substring(1));
        return title;
    }
}
