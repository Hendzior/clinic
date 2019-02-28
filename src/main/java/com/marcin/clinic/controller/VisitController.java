package com.marcin.clinic.controller;

import com.marcin.clinic.model.Visit;
import com.marcin.clinic.repository.DoctorRepository;
import com.marcin.clinic.repository.PatientRepository;
import com.marcin.clinic.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/visit")
@Controller
public class VisitController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    private VisitRepository visitRepository;

    public VisitController(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;

    }

    @GetMapping("/add")
    public String addVisit(ModelMap modelMap) {
        modelMap.addAttribute("visit", new Visit());
        modelMap.addAttribute("patients", patientRepository.findAll());
        modelMap.addAttribute("doctors", doctorRepository.findAll());
        return "addVisit";

    }

    @PostMapping
    public String saveVisit(@ModelAttribute Visit visit, ModelMap modelMap, @RequestParam(required = false) @Nullable Long patientId, Long doctorId) {

        visit.setPatient(patientRepository.findById(patientId).get());
        visit.setDoctor(doctorRepository.findById(doctorId).get());
        visitRepository.save(visit);
        modelMap.addAttribute("message", "Visit Added");
        modelMap.addAttribute("visits", visitRepository.findAll());
        return "showVisit";
    }

    @GetMapping
    public String showAllVisits(ModelMap modelMap) {

        modelMap.addAttribute("visits", visitRepository.findAll());

        return "showVisit";
    }

}
