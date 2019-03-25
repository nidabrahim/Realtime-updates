package com.easyeat.realtimeMicroservice.querySide.services.repositories;

import com.easyeat.realtimeMicroservice.querySide.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
