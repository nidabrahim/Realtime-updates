package com.easyeat.realtimeMicroservice.gateway;

import com.easyeat.realtimeMicroservice.events.OrderCanceledEvent;
import com.easyeat.realtimeMicroservice.events.OrderOpenedEvent;
import com.easyeat.realtimeMicroservice.events.OrderPaidEvent;
import com.easyeat.realtimeMicroservice.events.OrderReadyEvent;
import com.easyeat.realtimeMicroservice.events.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WebSocketEventsTranslator {

    @Autowired
    WebSocketEventsHandler wsSession;

    Logger logger = LoggerFactory.getLogger(getClass());

    @EventHandler
    public void on(OrderCanceledEvent event){
        sendEvent(event);
    }

    @EventHandler
    public void on(OrderOpenedEvent event){
        sendEvent(event);
    }

    @EventHandler
    public void on(OrderPaidEvent event){
        sendEvent(event);
    }

    @EventHandler
    public void on(OrderReadyEvent event){
        sendEvent(event);
    }

    private void sendEvent(Event event){
        logger.info("Sending order event to webSocket : " + event);
        //try {
        //    wsSession.sendEvent(event);
        //} catch (IOException e) {
        //    e.printStackTrace();
       // }
    }

}
