package com.amazon.learningSpringBootApp.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String program;
    private String major;
    private LocalDate enrollmentDate;
    private String status;
    private Double gpa;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
}
