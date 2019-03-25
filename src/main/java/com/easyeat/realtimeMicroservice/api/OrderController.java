package com.easyeat.realtimeMicroservice.api;

import com.easyeat.realtimeMicroservice.api.dto.OrderDTO;
import com.easyeat.realtimeMicroservice.commandeSide.commands.CancelOrderCommand;
import com.easyeat.realtimeMicroservice.commandeSide.commands.OpenOrderCommand;
import com.easyeat.realtimeMicroservice.commandeSide.services.OrderCommandService;
import com.easyeat.realtimeMicroservice.config.ResourceURI;
import com.easyeat.realtimeMicroservice.querySide.models.Order;
import com.easyeat.realtimeMicroservice.querySide.services.queryServices.OrderQueryService;
import com.easyeat.realtimeMicroservice.querySide.services.updateServices.OrderUpdateService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.callbacks.FutureCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;


@RestController
@RequestMapping("/orders")
@ResponseStatus(HttpStatus.ACCEPTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {

    @Autowired
    OrderQueryService orderQueryService;
    @Autowired
    OrderCommandService orderCommandService;

    Logger logger = LoggerFactory.getLogger(getClass());


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrders(){
        return orderQueryService.getAllOrders();
    }

    @GetMapping("{id}")
    public Order getOrder(@PathVariable String id){
        return orderQueryService.getOrderById(id);
    }

    @DeleteMapping("{id}")
    public Future<?> delete(@PathVariable String id){
        return orderCommandService.remove(id);
    }

    @PostMapping("create")
    public Future<?> create(@RequestBody OrderDTO body){
        logger.info("body : "+body);
        String id = UUID.randomUUID().toString();
        String customerId = body.getCustomerId();
        logger.info("Customer id : "+customerId);
        return orderCommandService.save(id,customerId);
    }

    @PutMapping("{id}/paid")
    public Future<?> setPaidStatus(@PathVariable String id){
        return orderCommandService.makeOrderPaid(id);
    }

    @PutMapping("{id}/ready")
    public Future<?> setReadyStatus(@PathVariable String id){
        return orderCommandService.makeOrderReady(id);
    }



}
