package com.amazon.learningSpringBootApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private AddressDTO address;
    private ContactInfoDTO contactInfo;
    private List<EnrollmentDTO> enrollments;
}
