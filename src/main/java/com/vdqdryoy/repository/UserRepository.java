package com.vdqdryoy.repository;

import com.vdqdryoy.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    public List<User> findByOfficeId(String o);
}
