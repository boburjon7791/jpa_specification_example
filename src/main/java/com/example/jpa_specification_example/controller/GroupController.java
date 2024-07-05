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
import com.example.jpa_specification_example.model.request.GroupCreate;
import com.example.jpa_specification_example.model.request.GroupUpdate;
import com.example.jpa_specification_example.model.request.get_all.GroupGet;
import com.example.jpa_specification_example.service.GroupService;
import com.example.jpa_specification_example.utils.Utils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = Utils.BASE_URL+"/group")
public class GroupController {
    private final GroupService groupService;
    @PostMapping
    public Header<?> save(@RequestBody @Valid Header<GroupCreate> request){
        return Header.ok(groupService.save(request.getData()));
    }
    @PutMapping
    public Header<?> update(@RequestBody @Valid Header<GroupUpdate> request){
        return Header.ok(groupService.update(request.getData()));
    }
    @GetMapping("/{id}")
    public Header<?> getById(@PathVariable Long id){
        return Header.ok(groupService.findById(id));
    }
    @GetMapping
    public Header<?> findAll(@RequestBody @Valid Header<GroupGet> request){
        return groupService.findAll(request.getData());
    }
    @DeleteMapping("/{id}")
    public Header<?> deleteById(@PathVariable Long id){
        groupService.deleteById(id);
        return Header.ok();
    }
}
