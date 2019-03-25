package com.easyeat.realtimeMicroservice.querySide.eventHandler;

import com.easyeat.realtimeMicroservice.events.Event;
import com.easyeat.realtimeMicroservice.events.OrderReadyEvent;
import com.easyeat.realtimeMicroservice.querySide.models.OrderStatus;
import com.easyeat.realtimeMicroservice.querySide.services.updateServices.OrderUpdateService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderReadyEventListener  {
    @Autowired
    OrderUpdateService orders;

    Logger logger = LoggerFactory.getLogger(getClass());

    @EventHandler
    public void on(OrderReadyEvent event){
        logger.info("Handling event : " + event);

        orders.updateStatus(event.getOrderId(), OrderStatus.Ready);
    }

}
