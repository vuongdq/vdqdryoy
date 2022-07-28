package com.vdqdryoy.controller;


import com.fasterxml.jackson.databind.DatabindException;
import com.vdqdryoy.model.Office;
import com.vdqdryoy.repository.OfficesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/admin/office")
public class OfficeController {

    @Autowired
    OfficesRepository officeRepository;

    @GetMapping("/")
    public String getAll(Model model){
        model.addAttribute("listOffice",officeRepository.findAll());
        return "admin/office/home";
    }

    @GetMapping("/showNewOfficeForm")
    public String showNewOfficeForm(Model model) {
        // create model attribute to bind form data
        Office office = new Office();
        model.addAttribute("office", office);
        return "admin/office/new_office_form";
    }
    @PostMapping("/saveOffice")
    public String saveOffice(@ModelAttribute("office") Office office) {
        // save Office to database
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        office.setDeletedDate(now);
        officeRepository.save(office);
        return "redirect:/admin/office/";
    }

    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") String id, Model model) {

        // get employee from the service
        Optional<Office> office = officeRepository.findById(id);
        if(office.isPresent()){

            model.addAttribute("office", office);
        }
        return "admin/office/update_office_form";
    }
}
