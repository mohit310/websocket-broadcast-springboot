package com.mk.stocks;

import com.mk.stocks.com.mk.stocks.StocksClientWebSocketHandler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by mk on 11/25/16.
 */
public class TestPricesApplication {


    private static Logger logger = LoggerFactory.getLogger(TestPricesApplication.class);


    @Test
    public void testPrice() {
        try {
            AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext(ClientConfig.class);
            System.out.println("\n\n\nWhen ready, press any key to exit\n\n\n");
            System.in.read();
            cxt.close();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Configuration
    static class ClientConfig {

        @Bean
        public WebSocketConnectionManager connectionManager() {
            WebSocketConnectionManager manager = new WebSocketConnectionManager(client(), handler(), "ws://localhost:8080/stocks/websocket");
            manager.setAutoStartup(true);
            return manager;
        }

        @Bean
        public TextWebSocketHandler handler() {
            return new StocksClientWebSocketHandler();
        }

        @Bean
        public WebSocketClient client() {
            return new StandardWebSocketClient();
        }
    }


}
