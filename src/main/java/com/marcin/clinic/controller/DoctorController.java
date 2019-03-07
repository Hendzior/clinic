package com.marcin.clinic.controller;

import com.marcin.clinic.model.Doctor;
import com.marcin.clinic.repository.DoctorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/add")
    public String addDoctor(ModelMap modelMap) {
        modelMap.addAttribute("doctor", new Doctor());

        return "addDoctor";
    }

    @PostMapping
    public String createDoctor(@Valid @ModelAttribute Doctor doctor, BindingResult bindingResult, ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            return "addDoctor";
        }
        doctorRepository.save(doctor);
        modelMap.addAttribute("message", "Doctor added!");

        return "show";
    }

    @GetMapping
    public String getAllDoctors(ModelMap modelMap) {
        modelMap.addAttribute("doctors", doctorRepository.findAll());
        return "show";
    }

    @GetMapping("/{id}/edit")
    public String editDoctor(@PathVariable Long id, ModelMap modelMap) {

        modelMap.addAttribute("doctor",
                doctorRepository.findById(id).get());
        return "addDoctor";
    }

    @PostMapping("/edit")
    public String updateDoctor(Doctor doctor, ModelMap modelMap) {
        doctorRepository.save(doctor);
        modelMap.addAttribute("message", "Doctor updated!");
        return "show";
    }

    @GetMapping("/{id}/delete")
    public String deleteDoctor(@PathVariable Long id, ModelMap modelMap) {
        doctorRepository.delete(doctorRepository.findById(id).get());
        modelMap.addAttribute("message", "Doctor deleted!");
        return "show";
    }
}
