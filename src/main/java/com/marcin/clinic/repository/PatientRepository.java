package com.marcin.clinic.repository;

import com.marcin.clinic.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {



}
