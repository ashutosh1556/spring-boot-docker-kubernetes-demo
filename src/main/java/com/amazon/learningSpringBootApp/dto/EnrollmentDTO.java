package com.amazon.learningSpringBootApp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {
    private String program;
    private String major;
    private LocalDate enrollmentDate;
    private String status;
    private Double gpa;
}
