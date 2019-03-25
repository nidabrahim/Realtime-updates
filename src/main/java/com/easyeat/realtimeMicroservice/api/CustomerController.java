package com.easyeat.realtimeMicroservice.api;

import com.easyeat.realtimeMicroservice.api.dto.CustomerDTO;
import com.easyeat.realtimeMicroservice.api.dto.OrderDTO;
import com.easyeat.realtimeMicroservice.querySide.models.Customer;
import com.easyeat.realtimeMicroservice.querySide.services.updateServices.CustomerUpdateService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/customers")
@ResponseStatus(HttpStatus.ACCEPTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {

    @Autowired
    CustomerUpdateService customerUpdateService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("create")
    public Customer create(@RequestBody CustomerDTO body){
        logger.info("CustomerDTO : "+ body);
        String id = UUID.randomUUID().toString();
        return customerUpdateService.create(id,body);
    }
}
