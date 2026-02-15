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
 * REST Controller for Student Management operations.
 * 
 * Provides endpoints for CRUD operations on students with pagination support.
 * Base URL: /students
 * 
 * Endpoints:
 * - GET    /students          - Get all students (paginated)
 * - GET    /students/{id}     - Get student by ID
 * - POST   /students          - Create new student
 * - PUT    /students/{id}     - Update entire student
 * - PATCH  /students/{id}     - Partial update student
 * - DELETE /students/{id}     - Delete student
 * 
 * @author Learning Spring Boot
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    /**
     * Get all students with pagination.
     * 
     * @param page Page number (default: 0)
     * @param size Page size (default: 5)
     * @return Paginated list of students
     */
    @GetMapping
    public ResponseEntity<PageResponse<StudentDTO>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(new PageResponse<>(studentService.getAllStudents(page, size)));
    }

    /**
     * Get a specific student by ID.
     * 
     * @param id Student ID
     * @return Student details
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentsById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
    }

    /**
     * Create a new student.
     * 
     * @param addNewStudentDTO Student data
     * @return Created student with generated ID
     */
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody AddNewStudentRequestDTO addNewStudentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(addNewStudentDTO));
    }

    /**
     * Delete a student by ID.
     * 
     * @param id Student ID to delete
     * @return No content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Update entire student record (replaces all fields).
     * 
     * @param id Student ID
     * @param addNewStudentRequestDTO Complete student data
     * @return Updated student
     */
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody @Valid AddNewStudentRequestDTO addNewStudentRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(id, addNewStudentRequestDTO));
    }

    /**
     * Partially update student (only specified fields).
     * 
     * @param id Student ID
     * @param updates Map of field names to new values
     * @return Updated student
     */
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO> patchStudent(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.patchStudent(id, updates));
    }

}
