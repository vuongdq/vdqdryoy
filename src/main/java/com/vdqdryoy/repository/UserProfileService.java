package com.vdqdryoy.repository;

import com.vdqdryoy.model.Profile;
import com.vdqdryoy.model.User;

public interface UserProfileService {
    void save(User user, Profile profile);
}
