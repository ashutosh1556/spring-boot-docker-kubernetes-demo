package com.amazon.learningSpringBootApp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class AddNewStudentRequestDTO {

    @NotBlank(message = "Name should not be blank")
    @Size(max = 255, min = 3, message = "Name should be minimum 3 characters long")
    private String name;

    @Email
    @NotBlank(message = "Email should not be blank")
    private String email;
    
    @NotNull(message = "Age should not be null")
    @Min(value = 1, message = "Age must be at least 1")
    private Integer age;
    
    @Valid
    private AddressDTO address;
    
    @Valid
    private ContactInfoDTO contactInfo;
    
    @Valid
    private List<EnrollmentDTO> enrollments;
}
