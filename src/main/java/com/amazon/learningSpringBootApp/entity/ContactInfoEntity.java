package com.amazon.learningSpringBootApp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String primaryPhone;
    private String secondaryPhone;
    private String emergencyContact;
    private String emergencyPhone;
    
    @OneToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
}
