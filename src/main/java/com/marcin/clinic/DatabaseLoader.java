package com.marcin.clinic;

import com.marcin.clinic.model.Doctor;
import com.marcin.clinic.model.Patient;
import com.marcin.clinic.model.Visit;
import com.marcin.clinic.repository.DoctorRepository;
import com.marcin.clinic.repository.PatientRepository;
import com.marcin.clinic.repository.VisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class DatabaseLoader {


    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {


            patientRepository.save(new Patient("Name1", "Surname1"));
            patientRepository.save(new Patient("Name2", "Surname2"));
            patientRepository.save(new Patient("Name3", "Surname3"));
            doctorRepository.save(new Doctor("doctorName1", "doctorSurname1"));
            doctorRepository.save(new Doctor("doctorName2", "doctorSurname2"));
            doctorRepository.save(new Doctor("doctorName3", "doctorSurname3"));

            log.info("patient findAll() : ");
            for (Patient patient : patientRepository.findAll()) {

                log.info(patient.toString());

            }

            log.info("doctor findAll() : ");
            for (Doctor doctor : doctorRepository.findAll()) {
                log.info(doctor.toString());

            }

            visitRepository.save(new Visit(patientRepository.findById(1L).get(),
                    doctorRepository.findById(1L).get(), LocalDate.of(2020, 03, 12), "visit1"));
            visitRepository.save(new Visit(patientRepository.findById(2L).get(),
                    doctorRepository.findById(2L).get(), LocalDate.of(2019, 06, 05), "visit2"));
            visitRepository.save(new Visit(patientRepository.findById(3L).get(),
                    doctorRepository.findById(1L).get(), LocalDate.of(2019, 03, 17), "visit3"));
            visitRepository.save(new Visit(patientRepository.findById(1L).get(),
                    doctorRepository.findById(2L).get(), LocalDate.of(2019, 02, 28), "visit4"));

            log.info("visit findAll() : ");
            for (Visit visit : visitRepository.findAll()) {

                log.info(visit.toString());

            }

        };

    }
}
