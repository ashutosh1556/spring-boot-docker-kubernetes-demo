package com.amazon.learningSpringBootApp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Student entity representing a student in the system.
 * 
 * Demonstrates JPA relationships:
 * - OneToOne with AddressEntity (bidirectional)
 * - OneToOne with ContactInfoEntity (bidirectional)
 * - OneToMany with EnrollmentEntity (bidirectional)
 * 
 * All relationships use CascadeType.ALL and orphanRemoval for automatic
 * management of related entities.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private Integer age;
    
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private AddressEntity address;
    
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private ContactInfoEntity contactInfo;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnrollmentEntity> enrollments = new ArrayList<>();
}
