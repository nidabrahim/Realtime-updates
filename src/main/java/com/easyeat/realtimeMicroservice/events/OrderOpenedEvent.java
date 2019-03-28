package com.easyeat.realtimeMicroservice.events;

import com.easyeat.realtimeMicroservice.api.dto.ProductDTO;
import com.easyeat.realtimeMicroservice.querySide.models.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderOpenedEvent implements Event{
    String id;
    String customerId;
    List<Product> products;
}
