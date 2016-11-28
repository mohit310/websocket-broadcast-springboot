package com.mk.stocks.timer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mk.stocks.entity.Stock;
import com.mk.stocks.BroadcastHandler;
import com.mk.stocks.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mk on 11/25/16.
 */
@Component
public class StockPriceScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(StockPriceScheduledTask.class);
    private final BroadcastHandler broadcastHandler;
    private final StockService stockService;
    private final List<String> tickers;

    @Autowired
    public StockPriceScheduledTask(BroadcastHandler broadcastHandler, StockService stockService, @Value("${tickers}") String tickers) {
        this.broadcastHandler = broadcastHandler;
        this.stockService = stockService;
        this.tickers = Arrays.asList(tickers.split(","));
    }

    @Scheduled(fixedRate = 5000)
    public void getPrices() {
        logger.debug("Getting latest prices now");
        List<Stock> stockPrices = stockService.getPrices(tickers);
        logger.debug("Broadcasting now");
        ObjectMapper mapper = new ObjectMapper();
        try {
            broadcastHandler.broadcast(mapper.writeValueAsString(stockPrices));
        } catch (JsonProcessingException e) {
            logger.error("Erorr in getting prices", e);
        }
    }
}
