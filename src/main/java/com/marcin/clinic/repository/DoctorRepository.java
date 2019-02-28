package com.marcin.clinic.repository;

import com.marcin.clinic.model.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
}
