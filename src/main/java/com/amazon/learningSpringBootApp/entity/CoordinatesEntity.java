package com.amazon.learningSpringBootApp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double latitude;
    private Double longitude;
    
    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;
}
