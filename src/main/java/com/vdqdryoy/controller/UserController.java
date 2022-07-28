package com.vdqdryoy.controller;

import com.vdqdryoy.model.Office;
import com.vdqdryoy.model.User;
import com.vdqdryoy.repository.OfficesRepository;
import com.vdqdryoy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OfficesRepository officeRepository;

    @GetMapping("/home1")
    public String home(Model model){
        List<Office> office = officeRepository.findAll();
        return "admin/user/home1";
    }

    @GetMapping("/")
    public String getAll(Model model){
        model.addAttribute("listUsers",userRepository.findAll());
        List<Office> office = officeRepository.findAll();
        model.addAttribute("listOffice",officeRepository.findAll());

        return "admin/user/home";
    }
    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);
        List<Office> office = officeRepository.findAll();
        model.addAttribute("listOffice",officeRepository.findAll());
        return "admin/user/new_user_form";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        // save Office to database

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        user.setBirthDate(now);
        user.setAccountStatus(1);
        userRepository.save(user);

        return "redirect:/admin/user/";
    }
}
