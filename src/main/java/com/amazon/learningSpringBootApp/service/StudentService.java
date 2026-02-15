package com.amazon.learningSpringBootApp.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.amazon.learningSpringBootApp.dto.AddNewStudentRequestDTO;
import com.amazon.learningSpringBootApp.dto.StudentDTO;

public interface StudentService {

    Page<StudentDTO> getAllStudents(int page, int size);

    StudentDTO getStudentById(Long id);

    StudentDTO createStudent(AddNewStudentRequestDTO addNewStudentDTO);

    void deleteStudentById(Long id);

    StudentDTO patchStudent(Long id, Map<String, Object> updates);

    StudentDTO updateStudent(Long id, AddNewStudentRequestDTO addNewStudentRequestDTO);
}
