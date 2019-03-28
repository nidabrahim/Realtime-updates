package com.easyeat.realtimeMicroservice.commandeSide.aggregates;

import com.easyeat.realtimeMicroservice.api.dto.ProductDTO;
import com.easyeat.realtimeMicroservice.commandeSide.commands.CancelOrderCommand;
import com.easyeat.realtimeMicroservice.commandeSide.commands.OpenOrderCommand;
import com.easyeat.realtimeMicroservice.commandeSide.commands.PayOrderCommand;
import com.easyeat.realtimeMicroservice.commandeSide.commands.ReadyOrderCommand;
import com.easyeat.realtimeMicroservice.events.OrderCanceledEvent;
import com.easyeat.realtimeMicroservice.events.OrderOpenedEvent;
import com.easyeat.realtimeMicroservice.events.OrderPaidEvent;
import com.easyeat.realtimeMicroservice.events.OrderReadyEvent;
import com.easyeat.realtimeMicroservice.querySide.models.OrderStatus;
import com.easyeat.realtimeMicroservice.querySide.models.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderAggregate {
    @AggregateIdentifier
    String id;
    String customerId;
    OrderStatus status;
    List<Product> products;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(OpenOrderCommand command){
        List<Product> products = new ArrayList<Product>();
        List<ProductDTO> productDTOS = command.getProducts();
        for (ProductDTO product:productDTOS) {
            products.add(new Product(product.getId(),product.getName(),product.getDescription(),product.getPrice(),null));
        }
        apply(new OrderOpenedEvent(command.getId(), command.getCustomerId(),products));
    }

    @CommandHandler
    public void on(CancelOrderCommand command){
        //checkStatus();
        apply(new OrderCanceledEvent(command.getOrderId()));
    }

    @CommandHandler
    public void on(PayOrderCommand command){
        //checkStatus();
        apply(new OrderPaidEvent(command.getOrderId()));
    }

    @CommandHandler
    public void on(ReadyOrderCommand command){
        //checkStatus();
        apply(new OrderReadyEvent(command.getOrderId()));
    }

    @EventSourcingHandler
    public void on(OrderOpenedEvent event){
        id = event.getId();
        customerId = event.getCustomerId();
        status = OrderStatus.opened;
        products = event.getProducts();
    }

    @EventSourcingHandler
    public void on(OrderCanceledEvent event){
        id = event.getOrderId();
        status = OrderStatus.canceled;
    }

    @EventSourcingHandler
    public void on(OrderPaidEvent event){
        id = event.getOrderId();
        status = OrderStatus.paid;
    }

    @EventSourcingHandler
    public void on(OrderReadyEvent event){
        id = event.getOrderId();
        status = OrderStatus.Ready;
    }

    private void checkStatus(){
        Assert.state(OrderStatus.opened == status, () -> "Order must be open");
    }
}
