package com.example.jpa_specification_example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa_specification_example.utils.Utils;

@RestController
@RequestMapping(value = Utils.BASE_URL+"/group")
public class GroupController {
    @GetMapping("/test")
    public String test(){
        return "GroupController test";
    }
}
