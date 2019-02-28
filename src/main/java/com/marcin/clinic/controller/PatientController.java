package com.marcin.clinic.controller;

import com.marcin.clinic.model.Patient;
import com.marcin.clinic.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/add")
    public String addPatient(ModelMap modelMap) {
        modelMap.addAttribute("patient", new Patient());

        return "addPatient";

    }

    @PostMapping
    public String createPatient(@ModelAttribute Patient patient, ModelMap modelMap) {

        patientRepository.save(patient);
        modelMap.addAttribute("message", "Patient added!");

        return "show";

    }

    @GetMapping
    public String getAllPatients(ModelMap modelMap) {
        modelMap.addAttribute("patients", patientRepository.findAll());
        return "show";
    }

}
