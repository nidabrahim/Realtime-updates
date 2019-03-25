package com.easyeat.realtimeMicroservice.querySide.services.updateServices;

import com.easyeat.realtimeMicroservice.api.dto.CustomerDTO;
import com.easyeat.realtimeMicroservice.querySide.models.Customer;
import com.easyeat.realtimeMicroservice.querySide.services.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerUpdateService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerUpdateService() {
    }

    public Customer create(String id, CustomerDTO customer){
        return customerRepository.save(new Customer(id,customer.getName(),customer.getEmail(),customer.getAddress()));
    }
}
