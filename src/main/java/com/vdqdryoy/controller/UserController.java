package com.vdqdryoy.controller;

import com.vdqdryoy.model.Office;
import com.vdqdryoy.model.Profile;
import com.vdqdryoy.model.User;
import com.vdqdryoy.model.UserProfile;
import com.vdqdryoy.repository.OfficesRepository;
import com.vdqdryoy.repository.ProfileRepository;
import com.vdqdryoy.repository.UserProfileService;
import com.vdqdryoy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OfficesRepository officesRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserProfileService userProfileService;

    @GetMapping("/")
    public String getAll(Model model){
        return "redirect:/admin/user/listUsers";
    }

    @GetMapping(value = "listUsers")
    public String ListUser(Model model,@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size){

        List<Office> office = officesRepository.findAll();
        model.addAttribute("listOffice",officesRepository.findAll());

        int curentPage = page.orElse(1);
        int pageSize = size.orElse(2);
        Page<UserProfile> userProfilesPage = userProfileService.findPageinated(PageRequest.of(curentPage-1,pageSize));
        model.addAttribute("userPage",userProfilesPage);
        int totalPages = userProfilesPage.getTotalPages();
        if (totalPages >0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }

        List<User> userListByOffice = userRepository.findByOfficeId("046405e40301413eab23c924d0ccb333");
        if(!userListByOffice.isEmpty()){
            model.addAttribute("userListByOffice",userListByOffice);
        }

        return "admin/user/listusers";

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

    @PostMapping("/userByOfficeId")
    public String detailUserByOffice(@ModelAttribute("userByOfficeId") String officeId, Model model)
    {
        List<User> userByOfficeId = userRepository.findByOfficeId(officeId);
        final Model userByOfficeId1 = model.addAttribute("userByOfficeId", userByOfficeId);

        return "admin/user/detailuserbyofice";
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
