package com.vdqdryoy.repository;

import com.vdqdryoy.model.Office;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OfficeRepository extends MongoRepository<Office,String> {
}
