package com.easyeat.realtimeMicroservice.querySide.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String id;
    private OrderStatus status;
    private Customer customer;
    private List<Product> products_ids;

}
