package com.amazon.learningSpringBootApp.service.impl;

import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amazon.learningSpringBootApp.dto.AddNewStudentRequestDTO;
import com.amazon.learningSpringBootApp.dto.StudentDTO;
import com.amazon.learningSpringBootApp.entity.AddressEntity;
import com.amazon.learningSpringBootApp.entity.ContactInfoEntity;
import com.amazon.learningSpringBootApp.entity.CoordinatesEntity;
import com.amazon.learningSpringBootApp.entity.EnrollmentEntity;
import com.amazon.learningSpringBootApp.entity.StudentEntity;
import com.amazon.learningSpringBootApp.repository.StudentRepository;
import com.amazon.learningSpringBootApp.service.StudentService;

/**
 *
 */
@Service
@RequiredArgsConstructor
class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<StudentDTO> getAllStudents(int page, int size) {
        Page<StudentEntity> students = studentRepository.findAll(PageRequest.of(page, size));
        return students.map(student -> modelMapper.map(student, StudentDTO.class));
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        StudentEntity student = studentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with given id"));

        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO createStudent(AddNewStudentRequestDTO addNewStudentDTO) {
        StudentEntity student = new StudentEntity();
        student.setName(addNewStudentDTO.getName());
        student.setEmail(addNewStudentDTO.getEmail());
        student.setAge(addNewStudentDTO.getAge());

        if (addNewStudentDTO.getAddress() != null) {
            AddressEntity address = modelMapper.map(addNewStudentDTO.getAddress(), AddressEntity.class);
            address.setStudent(student);
            student.setAddress(address);

            if (addNewStudentDTO.getAddress().getCoordinates() != null) {
                CoordinatesEntity coordinates = modelMapper.map(addNewStudentDTO.getAddress().getCoordinates(), CoordinatesEntity.class);
                coordinates.setAddress(address);
                address.setCoordinates(coordinates);
            }
        }

        if (addNewStudentDTO.getContactInfo() != null) {
            ContactInfoEntity contactInfo = modelMapper.map(addNewStudentDTO.getContactInfo(), ContactInfoEntity.class);
            contactInfo.setStudent(student);
            student.setContactInfo(contactInfo);
        }

        if (addNewStudentDTO.getEnrollments() != null && !addNewStudentDTO.getEnrollments().isEmpty()) {
            addNewStudentDTO.getEnrollments().forEach(enrollmentDTO -> {
                EnrollmentEntity enrollment = modelMapper.map(enrollmentDTO, EnrollmentEntity.class);
                enrollment.setStudent(student);
                student.getEnrollments().add(enrollment);
            });
        }

        StudentEntity savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student not found with given id");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO patchStudent(Long id, Map<String, Object> updates) {
        StudentEntity student = studentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with given id"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> student.setName((String) value);
                case "email" -> student.setEmail((String) value);
                case "age" -> student.setAge((Integer) value);
                case "address", "contactInfo", "enrollments" -> {
                    // Ignore complex nested objects in PATCH - use PUT for full updates
                }
                default -> throw new IllegalArgumentException("Invalid update key: " + key);
            }
        });
        studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO updateStudent(Long id, AddNewStudentRequestDTO addNewStudentRequestDTO) {
        StudentEntity student = studentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with given id"));

        student.setName(addNewStudentRequestDTO.getName());
        student.setEmail(addNewStudentRequestDTO.getEmail());
        student.setAge(addNewStudentRequestDTO.getAge());

        if (addNewStudentRequestDTO.getAddress() != null) {
            if (student.getAddress() == null) {
                AddressEntity address = modelMapper.map(addNewStudentRequestDTO.getAddress(), AddressEntity.class);
                address.setStudent(student);
                student.setAddress(address);
            } else {
                modelMapper.map(addNewStudentRequestDTO.getAddress(), student.getAddress());
            }
        }

        if (addNewStudentRequestDTO.getContactInfo() != null) {
            if (student.getContactInfo() == null) {
                ContactInfoEntity contactInfo = modelMapper.map(addNewStudentRequestDTO.getContactInfo(), ContactInfoEntity.class);
                contactInfo.setStudent(student);
                student.setContactInfo(contactInfo);
            } else {
                modelMapper.map(addNewStudentRequestDTO.getContactInfo(), student.getContactInfo());
            }
        }

        if (addNewStudentRequestDTO.getEnrollments() != null) {
            student.getEnrollments().clear();
            addNewStudentRequestDTO.getEnrollments().forEach(enrollmentDTO -> {
                EnrollmentEntity enrollment = modelMapper.map(enrollmentDTO, EnrollmentEntity.class);
                enrollment.setStudent(student);
                student.getEnrollments().add(enrollment);
            });
        }

        studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }

}

