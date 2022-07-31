package com.vdqdryoy.repository;

import com.vdqdryoy.model.Profile;
import com.vdqdryoy.model.User;
import com.vdqdryoy.model.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserProfileService {
    void save(User user, Profile profile);
    public Page<UserProfile> findPageinated(Pageable pageable);
}
