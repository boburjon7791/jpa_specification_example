package com.example.jpa_specification_example.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.jpa_specification_example.exception.ApiException;
import com.example.jpa_specification_example.model.common.Header;
import com.example.jpa_specification_example.model.entity.Group;
import com.example.jpa_specification_example.model.entity.Student;
import com.example.jpa_specification_example.model.request.StudentCreate;
import com.example.jpa_specification_example.model.request.StudentUpdate;
import com.example.jpa_specification_example.model.request.get_all.StudentGet;
import com.example.jpa_specification_example.model.response.StudentResponse;
import com.example.jpa_specification_example.repository.GroupRepository;
import com.example.jpa_specification_example.repository.StudentRepository;
import com.example.jpa_specification_example.specification.GroupSpecification;
import com.example.jpa_specification_example.specification.StudentSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    public Group group(Long id){
        return groupRepository.findById(id)
               .orElseThrow(() -> new ApiException()
                       .message("Group not found")
                       .status(HttpStatus.NOT_FOUND));
    }
    public StudentResponse save(StudentCreate request){
        return StudentResponse.fromEntity(studentRepository.save(request.toEntity(group(request.groupId()))));
    }
    public StudentResponse update(StudentUpdate request){
        return StudentResponse.fromEntity(studentRepository.save(request.toEntity(group(request.groupId()))));
    }
    public StudentResponse findById(Long id){
        return StudentResponse.fromEntity(
                studentRepository.findById(id)
                    .orElseThrow(() -> new ApiException()
                            .message("Student not found")
                            .status(HttpStatus.NOT_FOUND))
        );
    }
    public void deleteById(Long id){
        studentRepository.deleteById(id);
    }
    public Header<?> getAll(StudentGet request){
        Sort sort=Sort.by(request.getSorts());
        Specification<Student> specification = StudentSpecification.rateFrom(request.rateFrom)
                                                        .and(StudentSpecification.rateTo(request.rateTo))
                                                        .and(StudentSpecification.createdAtFrom(request.from))
                                                        .and(StudentSpecification.createdAtTo(request.to));

        if(request.all){
            return Header.ok(studentRepository.findAll(specification, sort).stream()
                    .map(StudentResponse::fromEntity)
                    .collect(Collectors.toList()));
        }
        return Header.ok(
            studentRepository.findAll(specification, request.pageable())
                                        .map(StudentResponse::fromEntity)
        );
    }
}
