package com.vdqdryoy.repository;

import com.vdqdryoy.model.Profile;
import com.vdqdryoy.model.User;
import com.vdqdryoy.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserProfileServiceImpl implements UserProfileService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void save(User user, Profile profile){
        if(user.getProfileId()==profile.getId()){
            userRepository.save(user);
            profileRepository.save(profile);
        }else throw new IllegalArgumentException("the id not match");
    }


    public Page<UserProfile> findPageinated(Pageable pageable){


        List<UserProfile> userProfiles = new ArrayList<>();

        List<User> listUsers = userRepository.findAll();

        listUsers.forEach((user -> {
            String userId = user.getId();
            Optional<Profile> profile = profileRepository.findById(userId);

            if (profile.isPresent()) {
                Profile profile1 = profile.get();
                UserProfile userProfile = new UserProfile();
                userProfile.setId(user.getId());
                userProfile.setFirstName(user.getFirstName());
                userProfile.setLastName(user.getLastName());
                userProfile.setAddress(user.getAddress());
                userProfile.setGender(profile1.getGender());
                userProfile.setPhoneNumber(profile1.getPhoneNumber());
                userProfile.setCareer(profile1.getCareer());
                // Add to List
                userProfiles.add(userProfile);
            }


        }));


        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage*pageSize;
        List<UserProfile> list;
        if(userProfiles.size()<startItem){
            list = Collections.emptyList();
        }else {
            int toIndex = Math.min(startItem+pageSize,userProfiles.size());
            list = userProfiles.subList(startItem,toIndex);
        }
        Page<UserProfile> userPage = new PageImpl<UserProfile>(list, PageRequest.of(currentPage,pageSize),userProfiles.size());
        return userPage;
    }
}
