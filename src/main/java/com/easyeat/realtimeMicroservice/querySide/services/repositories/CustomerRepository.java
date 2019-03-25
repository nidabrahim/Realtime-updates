package com.easyeat.realtimeMicroservice.querySide.services.repositories;

import com.easyeat.realtimeMicroservice.querySide.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
