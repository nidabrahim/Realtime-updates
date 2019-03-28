package com.easyeat.realtimeMicroservice.api;

import com.easyeat.realtimeMicroservice.api.dto.CustomerDTO;
import com.easyeat.realtimeMicroservice.api.dto.ProductDTO;
import com.easyeat.realtimeMicroservice.querySide.models.Customer;
import com.easyeat.realtimeMicroservice.querySide.models.Order;
import com.easyeat.realtimeMicroservice.querySide.models.Product;
import com.easyeat.realtimeMicroservice.querySide.services.updateServices.CustomerUpdateService;
import com.easyeat.realtimeMicroservice.querySide.services.updateServices.ProductUpdateService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/products")
@ResponseStatus(HttpStatus.ACCEPTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {

    @Autowired
    ProductUpdateService productUpdateService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("create")
    public Product create(@RequestBody ProductDTO body){
        logger.info("ProductDTO : "+ body);
        String id = UUID.randomUUID().toString();
        return productUpdateService.create(id,body);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts(){
        return productUpdateService.getAllProducts();
    }

    @GetMapping("{id}")
    public Product getProducts(@PathVariable String id){
        return productUpdateService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        productUpdateService.delete(id);
    }
}
