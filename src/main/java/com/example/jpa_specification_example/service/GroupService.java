package com.example.jpa_specification_example.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.jpa_specification_example.exception.ApiException;
import com.example.jpa_specification_example.model.common.Header;
import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.request.GroupCreate;
import com.example.jpa_specification_example.model.request.GroupUpdate;
import com.example.jpa_specification_example.model.request.get_all.GroupGet;
import com.example.jpa_specification_example.model.response.GroupResponse;
import com.example.jpa_specification_example.repository.GroupRepository;
import com.example.jpa_specification_example.specification.GroupSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    public GroupResponse save(GroupCreate request){
        return GroupResponse.fromEntity(groupRepository.save(request.toEntity()));
    }
    public GroupResponse update(GroupUpdate request){
        return GroupResponse.fromEntity(groupRepository.save(request.toEntity()));
    }
    public GroupResponse findById(Long id){
        return GroupResponse.fromEntity(
                groupRepository.findById(id)
                    .orElseThrow(() -> new ApiException()
                            .message("Group not found")
                            .status(HttpStatus.NOT_FOUND))
        );
    }
    public void deleteById(Long id){
        groupRepository.deleteById(id);
    }
    public Header<?> getAll(GroupGet request){
        Sort sort=Sort.by(request.getSorts());
        Specification<Group> specification=GroupSpecification.rateFrom(request.rateFrom)
                                                            .and(GroupSpecification.rateTo(request.rateTo))
                                                            .and(GroupSpecification.createdAtFrom(request.from))
                                                            .and(GroupSpecification.nameContains(request.name));
        if(request.all){
            return Header.ok(groupRepository.findAll(specification, sort).stream()
                    .map(GroupResponse::fromEntity)
                    .collect(Collectors.toList()));
        }
        return Header.ok(
            groupRepository.findAll(specification, request.pageable())
                                        .map(GroupResponse::fromEntity)
        );
    }
}
