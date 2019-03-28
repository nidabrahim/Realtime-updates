package com.easyeat.realtimeMicroservice.gateway;

import com.easyeat.realtimeMicroservice.events.Event;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
@NoArgsConstructor
public class WebSocketEventsHandler extends TextWebSocketHandler {

    private WebSocketSession session;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // The WebSocket has been closed
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // The WebSocket has been opened
        this.session = session;
        System.out.println("WS Opened ");
        //session.sendMessage(new TextMessage("You are now connected to the server. This is the first message."));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        // A message has been received
        System.out.println("Message received: " + textMessage.getPayload());
    }

    public void sendEvent(Event event) throws IOException {
        this.session.sendMessage(new TextMessage(event.toString()));
    }
}
