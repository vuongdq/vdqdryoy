package com.vdqdryoy.controller;


import com.vdqdryoy.model.Office;
import com.vdqdryoy.model.Profile;
import com.vdqdryoy.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/admin/profile")
public class ProfileController {

    @Autowired
    ProfileRepository profileRepository;

    @GetMapping("/")
    public String getAll(Model model){
        model.addAttribute("listProfiles",profileRepository.findAll());
        return "admin/profile/home";
    }
}
