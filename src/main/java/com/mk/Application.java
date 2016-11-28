package com.mk;

import com.mk.stocks.BroadcastHandler;
import com.mk.stocks.StockWebSocketHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;

/**
 * Created by mk on 11/25/16.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableWebSocket
@EnableScheduling
public class Application extends SpringBootServletInitializer implements WebSocketConfigurer {


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(stockWebSocketHandler(), "/stocks").setAllowedOrigins("*").withSockJS();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }


    @Bean
    public WebSocketHandler stockWebSocketHandler() {
        return new PerConnectionWebSocketHandler(StockWebSocketHandler.class);
    }

    @Bean
    public BroadcastHandler createBroadcastHandler() {
        return new BroadcastHandler();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
