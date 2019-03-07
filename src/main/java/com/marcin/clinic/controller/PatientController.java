package com.marcin.clinic.controller;

import com.marcin.clinic.model.Patient;
import com.marcin.clinic.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String createPatient(@ModelAttribute @Valid Patient patient, BindingResult bindingResult, ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            return "addPatient";
        }

        patientRepository.save(patient);
        modelMap.addAttribute("message", "Patient added!");
        return "show";
    }

    @GetMapping
    public String getAllPatients(ModelMap modelMap) {
        modelMap.addAttribute("patients", patientRepository.findAll());
        return "show";
    }

    @GetMapping("/{id}/delete")
    public String deletePatient(@PathVariable Long id, ModelMap modelMap) {

        Patient patient = patientRepository.findById(id).get();
        patientRepository.delete(patient);
        modelMap.addAttribute("patient", patient);
        modelMap.addAttribute("message", "Patient deleted!");

        return "show";
    }

    @GetMapping("/{id}/edit")
    public String updatePatient(@PathVariable Long id, ModelMap modelMap) {
        modelMap.addAttribute("patient", patientRepository.findById(id).get());
        modelMap.addAttribute("message", "Update patient:");

        return "addPatient";
    }

    @GetMapping("/surname")
    public String findPatientBySurname(@RequestParam String surname, ModelMap modelMap) {

        modelMap.addAttribute("patients", patientRepository.findBySurname(surname));
        return "show";
    }

}
