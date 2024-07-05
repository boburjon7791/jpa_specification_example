package com.example.jpa_specification_example.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa_specification_example.model.common.Header;
import com.example.jpa_specification_example.model.request.StudentCreate;
import com.example.jpa_specification_example.model.request.StudentUpdate;
import com.example.jpa_specification_example.model.request.get_all.StudentGet;
import com.example.jpa_specification_example.service.StudentService;
import com.example.jpa_specification_example.utils.Utils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = Utils.BASE_URL+"/student")
public class StudentController {
    private final StudentService studentService;
    @PostMapping
    public Header<?> save(@RequestBody @Valid Header<StudentCreate> request){
        return Header.ok(studentService.save(request.getData()));
    }
    @PutMapping
    public Header<?> update(@RequestBody @Valid Header<StudentUpdate> request){
        return Header.ok(studentService.update(request.getData()));
    }
    @DeleteMapping("/{id}")
    public Header<?> deleteById(@PathVariable Long id){
        studentService.deleteById(id);
        return Header.ok();
    }
    @GetMapping("/{id}")
    public Header<?> findById(@PathVariable Long id){
        return Header.ok(studentService.findById(id));
    }
    @GetMapping
    public Header<?> findAll(@RequestBody @Valid Header<StudentGet> request){
        return studentService.getAll(request.getData());
    }
}
