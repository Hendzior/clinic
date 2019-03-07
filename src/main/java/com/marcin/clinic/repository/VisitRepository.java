package com.marcin.clinic.repository;

import com.marcin.clinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Long> {

    List<Visit> findByPatientSurname(String surname);
}
