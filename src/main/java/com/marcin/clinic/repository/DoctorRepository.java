package com.marcin.clinic.repository;

import com.marcin.clinic.model.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    List<Doctor> findBySurname(String name);
}
