package com.vdqdryoy.controller;

import com.vdqdryoy.model.Office;
import com.vdqdryoy.model.Profile;
import com.vdqdryoy.model.User;
import com.vdqdryoy.model.UserProfile;
import com.vdqdryoy.repository.OfficesRepository;
import com.vdqdryoy.repository.ProfileRepository;
import com.vdqdryoy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OfficesRepository officesRepository;

    @Autowired
    ProfileRepository profileRepository;

    @GetMapping("/")
    public String getAll(Model model){
        model.addAttribute("listUsers",userRepository.findAll());
        List<Office> office = officesRepository.findAll();
        model.addAttribute("listOffice",officesRepository.findAll());

        return "admin/user/home";
    }

    @GetMapping("/detail/{id}")
    public String detailUser(@PathVariable(value = "id") String id, Model model){
        Optional<User> foundUser = userRepository.findById(id);
        Optional<Profile> foundProfile = profileRepository.findById(id);
        if(foundUser.isPresent() && foundProfile.isPresent()){
            model.addAttribute("user",foundUser.get());
            model.addAttribute("profile",foundProfile.get());
        }

        return "admin/user/detail";
    }


    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model) {
        // create model attribute to bind form data

        List<Office> listOffice = officesRepository.findAll();
        UserProfile userProfile = new UserProfile();
        model.addAttribute("userProfile", userProfile);
        model.addAttribute("listOffice", listOffice);
        return "admin/user/new_user_form";
    }

    @PostMapping("/saveUser")
    public String saveUserProfile(@ModelAttribute("userProfile") UserProfile userProfile) {
        // save Office to database
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        String uuid = UUID.randomUUID().toString().replace("-", "");
        User user = new User(uuid,userProfile.getFirstName(),userProfile.getLastName(),userProfile.getOfficeId(),uuid,userProfile.getAddress(),1,now);
        Profile profile = new Profile(uuid,userProfile.getGender(),userProfile.getPhoneNumber(),userProfile.getCareer());

        userRepository.save(user);
        profileRepository.save(profile);
        return "redirect:/admin/user/";
    }
}
