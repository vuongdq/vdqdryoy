package com.vdqdryoy.repository;

import com.vdqdryoy.model.Office;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfficesRepository extends MongoRepository<Office,String> {
    List<Office> findByName(String name);
}
