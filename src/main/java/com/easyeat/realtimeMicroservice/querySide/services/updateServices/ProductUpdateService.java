package com.easyeat.realtimeMicroservice.querySide.services.updateServices;

import com.easyeat.realtimeMicroservice.api.dto.CustomerDTO;
import com.easyeat.realtimeMicroservice.api.dto.ProductDTO;
import com.easyeat.realtimeMicroservice.querySide.models.Customer;
import com.easyeat.realtimeMicroservice.querySide.models.Order;
import com.easyeat.realtimeMicroservice.querySide.models.Product;
import com.easyeat.realtimeMicroservice.querySide.services.repositories.CustomerRepository;
import com.easyeat.realtimeMicroservice.querySide.services.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductUpdateService {

    @Autowired
    ProductRepository productRepository;

    public ProductUpdateService() {
    }

    public Product create(String id, ProductDTO product){
        return productRepository.save(new Product(id,product.getName(),product.getDescription(),product.getPrice(),null));
    }

    public void update(String id, Product product){
        product.setId(id);
        productRepository.save(product);
    }

    public void delete(String id){
        productRepository.deleteById(id);
    }

    public Product getProductById(String id){
        return productRepository.findById(id).get();
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

}
