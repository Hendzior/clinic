package com.marcin.clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.print.Doc;
import java.time.LocalDate;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public @Data class Visit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @OneToOne
    private Patient patient;
    @NonNull
    @OneToOne
    private Doctor doctor;
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitDate;
    @NonNull
    private String description;



}
