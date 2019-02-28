package com.marcin.clinic.controller;

import com.marcin.clinic.model.Doctor;
import com.marcin.clinic.repository.DoctorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/add")
    public String addDoctor(ModelMap modelMap){
        modelMap.addAttribute("doctor", new Doctor());

        return "addDoctor";
    }

    @PostMapping
    public String createDoctor(@ModelAttribute Doctor doctor, ModelMap modelMap){

        doctorRepository.save(doctor);
        modelMap.addAttribute("message", "Doctor added!");

        return "show";
    }
    @GetMapping
    public String getAllDoctors(ModelMap modelMap){
        modelMap.addAttribute("doctors", doctorRepository.findAll());
    return "show";
    }

}
