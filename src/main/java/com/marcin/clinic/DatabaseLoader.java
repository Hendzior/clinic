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

            patientRepository.save(new Patient(1L, "Name1", "Surname1", "patient1@gmail.com", "606 606 606"));
            patientRepository.save(new Patient(2L, "Name2", "Surname2", "patient2@gmail.com", "222 606 666"));
            patientRepository.save(new Patient(3L, "Name3", "Surname3", "patient3@gmail.com", "333 444 606"));
            doctorRepository.save(new Doctor(1L, "doctorName1", "doctorSurname1", "doctor1@gmail.com", "888 606 606"));
            doctorRepository.save(new Doctor(2L, "doctorName2", "doctorSurname2", "doctor1@gmail.com", "111 606 606"));
            doctorRepository.save(new Doctor(3L, "doctorName3", "doctorSurname3", "doctor1@gmail.com", "444 606 606"));

            log.debug("patient findAll() : ");
            for (Patient patient : patientRepository.findAll()) {

                log.debug(patient.toString());

            }
            log.debug("Patient findBySurname() : {}", patientRepository.findBySurname("Surname2"));

            log.debug("doctor findAll() : ");
            for (Doctor doctor : doctorRepository.findAll()) {
                log.debug(doctor.toString());

            }

            visitRepository.save(new Visit(1L, patientRepository.findById(1L).get(),
                    doctorRepository.findById(1L).get(), LocalDate.of(2020, 11, 12), "visit1"));
            visitRepository.save(new Visit(2L, patientRepository.findById(2L).get(),
                    doctorRepository.findById(2L).get(), LocalDate.of(2019, 6, 15), "visit2"));
            visitRepository.save(new Visit(3L, patientRepository.findById(3L).get(),
                    doctorRepository.findById(1L).get(), LocalDate.of(2019, 9, 17), "visit3"));
            visitRepository.save(new Visit(4L, patientRepository.findById(1L).get(),
                    doctorRepository.findById(2L).get(), LocalDate.of(2019, 6, 28), "visit4"));

            log.debug("visit findAll() : ");
            for (Visit visit : visitRepository.findAll()) {

                log.debug("find visit by patient surname : {}", visitRepository.findByPatientSurname("Surname1"));
            }

        };

    }
}
