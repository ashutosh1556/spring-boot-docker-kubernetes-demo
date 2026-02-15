package com.amazon.learningSpringBootApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoDTO {
    private String primaryPhone;
    private String secondaryPhone;
    private String emergencyContact;
    private String emergencyPhone;
}
