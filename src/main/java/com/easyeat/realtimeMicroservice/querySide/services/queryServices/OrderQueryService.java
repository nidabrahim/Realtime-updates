package com.easyeat.realtimeMicroservice.querySide.services.queryServices;

import com.easyeat.realtimeMicroservice.querySide.models.Order;
import com.easyeat.realtimeMicroservice.querySide.services.repositories.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderQueryService {
    private OrderRepository orderRepository;

    public OrderQueryService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(String id){
        return orderRepository.findById(id).get();
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
