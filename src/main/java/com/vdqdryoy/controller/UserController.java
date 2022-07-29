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

import javax.swing.*;
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

        List<Office> office = officesRepository.findAll();
        model.addAttribute("listOffice",officesRepository.findAll());

        List<User> listUsers = userRepository.findAll();

//        model.addAttribute("listUsers",listUsers);

        List<UserProfile> listUserProfile = new ArrayList<>();

        listUsers.forEach((user -> {
            String userId = user.getId();
            Optional<Profile> profile = profileRepository.findById(userId);

            if (profile.isPresent()) {
                Profile profile1 = profile.get();
                UserProfile userProfile = new UserProfile();
                userProfile.setId(user.getId());
                userProfile.setFirstName(user.getFirstName());
                userProfile.setFirstName(user.getLastName());
                userProfile.setAddress(user.getAddress());
                userProfile.setGender(profile1.getGender());
                userProfile.setPhoneNumber(profile1.getPhoneNumber());
                userProfile.setCareer(profile1.getCareer());
                // Add to List
                listUserProfile.add(userProfile);
            }


        }));

        model.addAttribute("listUserProfile",listUserProfile);

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

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id){
        Optional<User> foundUser = userRepository.findById(id);
        Optional<Profile> foundProfile = profileRepository.findById(id);
        if(foundUser.isPresent() && foundProfile.isPresent()){

            User userUpdate = foundUser.get();
            userUpdate.setAccountStatus(2);

            userRepository.save(userUpdate);
        }
        return "redirect:/admin/user/";


    }
}
