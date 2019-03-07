package com.marcin.clinic.repository;

import com.marcin.clinic.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    List<Patient> findBySurname(String name);
}
