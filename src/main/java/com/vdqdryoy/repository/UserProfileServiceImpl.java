package com.vdqdryoy.repository;

import com.vdqdryoy.model.Profile;
import com.vdqdryoy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
