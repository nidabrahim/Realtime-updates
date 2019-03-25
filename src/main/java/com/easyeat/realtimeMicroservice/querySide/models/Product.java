package com.easyeat.realtimeMicroservice.querySide.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private List<Order> orders_ids;

}
