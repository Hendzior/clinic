package com.marcin.clinic.controller;

import com.marcin.clinic.model.Visit;
import com.marcin.clinic.repository.DoctorRepository;
import com.marcin.clinic.repository.PatientRepository;
import com.marcin.clinic.repository.VisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@RequestMapping("/visit")
@Controller
public class VisitController {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private VisitRepository visitRepository;

    public VisitController(VisitRepository visitRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.visitRepository = visitRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/add")
    public String addVisit(ModelMap modelMap) {
        modelMap.addAttribute("visit", new Visit());
        modelMap.addAttribute("patients", patientRepository.findAll());
        modelMap.addAttribute("doctors", doctorRepository.findAll());
        return "addVisit";
    }

    @PostMapping
    public String saveVisit(@Valid @ModelAttribute Visit visit, BindingResult bindingResult, ModelMap modelMap,
                            @RequestParam Long patientId, Long doctorId,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !patientRepository.findById(patientId).isPresent() || !doctorRepository.findById(doctorId).isPresent()) {
            redirectAttributes.addFlashAttribute("visit", visit);
            return "redirect:/visit/add";
        }

        visit.setPatient(patientRepository.findById(patientId).get());
        visit.setDoctor(doctorRepository.findById(doctorId).get());
        visitRepository.save(visit);
        modelMap.addAttribute("message", "Visit Added");
        modelMap.addAttribute("visits", visitRepository.findAll());
        return "showVisit";
    }

    @GetMapping
    public String showAllVisits(ModelMap modelMap) {
        log.info("visits: {}", visitRepository.findAll());
        modelMap.addAttribute("visits", visitRepository.findAll());
        return "showVisit";
    }

    @GetMapping("/{id}/delete")
    public String deleteVisit(@PathVariable Long id, ModelMap modelMap) {
        Optional<Visit> visitOptional = visitRepository.findById(id);
        if (visitOptional.isPresent()) {
            visitRepository.delete(visitOptional.get());
            return "redirect:/visit";
        } else {
            modelMap.addAttribute("message", "Visit id = " + id + "not found");
        }
        return "redirect:/visit";
    }

    @GetMapping("/surname")
    public String getVisitByPatientSurname(@RequestParam String surname, ModelMap modelMap) {

        modelMap.addAttribute("visits", visitRepository.findByPatientSurname(surname));
        modelMap.addAttribute("message", "Visits for patient : " + surname);

        return "showVisit";
    }

    @GetMapping("/date")
    public String getVisitByDate(@RequestParam String visitDate, ModelMap modelMap) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        modelMap.addAttribute("visits", visitRepository.findByDate(LocalDate.parse(visitDate)));
        modelMap.addAttribute("message", "Visits for date : " + visitDate);

        return "showVisit";
    }
}
