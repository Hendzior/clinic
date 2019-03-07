package com.marcin.clinic.model;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public @Data
class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 30)
    @NonNull
    private String name;
    @Size(min =2, max = 30)
    @NonNull
    private String surname;
    @Email
    private String email;
    @Size(min = 9, max = 11)
    @NumberFormat
    private String phoneNumber;

}
