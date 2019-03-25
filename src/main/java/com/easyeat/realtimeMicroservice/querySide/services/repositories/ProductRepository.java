package com.easyeat.realtimeMicroservice.querySide.services.repositories;

import com.easyeat.realtimeMicroservice.querySide.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
