package com.example.jpa_specification_example.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.jpa_specification_example.exception.ApiException;
import com.example.jpa_specification_example.model.common.Header;
import com.example.jpa_specification_example.model.common.PaginationData;
import com.example.jpa_specification_example.model.common.ResponseCodes;
import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.request.GroupCreate;
import com.example.jpa_specification_example.model.request.GroupUpdate;
import com.example.jpa_specification_example.model.request.get_all.GroupGet;
import com.example.jpa_specification_example.model.response.GroupResponse;
import com.example.jpa_specification_example.repository.GroupRepository;
import com.example.jpa_specification_example.specification.BaseSpecification;
import com.example.jpa_specification_example.specification.GroupSpecification;
import com.example.jpa_specification_example.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    public Group group(Long id){
        return groupRepository.findById(id)
                .orElseThrow(() -> new ApiException("Could not find group", ResponseCodes.NOT_FOUND));
    }
    public GroupResponse save(GroupCreate request){
        if (groupRepository.existsByName(request.name())){
            throw new ApiException("Group with the same name already exists", ResponseCodes.BAD_REQUEST);
        }
        return GroupResponse.fromEntity(groupRepository.save(request.toEntity()));
    }
    public GroupResponse update(GroupUpdate request){
        return GroupResponse.fromEntity(
            groupRepository.save(request.toEntity(group(request.id())))
        );
    }
    public GroupResponse findById(Long id){
        return GroupResponse.fromEntity(group(id));
    }
    public void deleteById(Long id){
        groupRepository.deleteById(id);
    }
    public Header<?> findAll(GroupGet request){
        Sort sort=Utils.sortById();
        
        Specification<Group> baseSpecification=BaseSpecification.createBaseSpecification(request);
        baseSpecification=GroupSpecification.concatWithBaseSpecification(baseSpecification, request);

        if(request.all){
            return Header.ok(groupRepository.findAll(baseSpecification, sort).stream()
                    .map(GroupResponse::fromEntity)
                    .collect(Collectors.toList()));
        }

        Page<?> page= groupRepository.findAll(baseSpecification, request.pageable())
                                        .map(GroupResponse::fromEntity);
        return Header.ok(page.getContent(), PaginationData.of(page));
    }
}
