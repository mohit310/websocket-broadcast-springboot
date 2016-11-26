package com.mk.stocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by mk on 11/24/16.
 */
public class BroadcastHandler {

    private static final Logger logger = LoggerFactory.getLogger(BroadcastHandler.class);

    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private final Object MONITOR = new Object();

    public void add(WebSocketSession session) {
        synchronized (MONITOR) {
            if (!sessions.contains(session)) sessions.add(session);
        }
    }

    public void broadcast(String message) {
        logger.debug("Start Broadcasting");
        for (WebSocketSession session : sessions) {
            try {
                if (session.isOpen()) session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                logger.error("Error in sending message for session {}", session.getId(), e);
            }
        }
        logger.debug("Finished Broadcasting");
        logger.info("Total active sessions {}", sessions.size());
    }

    public void remove(WebSocketSession session) {
        synchronized (MONITOR) {
            logger.debug("REMOVING channel: " + session.toString());
            sessions.remove(session);
        }
    }
}
