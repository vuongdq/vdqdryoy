package com.vdqdryoy.repository;

import com.vdqdryoy.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;


@Service
public interface UserRepository extends MongoRepository<User,String> {
}
