package com.vdqdryoy.controller;


import com.fasterxml.jackson.databind.DatabindException;
import com.vdqdryoy.model.Office;
import com.vdqdryoy.model.ResponseObject;
import com.vdqdryoy.repository.OfficesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        String uuid = UUID.randomUUID().toString().replace("-", "");
        office.setId(uuid);
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
    @PostMapping("/updateOffice")
    public String updateOffice(@ModelAttribute("office") Office office) {
        // save Office to database
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        officeRepository.save(office);
        return "redirect:/admin/office/";
    }

    @GetMapping("/delete/{id}")
    public String deleteOffice(@PathVariable String id){
        Optional<Office> foundOffice = officeRepository.findById(id);
        if(foundOffice.isPresent()){
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            Office office = foundOffice.get();
            office.setDeletedDate(now);
            officeRepository.save(office);
        }
        return "redirect:/admin/office/";


    }


}
