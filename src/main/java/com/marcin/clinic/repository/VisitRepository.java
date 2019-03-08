package com.marcin.clinic.repository;

import com.marcin.clinic.model.Visit;
import org.apache.tomcat.jni.Local;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Long> {

    List<Visit> findByPatientSurname(String surname);

    List<Visit> findByDate(LocalDate localDate);
}
