package com.bsse5a.EcommerceWeb.chat;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("WebSocket Connected: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {

        String input = message.getPayload();
        System.out.println("Received message: " + input);

        String response;

        switch (input) {
            case "1":
                response = "About Us :\n FitMart provides premium gym equipment across Pakistan.\n"+
                        " Choose an option: 2 – Contact Us" +
                        " 3 – Working Hours";
                break;
            case "2":
                response = "Contact us :\n Contact us at support@fitmart.pk or call 0300-1234567\n"+
                        " Choose an option: 1 – About Us" +
                        " 3 – Working Hours";
                break;
            case "3":
                response = "Working hours:\n Mon–Sat, 9 AM – 6 PM\n"+
                        " Choose an option: 1 – About Us | 2 - Contact us ";
                break;
            default:
                response = "Invalid option. Please enter 1–3.";
        }

        System.out.println("📤 Sending response: " + response);
        session.sendMessage(new TextMessage(response));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("WebSocket Disconnected: " + session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("WebSocket Error: " + exception.getMessage());
    }
}