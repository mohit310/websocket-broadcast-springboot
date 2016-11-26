package com.mk.stocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by mk on 11/25/16.
 */
public class StockWebSocketHandler extends TextWebSocketHandler {

    public static final Logger logger = LoggerFactory.getLogger(StockWebSocketHandler.class);
    private BroadcastHandler broadcastHandler;

    @Autowired
    public StockWebSocketHandler(BroadcastHandler broadcastHandler) {
        logger.debug("Got handler :" + broadcastHandler.toString());
        this.broadcastHandler = broadcastHandler;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("Adding session :" + session.getId());
        broadcastHandler.add(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.debug("Removing session :" + session.getId());
        broadcastHandler.remove(session);
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.debug("GOT INIT MESSAGE: " + message.getPayload());
    }
}
