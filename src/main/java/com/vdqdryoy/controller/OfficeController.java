package com.vdqdryoy.controller;


import com.vdqdryoy.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/office")
public class OfficeController {

    @Autowired
    MongoRepository officeRepository;

    @GetMapping("/")
    public String getAll(Model model){
        model.addAttribute("listOffice",officeRepository.findAll());
        return "admin/office/home";
    }

    @GetMapping("/showNewOfficeForm")
    public String showNewProductForm(Model model) {
        // create model attribute to bind form data
        Office office = new Office();
        model.addAttribute("office", office);
        return "admin/office/new_office_form";
    }
    @PostMapping("/saveOffice")
    public String saveProduct(@ModelAttribute("office") Office office) {
        // save employee to database
        officeRepository.save(office);
        return "redirect:/admin/office/";
    }
}
