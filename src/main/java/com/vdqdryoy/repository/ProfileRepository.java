package com.vdqdryoy.repository;


import com.vdqdryoy.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile,String> {
}
