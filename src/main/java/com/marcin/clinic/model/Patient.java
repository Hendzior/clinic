package com.marcin.clinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
public @Data class Patient {

    @NonNull
    private String name;
    @NonNull
    private String surname;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
