package com.easyeat.realtimeMicroservice.querySide.services.updateServices;

import com.easyeat.realtimeMicroservice.querySide.models.Order;
import com.easyeat.realtimeMicroservice.querySide.models.OrderStatus;
import com.easyeat.realtimeMicroservice.querySide.models.Product;
import com.easyeat.realtimeMicroservice.querySide.services.repositories.CustomerRepository;
import com.easyeat.realtimeMicroservice.querySide.services.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderUpdateService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    public OrderUpdateService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void create(String orderId, String customerId, List<Product> products){
        Optional.ofNullable(customerRepository.findById(customerId).get())
                .map(customer -> new Order(orderId,OrderStatus.opened,customer,products))
                .ifPresent(orderRepository::save);
    }

    public void update(String orderId, Order order){
        order.setId(orderId);
        orderRepository.save(order);
    }

    public void updateStatus(String orderId, OrderStatus status){
        Optional.ofNullable(orderRepository.findById(orderId).get())
                .map(order -> setStatus(order,status))
                .ifPresent(orderRepository::save);
    }

    public void delete(String orderId){
        orderRepository.deleteById(orderId);
    }

    private Order setStatus(Order order, OrderStatus status){
        order.setStatus(status);
        return order;
    }
}
