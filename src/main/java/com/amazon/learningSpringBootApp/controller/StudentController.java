package com.amazon.learningSpringBootApp.controller;

import jakarta.validation.Valid;

import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.learningSpringBootApp.dto.AddNewStudentRequestDTO;
import com.amazon.learningSpringBootApp.dto.PageResponse;
import com.amazon.learningSpringBootApp.dto.StudentDTO;
import com.amazon.learningSpringBootApp.service.StudentService;

/**
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<PageResponse<StudentDTO>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(new PageResponse<>(studentService.getAllStudents(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentsById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody AddNewStudentRequestDTO addNewStudentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(addNewStudentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody @Valid AddNewStudentRequestDTO addNewStudentRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(id, addNewStudentRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO> patchStudent(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.patchStudent(id, updates));
    }

}
